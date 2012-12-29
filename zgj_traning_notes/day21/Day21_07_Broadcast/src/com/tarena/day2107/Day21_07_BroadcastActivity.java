package com.tarena.day2107;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class Day21_07_BroadcastActivity extends Activity {
	private MyReceiver receiver;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Intent intent = new Intent("com.tarena.action.STICKY_BROADCAST");
		// sendBroadcast(intent);
		sendStickyBroadcast(intent);//使用这种方式发送广播,广播的注册可以写在发送之后，其它2种形式普通广播和有序广播都不可以，广播的发送必须写在注册之后

		receiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.tarena.action.STICKY_BROADCAST");
		registerReceiver(receiver, filter);

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
		removeStickyBroadcast(new Intent("com.tarena.action.STICKY_BROADCAST"));
	}
}