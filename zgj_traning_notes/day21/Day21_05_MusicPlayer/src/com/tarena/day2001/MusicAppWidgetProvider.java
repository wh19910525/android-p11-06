package com.tarena.day2001;

import com.tarena.entity.Music;
import com.tarena.utils.GlobalUtils;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class MusicAppWidgetProvider extends AppWidgetProvider {
	
	@Override
	/**
	 ��������Activity��Service�Ĺ㲥���������沼�ֵ�����*/
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);
		String action = intent.getAction();
		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.layout_provider);
		if (GlobalUtils.ACTION_CURRENT_MUSIC_CHANGED.equals(action)) {
			Music m = (Music) intent
					.getSerializableExtra(GlobalUtils.EXTRA_CURRENT_MUSIC);
			if (m != null) {
				views.setTextViewText(R.id.tvMusicName_Provider, m.getName());
				views.setTextViewText(R.id.tvArtist_Provider, m.getArtist());
				update(context, views);
			}
		} else if (GlobalUtils.ACTION_SERVICE_STOP.equals(action)) {
			views.setTextViewText(R.id.tvMusicName_Provider, "������");
			views.setTextViewText(R.id.tvArtist_Provider, "����");
			update(context, views);
		} else if (GlobalUtils.ACTION_RESPONSE.equals(action)) {
			int play_state = intent.getIntExtra(GlobalUtils.EXTRA_PLAY_STATE,
					GlobalUtils.OTHERS);
			if (play_state != GlobalUtils.OTHERS) {
				Music m = (Music) intent
						.getSerializableExtra(GlobalUtils.EXTRA_CURRENT_MUSIC);
				if (m != null) {
					views.setTextViewText(R.id.tvMusicName_Provider,
							m.getName());
					views.setTextViewText(R.id.tvArtist_Provider, m.getArtist());
					update(context, views);
				}
			}
		}
	}

	private static void update(Context context, RemoteViews views) {
		AppWidgetManager manager = AppWidgetManager.getInstance(context);
		ComponentName provider = new ComponentName(context,
				MusicAppWidgetProvider.class);
		manager.updateAppWidget(provider, views);
	}

	@Override
	public void onEnabled(Context context) {
		// TODO Auto-generated method stub
		super.onEnabled(context);
		Intent intent = new Intent(context, MusicService.class);
		context.startService(intent);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		/**��Զ�̲��ֵİ�ť���������,��������ͼ*/
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_provider);
		
		views.setOnClickPendingIntent(R.id.btnPlay_Provider, 
				PendingIntent.getBroadcast(context, 0, 
						new Intent(GlobalUtils.ACTION_PLAY),
						PendingIntent.FLAG_UPDATE_CURRENT));//����

		views.setOnClickPendingIntent(R.id.btnPrevious_Provider, 
				PendingIntent.getBroadcast(context, 1, new Intent(
						GlobalUtils.ACTION_PREVIOUS),
						PendingIntent.FLAG_UPDATE_CURRENT));//��һ��

		views.setOnClickPendingIntent(R.id.btnPause_Provider, 
				PendingIntent.getBroadcast(context, 2, new Intent(GlobalUtils.ACTION_PAUSE),
						PendingIntent.FLAG_UPDATE_CURRENT));//��ͣ

		views.setOnClickPendingIntent(R.id.btnNext_Provider, 
				PendingIntent.getBroadcast(context, 3, new Intent(GlobalUtils.ACTION_NEXT),
						PendingIntent.FLAG_UPDATE_CURRENT));//��һ��

		views.setOnClickPendingIntent(R.id.btnList_Provider, 
				PendingIntent.getActivity(context, 4, new Intent(context,
						Day20_01_MusicPlayerActivity.class),
						PendingIntent.FLAG_UPDATE_CURRENT));//�����б�

		appWidgetManager.updateAppWidget(appWidgetIds, views);

		Intent intent = new Intent(GlobalUtils.ACTION_REQUEST);
		context.sendBroadcast(intent);
	}
}
