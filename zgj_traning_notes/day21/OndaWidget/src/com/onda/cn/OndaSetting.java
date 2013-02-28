package com.onda.cn;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class OndaSetting extends AppWidgetProvider {

	static final String TAG = "OndaSetting";

	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		Log.i(TAG, "onUpdate");

		RemoteViews settings_view = new RemoteViews(context.getPackageName(), R.layout.setting);

		final int N = appWidgetIds.length;
		for (int i = 0; i < N; i++) {
			int appWidgetId = appWidgetIds[i];

			// ====================select wifi setting
			Intent wifi_intent = new Intent();

			ComponentName wifiComponentName = new ComponentName("com.android.settings", "com.android.settings.Settings$WifiSettingsActivity");
			wifi_intent.setComponent(wifiComponentName);
			PendingIntent wifiPendingIntent = PendingIntent.getActivity(
					context, 0, wifi_intent, 0);
			settings_view.setOnClickPendingIntent(R.id.but_wifi, wifiPendingIntent);

			// ====================select settings

			Intent settings_intent = new Intent();
			ComponentName settingComponentName = new ComponentName(
					"com.android.settings", "com.android.settings.Settings");
			settings_intent.setComponent(settingComponentName);
			PendingIntent settingsPendingIntent = PendingIntent.getActivity(context, 0, settings_intent, 0);
			settings_view.setOnClickPendingIntent(R.id.but_setting, settingsPendingIntent);

			// ====================select brightness settings

			Intent brightness_intent = new Intent();
			ComponentName brightComponentName = new ComponentName(
					"com.android.settings",
					"com.android.settings.Settings$DisplaySettingsActivity");
			brightness_intent.setComponent(brightComponentName);
			PendingIntent brightPendingIntent = PendingIntent.getActivity(
					context, 0, brightness_intent, 0);
			settings_view.setOnClickPendingIntent(R.id.but_bright, brightPendingIntent);

			// ====================select app management

			Intent app_intent = new Intent();				//not work now. I will fix later
			ComponentName appComponentName = new ComponentName(
					"com.gsoft.appinstall", "com.gsoft.appinstall.main");
			app_intent.setComponent(appComponentName);
			PendingIntent appPendingIntent = PendingIntent.getActivity(context,
					0, app_intent, 0);
			settings_view.setOnClickPendingIntent(R.id.but_app, appPendingIntent);
			
			
			// ====================select shutdown
			Intent shutdown_intent = new Intent();
			ComponentName shutdownComponentName = new ComponentName(
					"com.onda.cn", "com.onda.cn.ShutDownNow");
			shutdown_intent.setComponent(shutdownComponentName);
			PendingIntent shutdownPendingIntent = PendingIntent.getActivity(
					context, 0, shutdown_intent, 0);
			settings_view.setOnClickPendingIntent(R.id.but_shutdow, shutdownPendingIntent);

			// =========================================update
			appWidgetManager.updateAppWidget(appWidgetId, settings_view);

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