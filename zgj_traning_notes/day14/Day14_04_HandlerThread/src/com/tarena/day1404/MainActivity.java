package com.tarena.day1404;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
	private Handler handler;
	private MyHandlerThread thread;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		thread = new MyHandlerThread();
		thread.start();

		Looper looper = thread.getLooper();
		handler = new Handler(looper) {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				Log.i("info", "���̣߳�" + Thread.currentThread().getName()
						+ "��,ִ��handleMessage������msg.obj=" + msg.obj);
			}
		};
	}

	public void doClick(View v) {
		Message msg = Message.obtain();
		msg.obj = "����һ����Ϣ�������ڣ�" + Thread.currentThread().getName();
		handler.sendMessage(msg);
		Log.i("info", "��Ϣ�ѷ��ͣ����̣߳�" + Thread.currentThread().getName());
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		thread.quit();
	}
}