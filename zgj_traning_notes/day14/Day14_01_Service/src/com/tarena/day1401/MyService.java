package com.tarena.day1401;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("info", "onCreate");

	}

	@Override
	public int onStartCommand(Intent intent, int flags,final int startId) {
		// TODO Auto-generated method stub
		Log.i("info", "onStartCommand");
		new Thread() {
			public void run() {
				for (int i = 1; i <= 3; i++) {
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Log.i("info", "in thread:" + getName() + ":i=" + i);
				}
				stopSelf(startId);// 结束当前服务
			};
		}.start();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("info", "onDestroy");
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
