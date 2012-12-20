package com.tarena.day1406;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public abstract class MyIntentService extends Service {
	
	private HandlerThread thread;
	private Handler handler;
	private String name;
	public abstract void onHandleIntent(Intent intent);//����һ�� ���󷽷���˵�� ����� ��Ҫ ������ �̳�
	
	public MyIntentService(String name)
	{
		this.name = name;
	}
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("info", "MyIntentService.onCreate");
		thread = new HandlerThread(name);
		thread.start();

		Looper looper = thread.getLooper();
		handler = new Handler(looper) {
			/**
			 * �˷��������ڹ����߳� workThread��
			 */
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				// ִ������
				onHandleIntent((Intent) msg.obj);
				// ֹͣ��������
				stopSelf(msg.arg1);
				Log.i("info", "stopSelf(" + msg.arg1 + ")");
			}
		};
	}


	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("info", "MyIntentService.onStartCommand(" + startId + ")");
		// ������Ϣ����
		Message msg = Message.obtain();
		msg.what = 0;
		msg.arg1 = startId;
		msg.obj = intent;

		// ������Ϣ�������߳�
		handler.sendMessage(msg);

		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.i("info", "MyIntentService.onDestroy");
		super.onDestroy();
		thread.quit();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
