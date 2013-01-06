package com.tarena.day2206;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {

	/**本类是验证通过模拟器直接发信息给模拟器,当模拟器收到短息后，短信系统会发 android.provider.Telephony.SMS_RECEIVED 广播，
	 * 然后 本广播接收器接收对应的广播, 并读取短信
	 * 当发送的号码为5556，本广播自动会销毁广播的继续下传，这样模拟器将不会显示收到短信*/
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getAction();
		Log.i("info", "接收短信");
		if ("android.provider.Telephony.SMS_RECEIVED".equals(action)) {
			// 读取短信息内容，格式固定
			Object[] pdus = (Object[]) intent.getExtras().get("pdus");
			if (pdus != null) {
				for (Object pdu : pdus) {
					SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdu);
					String number = msg.getDisplayOriginatingAddress();//获取 发送方的 号码
					Log.i("info", "===zgj"+number);
					Log.i("info", "===zgj"+msg.getDisplayMessageBody());//获取 发送方 发的 短信内容

					if ("5556".equals(number))
						abortBroadcast();//系统已经 接收 短信， 并且 被拦截，用户看不到这条短信；用这个方法可以 欺骗 用户；
				}
			}

			// if(isOrderedBroadcast())
			// abortBroadcast();
		}
		
		//启动service
		if ("android.intent.action.BOOT_COMPLETED".equals(action)) {
			Intent mIntent = new Intent(context, SmsService.class);
		    context.startService(mIntent);
			
		}
		
	}

}
