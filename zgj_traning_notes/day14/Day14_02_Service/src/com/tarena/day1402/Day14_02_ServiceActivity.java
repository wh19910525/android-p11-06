package com.tarena.day1402;

import com.tarena.day1402.MusicPlayService.MyBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;

public class Day14_02_ServiceActivity extends Activity {

	private MusicPlayService service;
	
	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			// TODO Auto-generated method stub
			Log.i("info", "ServiceConnection.onServiceConnected()");
			service = ((MyBinder) binder).getService();//
		}
	};

	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btnPlay:
			service.play();
			break;
		case R.id.btnPause:
			service.pause();
			break;
		case R.id.btnPrevious:
			service.previous();
			break;
		case R.id.btnNext:
			service.next();
			break;
		case R.id.btnSeekTo:
			service.seekTo(12000);
			break;
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 绑定到服务
		Intent intent = new Intent(this, MusicPlayService.class);
		bindService(intent, conn, BIND_AUTO_CREATE);//
		startService(intent);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unbindService(conn);//
		Log.i("wang","activity.onDestroy");
	}
}