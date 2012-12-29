package com.tarena.day2106;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class Day_21_06_BroadcastActivity extends Activity {
	private ThirdReceiver receiver;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		receiver = new ThirdReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.tarena.action.Test_Broadcast");
		filter.setPriority(800);
		registerReceiver(receiver, filter);
	}

	public void doClick(View v) {
		Intent intent = new Intent("com.tarena.action.Test_Broadcast");
		// sendBroadcast(intent);
		sendOrderedBroadcast(intent, null);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}
}