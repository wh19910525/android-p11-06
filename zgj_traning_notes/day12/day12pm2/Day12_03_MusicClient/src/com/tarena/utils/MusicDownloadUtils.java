package com.tarena.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.conn.ConnectTimeoutException;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.tarena.entity.MusicDownloadTask;

public class MusicDownloadUtils {
	private static final int MSG_TAG_EXISTS = 1;
	private static final int MSG_TAG_FINISHED = 2;
	private static final int MSG_TAG_FAILED = 3;
	private static final int MSG_TAG_CONNECT_TIME_OUT = 4;

	private ArrayList<MusicDownloadTask> tasks;
	private boolean isLoop;
	private Thread workThread;
	private Handler handler;

	public MusicDownloadUtils(final Context context) {
		tasks = new ArrayList<MusicDownloadTask>();
		isLoop = true;
		this.handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				String content = null;
				switch (msg.what) {
				case MSG_TAG_CONNECT_TIME_OUT:
					content = "网络连接超时";
					break;
				case MSG_TAG_EXISTS:
					content = "文件已存在，请勿重复下载:" + msg.obj.toString();
					break;
				case MSG_TAG_FAILED:
					content = "下载失败:" + msg.obj.toString();
					break;
				case MSG_TAG_FINISHED:
					content = "下载完成：" + msg.obj.toString();
					break;
				}
				Toast.makeText(context, content, 3000).show();
			};
		};
		workThread = new Thread() {
			@Override
			public void run() {
				Message msg = null;
				while (isLoop) {
					// 轮询任务集合，下载音乐
					while (tasks.size() > 0) {
						MusicDownloadTask task = tasks.remove(0);
						// 判断本地是否存在该音乐
						File file = new File(task.getPath());
						if (file.exists()) {
							// 如果文件已存在发送消息回主线程
							msg = Message.obtain(handler, MSG_TAG_EXISTS,
									task.getUri());
							msg.sendToTarget();
							continue;
						}

						try {
							// 下载音乐
							HttpEntity entity = HttpUtils.getEntity(
									task.getUri(), null, HttpUtils.METHOD_GET);
							InputStream in = HttpUtils.getStream(entity);
							StreamUtils.save(in, task.getPath());

							// 发消息回主线程
							msg = Message.obtain(handler, MSG_TAG_FINISHED,
									task.getPath());
							msg.sendToTarget();
						} catch (ConnectTimeoutException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							handler.sendEmptyMessage(MSG_TAG_CONNECT_TIME_OUT);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							msg = Message.obtain(handler, MSG_TAG_FAILED,
									task.getUri());
							msg.sendToTarget();
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
		workThread.start();
	}

	public void download(String uri, String path) {
		// 创建一个音乐下载任务，添加到任务集合
		MusicDownloadTask task = new MusicDownloadTask();
		task.setPath(path);
		task.setUri(uri);
		tasks.add(task);
		// 唤醒线程
		synchronized (workThread) {
			workThread.notify();
		}
	}
}
