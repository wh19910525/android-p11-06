package com.tarena.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.conn.ConnectTimeoutException;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.format.Formatter;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.tarena.day1201.MusicClientActivity;
import com.tarena.day1201.R;
import com.tarena.entity.MusicDownloadTask;

public class MusicDownloadUtils {
	
	private static final int MSG_TAG_EXISTS = 1;
	private static final int MSG_TAG_FINISHED = 2;
	private static final int MSG_TAG_FAILED = 3;
	private static final int MSG_TAG_CONNECT_TIME_OUT = 4;
	private static final int MSG_TAG_STARTED = 5;
	public static final int MSG_TAG_UPDATE_PROGRESS = 6;
	private static final int NOTI_ID = 0;

	private ArrayList<MusicDownloadTask> tasks;
	private boolean isLoop;
	private Thread workThread;
	private Handler handler;
	private String musicName;
	private long fileLength;
	private Notification noti;
	private NotificationManager manager;
	private Context context;

	public MusicDownloadUtils(final Context context) {
		this.context = context;
		this.tasks = new ArrayList<MusicDownloadTask>();
		this.isLoop = true;
		this.manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		// ����֪ͨ����
		this.noti = new Notification(android.R.drawable.ic_menu_save, "����", System.currentTimeMillis());
		this.noti.defaults = Notification.DEFAULT_LIGHTS;
		this.noti.flags = Notification.FLAG_NO_CLEAR;
		this.noti.contentIntent = PendingIntent.getActivity(context, 0,
				new Intent(context, MusicClientActivity.class),
				PendingIntent.FLAG_UPDATE_CURRENT);
		this.noti.contentView = new RemoteViews(context.getPackageName(),R.layout.layout_noti);

		this.handler = new Handler() {
			public void handleMessage(android.os.Message msg) {
				String content = null;
				switch (msg.what) {
				case MSG_TAG_CONNECT_TIME_OUT:
					content = "�������ӳ�ʱ";
					Toast.makeText(context, content, 3000).show();
					break;
				case MSG_TAG_EXISTS:
					content = "�ļ��Ѵ��ڣ������ظ�����:" + msg.obj.toString();
					Toast.makeText(context, content, 3000).show();
					//Toast.makeText(context, context.toString(), 3000).show();
					break;
				case MSG_TAG_STARTED:// ��ʼ����
					noti.contentView.setTextViewText(R.id.tvFileName, musicName);
					noti.contentView.setTextViewText(R.id.tvLoadedLength, format(0));
					noti.contentView.setTextViewText(R.id.tvFileLength, format(fileLength));
					noti.contentView.setProgressBar(R.id.progressBar1, (int) fileLength, 0, false);
					manager.notify(NOTI_ID, noti);
					break;
				case MSG_TAG_UPDATE_PROGRESS:
					noti.contentView.setTextViewText(R.id.tvLoadedLength, format(msg.arg1 * 1024));
					noti.contentView.setProgressBar(R.id.progressBar1, (int) fileLength, msg.arg1 * 1024, false);
					manager.notify(NOTI_ID, noti);
					break;
				case MSG_TAG_FAILED:
					Toast.makeText(context, "�ļ�����ʧ��...", 3000).show();
				case MSG_TAG_FINISHED:
					manager.cancel(NOTI_ID);
					break;
				}

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
							msg = Message.obtain(handler, MSG_TAG_EXISTS, task.getUri());
							msg.sendToTarget();
							continue;
						}

						try {
							// ��������
							HttpEntity entity = HttpUtils.getEntity(task.getUri(), null, HttpUtils.METHOD_GET);
							musicName = file.getName();// ��ȡ��ǰ���ص����ֵ�������
							fileLength = HttpUtils.getLength(entity);// ��ȡ���ֵ��ļ�����
							// ������Ϣ����ʼ����
							handler.sendEmptyMessage(MSG_TAG_STARTED);
							// ����
							InputStream in = HttpUtils.getStream(entity);
							StreamUtils.save(in, task.getPath(), handler);

							// ����Ϣ�����߳�
							msg = Message.obtain(handler, MSG_TAG_FINISHED, task.getPath());
							msg.sendToTarget();
						} catch (ConnectTimeoutException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							handler.sendEmptyMessage(MSG_TAG_CONNECT_TIME_OUT);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							msg = Message.obtain(handler, MSG_TAG_FAILED, task.getUri());
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

	private String format(long length) {
		return Formatter.formatFileSize(context, length);
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
