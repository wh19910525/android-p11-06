package com.tarena.day2401;

import com.tarena.biz.BlackListBiz;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.SmsMessage;

public class SmsReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getAction();
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		if ("android.provider.Telephony.SMS_RECEIVED".equals(action)) {
			//如果启用了黑名单
			if (pref.getBoolean("useBlackList", false)) {
				BlackListBiz biz = new BlackListBiz(context);
				Object[] pdus = (Object[]) intent.getExtras().get("pdus");
				//如果黑名单中存在该号码，则拦截广播
				for (Object pdu : pdus) {
					SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdu);
					if (biz.isEists(msg.getDisplayOriginatingAddress())) {
						abortBroadcast();//系统已经 接收 短信， 并且 被拦截，也就是 用户看不到这条短信
					}
				}
			}
		} else if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {

			// 如果已经启用黑名单，则启动service
			if (pref.getBoolean("useBlackList", false)) {
				Intent service = new Intent(context, TelService.class);
				context.startService(service);
			}
		}

	}

}
