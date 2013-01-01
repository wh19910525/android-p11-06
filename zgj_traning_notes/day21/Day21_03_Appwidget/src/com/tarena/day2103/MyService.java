package com.tarena.day2103;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

public class MyService extends Service {
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		
		final ComponentName provider = new ComponentName(MyService.this, MyWidgetProvider.class);
		final AppWidgetManager manager = AppWidgetManager.getInstance(MyService.this);
		
		new Thread() {
			public void run() {
				try {
					sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				for (int i = 1200; i >= 0; i--) {
					
					//发送广播，更新界面
					Intent intent = new Intent("com.tarena.action.UPDATE_WIDGET");
					intent.putExtra("numb", i);
					sendBroadcast(intent);

					// 睡眠1s
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
		}.start();
	}

	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
