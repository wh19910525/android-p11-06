package com.tarena.utils;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpEntity;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;

import com.tarena.entity.Task;

public class AsyncImageLoader {
	
	public static final int MSG_TAG_FINISHED = 1;
	private boolean isLoop;
	private Thread workThread;
	private ArrayList<Task> tasks;
	private HashMap<String, SoftReference<Bitmap>> caches;//

	// private Handler handler;

	public AsyncImageLoader(final Handler handler) {//
		
		this.tasks = new ArrayList<Task>();
		this.caches = new HashMap<String, SoftReference<Bitmap>>();
		this.isLoop = true;
		
		this.workThread = new Thread() {
			@Override
			public void run() {
				while (isLoop) {
					// 轮询任务集合，执行加载图片任务
					while (tasks.size() > 0) {
						try {
							Task task = tasks.remove(0);
							// 加载图片
							HttpEntity entity = HttpUtils.getEntity(HttpUtils.BASE_URL + task.getPath(), null, HttpUtils.METHOD_GET);
							byte[] data = EntityUtils.toByteArray(entity);
							task.setBitmap(BitmapUtils.loadBitmap(data, 100, 100));

							// 添加到缓存集合
							caches.put(task.getPath(), new SoftReference<Bitmap>(task.getBitmap()));

							// 添加到文件缓存
							BitmapUtils.save(task.getBitmap(), "/mnt/sdcard/" + task.getPath());

							// 发送消息
							Message msg = Message.obtain(handler, MSG_TAG_FINISHED, task);
							msg.sendToTarget();
						} catch (ConnectTimeoutException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					// 线程等待
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		};
		this.workThread.start();
	}

	public Bitmap loadBitmap(String path) {
		Bitmap bm = null;
		// 判断缓存集合中是否存在该图片
		if (caches.containsKey(path)) {
			bm = caches.get(path).get();
			// 如果缓存图片存在，则返回，否则从缓存集合中移除该路径
			if (bm != null)
				return bm;
			else
				caches.remove(path);//
		}

		// 从文件缓存查找图片
		bm = BitmapUtils.loadBitmap("/mnt/sdcard/" + path);
		if (bm != null)
			return bm;

		// 缓存不存在，将任务添加到任务集合
		Task task = new Task();
		task.setPath(path);
		tasks.add(task);
		synchronized (workThread) {
			workThread.notify();
		}

		return bm;
	}

}
