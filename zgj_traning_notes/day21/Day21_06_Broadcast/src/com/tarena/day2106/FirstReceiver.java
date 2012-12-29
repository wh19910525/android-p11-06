package com.tarena.day2106;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class FirstReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getAction();
		if ("com.tarena.action.Test_Broadcast".equals(action)) {
			Log.i("info", "FirstReceiver.onReceive --->action=" + action);
		}

		// if(isOrderedBroadcast()){
		// abortBroadcast();
		// }

		if (isOrderedBroadcast()) {
			setResultData("这是在FirstReceiver中追加的数据");
		}
	}

}
