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
					// ��ѯ���񼯺ϣ�ִ�м���ͼƬ����
					while (tasks.size() > 0) {
						try {
							Task task = tasks.remove(0);
							// ����ͼƬ
							HttpEntity entity = HttpUtils.getEntity(HttpUtils.BASE_URL + task.getPath(), null, HttpUtils.METHOD_GET);
							byte[] data = EntityUtils.toByteArray(entity);
							task.setBitmap(BitmapUtils.loadBitmap(data, 100, 100));

							// ��ӵ����漯��
							caches.put(task.getPath(), new SoftReference<Bitmap>(task.getBitmap()));

							// ��ӵ��ļ�����
							BitmapUtils.save(task.getBitmap(), "/mnt/sdcard/" + task.getPath());

							// ������Ϣ
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
					
					// �̵߳ȴ�
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
		// �жϻ��漯�����Ƿ���ڸ�ͼƬ
		if (caches.containsKey(path)) {
			bm = caches.get(path).get();
			// �������ͼƬ���ڣ��򷵻أ�����ӻ��漯�����Ƴ���·��
			if (bm != null)
				return bm;
			else
				caches.remove(path);//
		}

		// ���ļ��������ͼƬ
		bm = BitmapUtils.loadBitmap("/mnt/sdcard/" + path);
		if (bm != null)
			return bm;

		// ���治���ڣ���������ӵ����񼯺�
		Task task = new Task();
		task.setPath(path);
		tasks.add(task);
		synchronized (workThread) {
			workThread.notify();
		}

		return bm;
	}

}
