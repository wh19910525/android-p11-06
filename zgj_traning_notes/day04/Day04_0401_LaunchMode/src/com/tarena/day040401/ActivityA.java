package com.tarena.day040401;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ActivityA extends Activity {
	private static final String TAG = "ActivityA";

	public void doClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btnStartActivityA:
			intent.setAction("com.tarena.action.ACTIVITYA");
			break;
		case R.id.btnStartActivityB:
			intent.setAction("com.tarena.action.ACTIVITYB");
			break;
		case R.id.btnStartActivity1:
			intent.setAction("com.tarena.action.ACTIVITY1");
			break;
		case R.id.btnStartActivity2:
			intent.setAction("com.tarena.action.ACTIVITY2");
			break;
		}
		startActivity(intent);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setTitle(this.toString());//把activiry设置为 程序名

		Log.i("info", TAG + ".onCreate()----taskId=" + getTaskId());
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i("info", TAG + ".onStart()----taskId=" + getTaskId());
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("info", TAG + ".onResume()----taskId=" + getTaskId());
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("info", TAG + ".onPause()----taskId=" + getTaskId());
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("info", TAG + ".onStop()----taskId=" + getTaskId());
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("info", TAG + ".onDestroy()----taskId=" + getTaskId());
	}
}