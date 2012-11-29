package com.tarena.day0402;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Day04_02_IntentActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void doClick(View v) {
		
		Intent intent = new Intent();
		
		switch (v.getId()) {
		case R.id.btnDial:
			intent.setAction(Intent.ACTION_DIAL);
			intent.setData(Uri.parse("tel:13141755600"));
			break;
		case R.id.btnCall:
			intent.setAction(Intent.ACTION_CALL);//打电话 需要 在 AndroidManifest.xml 里 声明android.permission.CALL_PHONE 权限
			intent.setData(Uri.parse("tel:13241755600"));
			break;
		case R.id.btnSendSms:
			intent.setAction(Intent.ACTION_SENDTO);
			intent.setData(Uri.parse("smsto:13341755600"));
			intent.putExtra("sms_body", "hello");//
			break;
		case R.id.btnBrowser:
			intent.setAction(Intent.ACTION_VIEW);
			intent.setData(Uri.parse("http://www.163.com"));
			break;
		}
		startActivity(intent);
	}
}