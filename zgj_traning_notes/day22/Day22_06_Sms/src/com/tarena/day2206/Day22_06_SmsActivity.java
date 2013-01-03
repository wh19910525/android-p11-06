package com.tarena.day2206;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**本类是验证通过代码的形式给模拟器发短信,并启动模拟器自身系统的Activity接收短信*/

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
		startActivity(intent);//将会启动那个activity
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