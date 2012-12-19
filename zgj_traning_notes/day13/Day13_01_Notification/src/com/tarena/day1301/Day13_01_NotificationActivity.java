package com.tarena.day1301;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

public class Day13_01_NotificationActivity extends Activity {
	private NotificationManager manager;

	public void doClick(View v) {
		final Notification noti = new Notification(R.drawable.ic_launcher, "œ¬‘ÿÃ· æ", System.currentTimeMillis());

		noti.defaults = Notification.DEFAULT_LIGHTS;
		noti.flags = Notification.FLAG_NO_CLEAR;
		noti.contentIntent = PendingIntent.getActivity(
				this,
				0, 
				new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com")),
				PendingIntent.FLAG_UPDATE_CURRENT);
		
		noti.contentView = new RemoteViews(getPackageName(), R.layout.layout_noti);//
		noti.contentView.setTextViewText(R.id.tvName, "yingbi.mp3");
		noti.contentView.setTextViewText(R.id.tvLoaded, "0KB");
		noti.contentView.setTextViewText(R.id.tvLength, "100KB");
		noti.contentView.setProgressBar(R.id.progressBar1, 100, 0, false);
		manager.notify(0, noti);

		new Thread() {
			public void run() {
				for (int i = 0; i <= 100; i += 10) {
					noti.contentView.setTextViewText(R.id.tvLoaded, i + "KB");
					noti.contentView.setProgressBar(R.id.progressBar1, 100, i, false);
					manager.notify(0, noti);
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				manager.cancel(0);
			};
		}.start();
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	}
}