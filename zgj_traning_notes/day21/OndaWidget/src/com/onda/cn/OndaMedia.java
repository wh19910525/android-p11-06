package com.onda.cn;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class OndaMedia extends AppWidgetProvider {

	static final String TAG = "OndaMedia";

	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		Log.i(TAG, "onUpdate");

		RemoteViews media_view = new RemoteViews(context.getPackageName(), R.layout.media);

		final int N = appWidgetIds.length;
		for (int i = 0; i < N; i++) {//个人 认为 这个 for循环 没有 什么意义 因为 N的 值 一直是1
			int appWidgetId = appWidgetIds[i];

			// ===select clock
			Intent clock_intent = new Intent();
			ComponentName clockComponentName = new ComponentName(
					"com.android.deskclock", "com.android.deskclock.DeskClock");
			clock_intent.setComponent(clockComponentName);
			PendingIntent clockPendingIntent = PendingIntent.getActivity(
					context, 0, clock_intent, 0);
			media_view.setOnClickPendingIntent(R.id.btn_clock, clockPendingIntent);

			// ====select picture

			Intent gallerys_intent = new Intent();    //wanghai  .
			ComponentName galleryComponentName = new ComponentName(
					"com.android.gallery3d", "com.android.gallery3d.app.Gallery");
			gallerys_intent.setComponent(galleryComponentName);
			PendingIntent gallerysPendingIntent = PendingIntent.getActivity(
					context, 0, gallerys_intent, 0);
			media_view.setOnClickPendingIntent(R.id.btn_gallery, gallerysPendingIntent);

			// ===select calendar

			Intent calendar_intent = new Intent();

			ComponentName calendarComponentName = new ComponentName(
					"com.android.calendar", "com.android.calendar.AllInOneActivity");
			calendar_intent.setComponent(calendarComponentName);
			PendingIntent brightPendingIntent = PendingIntent.getActivity(
					context, 0, calendar_intent, 0);
			media_view.setOnClickPendingIntent(R.id.btn_calendar, brightPendingIntent);

			// ===select online video
			Intent online_video_intent = new Intent();
			ComponentName onlineComponentName = new ComponentName("com.pplive.androidpad", "com.pplive.androidpad.ui.FirstActivity");
			online_video_intent.setComponent(onlineComponentName);
					
			PendingIntent online_videoPendingIntent = PendingIntent.getActivity(context, 0, online_video_intent, 0);
			media_view.setOnClickPendingIntent(R.id.btn_video, online_videoPendingIntent);


			// ===select QQ

			Intent qq_intent = new Intent();

			ComponentName qqComponentName = new ComponentName(
					"com.tencent.minihd.qq",
					"com.tencent.qq.SplashActivity");
			qq_intent.setComponent(qqComponentName);
			PendingIntent qqPendingIntent = PendingIntent.getActivity(
					context, 0, qq_intent, 0);
			media_view.setOnClickPendingIntent(R.id.btn_qq, qqPendingIntent);
					
					
			// ====update
			appWidgetManager.updateAppWidget(appWidgetId, media_view);

		}

	}

	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
	}

	public void onEnabled(Context context) {
		Log.i(TAG, "onEnabled");
	}
}
