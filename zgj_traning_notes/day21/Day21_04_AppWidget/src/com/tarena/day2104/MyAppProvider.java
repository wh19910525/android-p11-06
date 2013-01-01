package com.tarena.day2104;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyAppProvider extends AppWidgetProvider {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);

		if ("com.tarena.action.CONFIGURE_OK".equals(intent.getAction())) {
			// Æô¶¯Service
			Log.i("info", "numb=" + intent.getIntExtra("numb", 0));
			Intent service = new Intent(context, MyService.class);
			service.putExtra("numb", intent.getIntExtra("numb", 0));
			context.startService(service);
		}
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		Log.i("info", "onUpdate");

	}
}
