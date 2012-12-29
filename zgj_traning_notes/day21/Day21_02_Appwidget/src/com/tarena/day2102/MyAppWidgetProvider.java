package com.tarena.day2102;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;

public class MyAppWidgetProvider extends AppWidgetProvider {
	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		
		// TODO Auto-generated method stub
		super.onUpdate(context, appWidgetManager, appWidgetIds);

		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_appwidget);
		views.setTextViewText(R.id.tv1, "HA");
		views.setTextViewText(R.id.tv2, "HA");
		
		PendingIntent pi = PendingIntent.getActivity(context, 0, 
				new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.163.com")),
				PendingIntent.FLAG_UPDATE_CURRENT);
		views.setOnClickPendingIntent(R.id.btnTest, pi);
		
		//方法一：
//		appWidgetManager.updateAppWidget(appWidgetIds, views);

		//方法三：
		ComponentName provider = new ComponentName(context, MyAppWidgetProvider.class);
		AppWidgetManager am = AppWidgetManager.getInstance(context);
		am.updateAppWidget(provider, views);
		
		//方法二：
//		ComponentName provider = new ComponentName(context, MyAppWidgetProvider.class);
//		appWidgetManager.updateAppWidget(provider, views);


	}
}
