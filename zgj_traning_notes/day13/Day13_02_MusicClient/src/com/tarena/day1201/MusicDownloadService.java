package com.tarena.day1201;

import com.tarena.utils.MusicDownloadUtils;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MusicDownloadService extends Service {
	
	MusicDownloadUtils utils;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		utils = new MusicDownloadUtils(this);//
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		String uri = intent.getStringExtra("uri");
		String path = intent.getStringExtra("path");
		utils.download(uri, path);//
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
