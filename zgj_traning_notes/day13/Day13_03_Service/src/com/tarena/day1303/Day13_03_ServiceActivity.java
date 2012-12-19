package com.tarena.day1303;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Day13_03_ServiceActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void doClick(View v) {
		Intent intent = new Intent(this, MyService.class);

		switch (v.getId()) {
		case R.id.btnStartService:
			startService(intent);//
			break;

		case R.id.btnStopService:
			stopService(intent);//
			break;
		}
	}
}