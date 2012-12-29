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
		sendStickyBroadcast(intent);//ʹ�����ַ�ʽ���͹㲥,�㲥��ע�����д�ڷ���֮������2����ʽ��ͨ�㲥������㲥�������ԣ��㲥�ķ��ͱ���д��ע��֮��

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