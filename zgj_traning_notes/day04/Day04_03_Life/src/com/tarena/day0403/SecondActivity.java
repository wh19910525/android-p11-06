package com.tarena.day0403;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SecondActivity extends Activity {
	
	private static final String TAG = "SecondActivity";

	public void doClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btnStartActivity:
			intent.setClass(this, FirstActivity.class);
			break;

		case R.id.btnSecondActivity:
			intent.setClass(this, SecondActivity.class);
			break;
		}

		startActivity(intent);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.i("info", TAG + ".onCreate()"+getTaskId());
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i("info", TAG + ".onStart()"+getTaskId());
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.i("info", TAG + ".onReStart()"+getTaskId());
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("info", TAG + ".onResume()"+getTaskId());
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("info", TAG + ".onPause()"+getTaskId());
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("info", TAG + ".onStop()"+getTaskId());
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("info", TAG + ".onDestroy()"+getTaskId());
	}

}
