package com.tarena.day1204;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Day12_04_NotificationActivity extends Activity {
	private NotificationManager manager;

	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btnSend:
			// 创建通知对象
			Notification noti = new Notification(R.drawable.ic_launcher, "通知",
					System.currentTimeMillis());
			PendingIntent contentIntent = PendingIntent.getActivity(
					this,
					0,
					new Intent(Intent.ACTION_VIEW, Uri
							.parse("http://www.baidu.com")),
					PendingIntent.FLAG_UPDATE_CURRENT);
			noti.setLatestEventInfo(this, "重要通知", "这是一个很重要的通知。", contentIntent);
			noti.defaults = Notification.DEFAULT_LIGHTS;
			noti.flags = Notification.FLAG_NO_CLEAR;
			
			// 发布通知
			manager.notify(0, noti);
			break;

		case R.id.btnCancel:
			manager.cancel(0);
			break;
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}
}