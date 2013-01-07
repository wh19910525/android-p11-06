package com.tarena.day2303;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TelReceiver extends BroadcastReceiver {

	/**
	 * 此类用于测试模拟器电话的呼出*/
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		/**当有电话打进来的时候, 系统会自动发出android.intent.action.NEW_OUTGOING_CALL广播, 
		 * 此广播接收器用于接收 打进来的号码,不管是禁止的还是普通号码
		 * 只是如果是本项目的指定号码,虚拟器不会接收到而已,但是系统还是会发出广播*/
		
		String action = intent.getAction();
		if (Intent.ACTION_NEW_OUTGOING_CALL.equals(action)) {
			// 获取呼出号码
			// String number = getResultData();
			String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
			Log.i("info", "number:" + number);

			if ("110".equals(number)) {
				
		/**注意电话的拨打不像发短信或其他普通广播一样,即使此广播为有序广播,调用abortBroadcast()方法
		   也不会是阻断广播的继续传播*/
				
//				 if (isOrderedBroadcast()) {
//				 Log.i("info", "isOrderedBroadcast");
//				 abortBroadcast();
				setResultData(null);//此方法可以使指定的号码拨不出去
//				 }
			}
		}
	}

}
