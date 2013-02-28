package com.onda.cn;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

public class OndaHome extends AppWidgetProvider {

	static final String TAG = "OndaHome";

	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		Log.i(TAG, "onUpdate");
		
		RemoteViews home_view = new RemoteViews(context.getPackageName(), R.layout.home);

		final int N = appWidgetIds.length;
		for (int i = 0; i < N; i++) {
			int appWidgetId = appWidgetIds[i];

			// ====================select browser
			Intent browser_intent = new Intent();
			browser_intent.setAction("android.intent.action.VIEW");//
			Uri content_url = Uri.parse("http://www.google.com.hk");
			browser_intent.setData(content_url);//

			PendingIntent browserPendingIntent = PendingIntent.getActivity(
					context, 0, browser_intent, 0);
			home_view.setOnClickPendingIntent(R.id.btn_browser, browserPendingIntent);

			// =====================select camera
			Log.i("wanghai", "wanghai-test");
			Intent camera_tntent = new Intent();
			ComponentName camera_componentname = new ComponentName(
					"com.android.gallery3d", "com.android.camera.CameraLauncher");
			camera_tntent.setComponent(camera_componentname);//
		//	camera_tntent.setAction("android.intent.action.VIEW");// wanghai: must add this line!
			
			PendingIntent cameraPendingIntent = PendingIntent.getActivity(
					context, 0, camera_tntent, 0);
			home_view.setOnClickPendingIntent(R.id.btn_camera, cameraPendingIntent);
			
			// =====================select help
			Intent help_intent = new Intent();
			ComponentName help_componentname = new ComponentName("com.onda.mid", "com.onda.mid.MainActivity");
			help_intent.setComponent(help_componentname);
					
			PendingIntent helpPendingIntent = PendingIntent.getActivity(
					context, 0, help_intent, 0);
			home_view.setOnClickPendingIntent(R.id.btn_help, helpPendingIntent);
			

			// =======================select music
			Intent music_tntent = new Intent();
			ComponentName music_componentname = new ComponentName("com.android.music", "com.android.music.MusicBrowserActivity");
			music_tntent.setComponent(music_componentname);
			PendingIntent musicPendingIntent = PendingIntent.getActivity(
					context, 0, music_tntent, 0);
			home_view.setOnClickPendingIntent(R.id.btn_music, musicPendingIntent);
			
			// =====================select movie

			Intent movie_intent = new Intent(); //wanghai
			ComponentName movie_componentname = new ComponentName("com.farcore.videoplayer", "com.farcore.videoplayer.FileList");
			movie_intent.setComponent(movie_componentname);
			PendingIntent moviePendingIntent = PendingIntent.getActivity(
					context, 0, movie_intent, 0);
			home_view.setOnClickPendingIntent(R.id.btn_movie,moviePendingIntent);
						
			// ====== ===============select download

			Intent download_tntent = new Intent(); //wanghai
			ComponentName download_componentname = new ComponentName("com.onda.gfan", "com.onda.gfan.ui.SplashActivity");
			download_tntent.setComponent(download_componentname);
			PendingIntent downloadPendingIntent = PendingIntent.getActivity(
					context, 0, download_tntent, 0);
			home_view.setOnClickPendingIntent(R.id.btn_download, downloadPendingIntent);
					
			//=========================================update
			appWidgetManager.updateAppWidget(appWidgetId, home_view);

		}
	}

	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		Log.i(TAG, "onReceive");
	}

	public void onEnabled(Context context) {
		Log.i(TAG, "onEnabled");
	}
}