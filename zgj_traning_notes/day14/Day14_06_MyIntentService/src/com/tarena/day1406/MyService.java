package com.tarena.day1406;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyService extends MyIntentService {
//public class MyService extends IntentService {

	public MyService() {
		super("whworkThread");
	}
	
//	public MyService(String name){
//		super(name);
//	}

	@Override
	public void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		// ��ȡ��Ϣ��ִ������
		String path = intent.getStringExtra("path");
		if (path != null) {
			Log.i("info", "�������أ�" + path);
			for (int i = 1; i <= 5; i++) {
				Log.i("info", "thread " + Thread.currentThread().getName() + ",i=" + i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
