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
					content = "�������ӳ�ʱ";
					break;
				case MSG_TAG_EXISTS:
					content = "�ļ��Ѵ��ڣ������ظ�����:" + msg.obj.toString();
					break;
				case MSG_TAG_FAILED:
					content = "����ʧ��:" + msg.obj.toString();
					break;
				case MSG_TAG_FINISHED:
					content = "������ɣ�" + msg.obj.toString();
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
					// ��ѯ���񼯺ϣ���������
					while (tasks.size() > 0) {
						MusicDownloadTask task = tasks.remove(0);
						// �жϱ����Ƿ���ڸ�����
						File file = new File(task.getPath());
						if (file.exists()) {
							// ����ļ��Ѵ��ڷ�����Ϣ�����߳�
							msg = Message.obtain(handler, MSG_TAG_EXISTS,
									task.getUri());
							msg.sendToTarget();
							continue;
						}

						try {
							// ��������
							HttpEntity entity = HttpUtils.getEntity(
									task.getUri(), null, HttpUtils.METHOD_GET);
							InputStream in = HttpUtils.getStream(entity);
							StreamUtils.save(in, task.getPath());

							// ����Ϣ�����߳�
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
		workThread.start();
	}

	public void download(String uri, String path) {
		// ����һ����������������ӵ����񼯺�
		MusicDownloadTask task = new MusicDownloadTask();
		task.setPath(path);
		task.setUri(uri);
		tasks.add(task);
		// �����߳�
		synchronized (workThread) {
			workThread.notify();
		}
	}
}
