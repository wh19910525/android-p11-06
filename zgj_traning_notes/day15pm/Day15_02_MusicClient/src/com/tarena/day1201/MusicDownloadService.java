package com.tarena.day1201;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.conn.ConnectTimeoutException;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.format.Formatter;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.tarena.utils.GlobalUtils;
import com.tarena.utils.HttpUtils;
import com.tarena.utils.StreamUtils;

public class MusicDownloadService extends IntentService {
	private Handler handler;
	private Notification noti;
	private NotificationManager manager;
	private String musicName;
	private int fileLength;

	public MusicDownloadService() {
		super("workThread");
	}

	private String format(long length) {
		return Formatter.formatFileSize(this, length);
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		noti = new Notification(android.R.drawable.ic_notification_overlay,
				"下载提示", System.currentTimeMillis());
		noti.defaults = Notification.DEFAULT_LIGHTS;
		noti.flags = Notification.FLAG_NO_CLEAR;
		noti.contentIntent = PendingIntent.getActivity(this, 0, new Intent(
				this, MusicClientActivity.class),
				PendingIntent.FLAG_UPDATE_CURRENT);
		noti.contentView = new RemoteViews(getPackageName(),
				R.layout.layout_noti);

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case GlobalUtils.MSG_TAG_EXISTS:// 文件已存在
					Toast.makeText(MusicDownloadService.this, "文件已存在，请勿重复下载.",
							3000).show();
					break;
				case GlobalUtils.MSG_TAG_STARTED:// 开始下载
					noti.contentView
							.setTextViewText(R.id.tvFileName, musicName);
					noti.contentView.setTextViewText(R.id.tvFileLength,
							format(fileLength));
					noti.contentView.setTextViewText(R.id.tvLoadedLength,
							format(0));
					noti.contentView.setProgressBar(R.id.progressBar1,
							fileLength, 0, false);
					manager.notify(0, noti);
					break;
				case GlobalUtils.MSG_TAG_UPDATE_PROGRESS:// 下载进度变更
					noti.contentView.setTextViewText(R.id.tvLoadedLength,
							format(msg.arg1));
					noti.contentView.setProgressBar(R.id.progressBar1,
							fileLength, msg.arg1, false);
					manager.notify(0, noti);
					break;
				case GlobalUtils.MSG_TAG_FAILED:// 下载失败
					Notification noti1 = new Notification(
							android.R.drawable.ic_notification_overlay, "下载失败",
							System.currentTimeMillis());
					noti1.defaults = Notification.DEFAULT_LIGHTS;
					noti1.flags = Notification.FLAG_NO_CLEAR;
					noti1.setLatestEventInfo(MusicDownloadService.this, "下载失败",
							"文件下载失败。", PendingIntent.getActivity(
									MusicDownloadService.this, 0, new Intent(
											MusicDownloadService.this,
											MusicClientActivity.class),
									PendingIntent.FLAG_UPDATE_CURRENT));
					manager.notify(1, noti1);
				case GlobalUtils.MSG_TAG_FINISHED:// 下载完成
					manager.cancel(0);
					break;

				default:
					break;
				}
			}
		};
	}

	/**
	 * 此方法在工作线程中运行，用于执行具体的任务逻辑
	 */
	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		// 从intent中获取要下载的音乐路径和保存路径
		String uri = intent.getStringExtra("uri");
		String path = intent.getStringExtra("path");
		// 判断是否已存在该文件
		File file = new File(path);
		if (file.exists()) {
			Message msg = Message.obtain(handler, GlobalUtils.MSG_TAG_EXISTS,
					path);
			msg.sendToTarget();
			return;
		}
		// 下载
		try {
			HttpEntity entity = HttpUtils.getEntity(uri, null,
					HttpUtils.METHOD_GET);
			fileLength = (int) HttpUtils.getLength(entity);
			musicName = file.getName();
			handler.sendEmptyMessage(GlobalUtils.MSG_TAG_STARTED);
			InputStream in = HttpUtils.getStream(entity);
			StreamUtils.save(in, path, handler);
			handler.sendEmptyMessage(GlobalUtils.MSG_TAG_FINISHED);
		} catch (ConnectTimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Message msg = Message.obtain(handler, GlobalUtils.MSG_TAG_FAILED,
					uri);
			msg.sendToTarget();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Message msg = Message.obtain(handler, GlobalUtils.MSG_TAG_FAILED,
					uri);
			msg.sendToTarget();
		}

	}

}
