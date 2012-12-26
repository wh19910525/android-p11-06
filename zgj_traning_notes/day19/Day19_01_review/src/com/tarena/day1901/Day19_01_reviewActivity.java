package com.tarena.day1901;

import java.io.UnsupportedEncodingException;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;

public class Day19_01_reviewActivity extends Activity {
	
	private MyReceiver receiver;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		receiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.tarena.action.NEW_BROADCAST");
		filter.addAction(Intent.ACTION_BOOT_COMPLETED);

		registerReceiver(receiver, filter);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}

	public void doClick(View v) {
		Intent intent = new Intent("com.tarena.action.NEW_BROADCAST");
		sendBroadcast(intent);
		
		String abc = "sdfasdfa£»°®ºÃ";
		
	}
}