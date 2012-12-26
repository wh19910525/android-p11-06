package com.tarena.day1901;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getAction();
		if ("com.tarena.action.NEW_BROADCAST".equals(action)) {
			Log.i("info", "自定义广播：" + action);
			
		} else if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {
			Intent service = new Intent(context, MyService.class);
			context.startService(service);
		}
	}

}
