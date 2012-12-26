package com.tarena.day1804;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("info", "onReceive--2");
		String action = intent.getAction();
		/**
		 注意：在非上下文组件内,不建议使用对话框和Toast弹出信息
		 可以发通知进行信息的显示*/
		
		if ("com.tarena.action.MYBROADCAST".equals(action)) {
			Toast.makeText(context, "收到自定义广播", 3000).show();
			// Intent target = new Intent(context, Day18_04_BroadcastReceiverActivity.class);
			
			/**设置新起动的Activity放到新的任务栈里,但是根据广播的生命周期,一般非上下文组件不建议启动新的Activity,*/
			// target.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// context.startActivity(target);
		} else if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {

		} else if (Intent.ACTION_BATTERY_LOW.equals(action)) {

		}

	}

}
