package com.tarena.day1405;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

public class Day14_05_HandlerThreadActivity extends Activity {
	private HandlerThread thread;
	private Handler handler;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		thread = new HandlerThread("handlerThread");
		thread.start();
		Looper looper = thread.getLooper();

		handler = new Handler(looper) {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				Log.i("info", "handleMessage 运行在线程："
						+ Thread.currentThread().getName());
				Log.i("info", "msg.obj=" + msg.obj);
			}
		};

	}

	public void doClick(View v) {
		Message msg = Message.obtain();
		msg.obj = "这是一条消息，创建在线程：" + Thread.currentThread().getName();
		handler.sendMessage(msg);
		Log.i("info", "消息已发送，在线程：" + Thread.currentThread().getName());
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		thread.quit();
	}
}