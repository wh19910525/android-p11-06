package com.tarena.day2301;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Day23_01_SmsActivity extends Activity {
	
	private EditText etNumber, etContent;
	private SmsManager manager;
	private InnerReceiver receiver;

	private void setupView() {
		etNumber = (EditText) findViewById(R.id.etNumber);
		etContent = (EditText) findViewById(R.id.etContent);
	}

	private class InnerReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			if ("com.tarena.action.SNED_SMS".equals(action)) {
				
				String number = intent.getStringExtra("number");
				String content = intent.getStringExtra("content");
				Log.i("info", "number:" + number);
				Log.i("info", "content:" + content);
			}
		}
	}

	public void send(View v) {
		// 获取输入
		String number = etNumber.getText().toString();
		String content = etContent.getText().toString();

		Intent intent = new Intent("com.tarena.action.SNED_SMS");
		intent.putExtra("number", number);
		intent.putExtra("content", content);
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

		Log.i("info", "pi=" + pi);
		// 发送短消息
		manager.sendTextMessage(number, null, content, pi, null);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
		manager = SmsManager.getDefault();

		receiver = new InnerReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.tarena.action.SNED_SMS");
		registerReceiver(receiver, filter);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}
}