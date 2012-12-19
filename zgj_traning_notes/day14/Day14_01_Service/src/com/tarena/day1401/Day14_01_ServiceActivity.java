package com.tarena.day1401;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Day14_01_ServiceActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void doClick(View v) {
		Intent intent = new Intent(this, MyService.class);
		startService(intent);//
	}
}