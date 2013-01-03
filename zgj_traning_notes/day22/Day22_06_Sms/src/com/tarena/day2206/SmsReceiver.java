package com.tarena.day2206;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {

	/**本类是验证通过模拟器直接发信息给模拟器,当模拟器收到短息后，系统会发广播，通过本广播接收器接收对应的广播,
	 * 并读取短信
	 * 当发送的号码为5556，本广播自动会销毁广播的继续下传，这样模拟器将不会收到短信*/
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getAction();
		Log.i("wh", "接收短信");
		if ("android.provider.Telephony.SMS_RECEIVED".equals(action)) {
			// 读取短信息内容
			Object[] pdus = (Object[]) intent.getExtras().get("pdus");
			if (pdus != null) {
				for (Object pdu : pdus) {
					SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdu);
					String number = msg.getDisplayOriginatingAddress();
					Log.i("info", "===zgj"+number);
					Log.i("info", "===zgj"+msg.getDisplayMessageBody());

					if ("5556".equals(number))
						abortBroadcast();
				}
			}

			// if(isOrderedBroadcast())
			// abortBroadcast();
		}
	}

}
