package com.tarena.day1402;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MusicPlayService extends Service {
	
	public class MyBinder extends Binder {
		
		public MusicPlayService getService() {
			return MusicPlayService.this;//
		}
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		Log.i("info", "MusicPlayService.onCreate()");
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.i("info", "MusicPlayService.onBind()");
		return new MyBinder();//
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log.i("info", "MusicPlayService.onUnbind()");
		// TODO Auto-generated method stub
		return super.onUnbind(intent);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i("info", "MusicPlayService.onStartCommand");
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("info", "MusicPlayService.onDestroy()");
	}

	public void play() {
		Log.i("info", "MusicPlayService.play()");
	}

	public void pause() {
		Log.i("info", "MusicPlayService.pause()");
	}

	public void previous() {
		Log.i("info", "MusicPlayService.previous()");
	}

	public void next() {
		Log.i("info", "MusicPlayService.next()");
	}

	public void seekTo(int position) {
		Log.i("info", "MusicPlayService.seekTo(" + position + ")");
	}

}
