package com.tarena.day2206;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class Day22_06_SmsActivity extends Activity {
	
	private EditText etNumber, etContent;

	private void setupView() {
		etNumber = (EditText) findViewById(R.id.etNumber);
		etContent = (EditText) findViewById(R.id.etContent);
	}

	public void send(View v) {
		String number = etNumber.getText().toString();
		String content = etContent.getText().toString();

		Intent intent = new Intent(Intent.ACTION_SENDTO);//
		intent.setData(Uri.parse("smsto:" + number));//
		intent.putExtra("sms_body", content);//
		startActivity(intent);//经过 以上 三步 将会启动 系统的发送短信 界面；
		Log.i("wh", "发送短信");
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}
}