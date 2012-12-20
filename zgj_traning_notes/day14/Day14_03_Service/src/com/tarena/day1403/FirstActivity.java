package com.tarena.day1403;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class FirstActivity extends Activity {
	
	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			Log.i("info", "FirstActivity.ServiceConnection.onServiceConnected");
		}
	};

	public void doClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btnFirstActivity:
			intent.setClass(this, FirstActivity.class);
			startActivity(intent);
			break;
		case R.id.btnSecondActivity:
			intent.setClass(this, SecondActivity.class);
			startActivity(intent);
			break;
		case R.id.btnbindService:
			intent.setClass(this, MyService.class);
			bindService(intent, conn, BIND_AUTO_CREATE);
			break;
		case R.id.btnUnbindService:
			unbindService(conn);
			break;
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
}