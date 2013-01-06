package com.tarena.day1804;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class Day18_04_BroadcastReceiverActivity extends Activity {
	
	private MyReceiver receiver;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
//		receiver = new MyReceiver();
//		IntentFilter filter = new IntentFilter();
//		filter.addAction("com.tarena.action.MYBROADCAST");
//		filter.addAction(Intent.ACTION_BOOT_COMPLETED);//�豸�������ʱ����(�翪��)
//		filter.addAction(Intent.ACTION_BATTERY_LOW);//�������Ǵ���
//
//		registerReceiver(receiver, filter);//��ָ���㲥����ע��
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
//		unregisterReceiver(receiver);
	}

	public void doClick(View v) {
		Intent intent = new Intent("com.tarena.action.MYBROADCAST");
		sendBroadcast(intent);
	}
}