package com.tarena.day2301;

import java.util.ArrayList;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
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
				if (getResultCode() == RESULT_OK) {//如果 发送短信成功，返回 这个 值
					
					// 获取发送的短消息信息
					String number = intent.getStringExtra("number");
					String content = intent.getStringExtra("content");
					Log.i("info", "number:" + number);
					Log.i("info", "content:" + content);

					// 保存到内容提供器，供其他程序用，这段代码也可以删除掉;
					ContentResolver cr = getContentResolver();
					ContentValues values = new ContentValues();
					values.put("address", number);
					values.put("body", content);
					values.put("date", System.currentTimeMillis());
					
					values.put("type", 2);
					cr.insert(Uri.parse("content://sms"), values);
					
					//以下 这一句 可以代替 上边的两句，2代表  发送信息
					// cr.insert(Uri.parse("content://sms/sent"), values)
				}
			}
		}
	}

	public void send(View v) {
		// 获取输入
		String number = etNumber.getText().toString();
		String content = etContent.getText().toString();
		
		// 发送短消息 有两种方法：
		//方法一：
		Intent intent = new Intent("com.tarena.action.SNED_SMS");//
		intent.putExtra("number", number);
		intent.putExtra("content", content);
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);//

		Log.i("info", "pi=" + pi);
		manager.sendTextMessage(number, null, content, pi, null);//
		
		//方法二：
		//如果一条短信 内容有很多 用 下面两句 可以把 短信 分隔成多条来发；
//		ArrayList<String> parts = manager.divideMessage(content);
//		manager.sendMultipartTextMessage(number, null, parts, null, null);//
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
		manager = SmsManager.getDefault();//

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