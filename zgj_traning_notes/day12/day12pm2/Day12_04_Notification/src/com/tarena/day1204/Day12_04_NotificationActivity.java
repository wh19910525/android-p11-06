package com.tarena.day1204;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class Day12_04_NotificationActivity extends Activity {
	
	private NotificationManager manager;

	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btnSend:
			// 创建通知对象
			//Notification noti = new Notification(R.drawable.ic_launcher, "通知", System.currentTimeMillis());
			Notification noti = new Notification();//以下四句 等于上边 一句
			noti.icon = R.drawable.ic_launcher;
			noti.tickerText = "通知wh";
			noti.when = System.currentTimeMillis();
			
			PendingIntent contentIntent = PendingIntent.getActivity(//当用户点击Notification之后，由系统发出一条 启动Activity 的 Intent 
					this,
					0,
					new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com")),
					PendingIntent.FLAG_UPDATE_CURRENT);
			
			noti.setLatestEventInfo(this, "重要通知", "这是一个很重要的通知。", contentIntent);//当我们创造了一个Notification对象时，一定要为其设置setLatestEventInfo()方法，否则程序会报错
			noti.defaults = Notification.DEFAULT_LIGHTS;//设置 提示方式
			noti.flags = Notification.FLAG_NO_CLEAR;//设置状态标志
			
			// 发布通知
			manager.notify(0, noti);
			break;

		case R.id.btnCancel:
			manager.cancel(0);
			break;
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
	}
}