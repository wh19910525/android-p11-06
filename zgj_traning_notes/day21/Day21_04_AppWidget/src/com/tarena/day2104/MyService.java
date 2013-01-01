package com.tarena.day2104;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class MyService extends Service {
	
	private Thread thread;
	private int numb;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		final ComponentName provider = new ComponentName(this, MyAppProvider.class);
		final AppWidgetManager manager = AppWidgetManager.getInstance(this);

		thread = new Thread() {
			@Override
			public void run() {
				for (int i = numb; i >= 0; i--) {
					String min = format(i / 60);
					String sec = format(i % 60);

					RemoteViews views = new RemoteViews(getPackageName(), R.layout.layout_appwidget);
					views.setTextViewText(R.id.tv1, min);
					views.setTextViewText(R.id.tv2, sec);

					manager.updateAppWidget(provider, views);

					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
	}

	private String format(int numb) {
		return numb < 10 ? "0" + numb : "" + numb;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		numb = intent.getIntExtra("numb", 0);

		try {
			thread.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
}
