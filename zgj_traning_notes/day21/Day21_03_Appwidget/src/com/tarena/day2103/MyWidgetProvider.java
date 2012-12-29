package com.tarena.day2103;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

public class MyWidgetProvider extends AppWidgetProvider {
	@Override
	public void onReceive(Context context, Intent intent) {
		
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
		if ("com.tarena.action.UPDATE_WIDGET".equals(intent.getAction())) {

			// ��ȡnumb
			int numb = intent.getIntExtra("numb", 0);
//			Log.i("wanghai", "" + numb);
			// ����ֺ���
			String min = format(numb / 60);
			String sec = format(numb % 60);
			// �����������
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_appwidget);
			views.setTextViewText(R.id.tv1, min);
			views.setTextViewText(R.id.tv2, sec);

			AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
			ComponentName provider = new ComponentName(context, MyWidgetProvider.class);
			appWidgetManager.updateAppWidget(provider, views);
		}
	}

	private String format(int numb) {
		return numb < 10 ? "0" + numb : "" + numb;
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		// ��ʼ���������
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_appwidget);
		views.setTextViewText(R.id.tv1, "HA");
		views.setTextViewText(R.id.tv2, "HA");
		PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(
				Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com")),
				PendingIntent.FLAG_UPDATE_CURRENT);
		views.setOnClickPendingIntent(R.id.btnTest, pi);

		appWidgetManager.updateAppWidget(appWidgetIds, views);

		// ����Service����ʱ�������
		Intent intent = new Intent(context, MyService.class);
		context.startService(intent);
	}
	
}
