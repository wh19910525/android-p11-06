package com.tarena.day1403;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
	public class MyBinder extends Binder {

	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("info", "MyService.onCreate");
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i("info", "MyService.onBind");
		return new MyBinder();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.i("info", "MyService.onUnbind");
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}

	@Override
	public void onDestroy() {
		Log.i("info", "MyService.onDestroy");
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
