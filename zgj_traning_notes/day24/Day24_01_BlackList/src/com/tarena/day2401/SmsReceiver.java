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
			//��������˺�����
			if (pref.getBoolean("useBlackList", false)) {
				BlackListBiz biz = new BlackListBiz(context);
				Object[] pdus = (Object[]) intent.getExtras().get("pdus");
				//����������д��ڸú��룬�����ع㲥
				for (Object pdu : pdus) {
					SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdu);
					if (biz.isEists(msg.getDisplayOriginatingAddress())) {
						abortBroadcast();//ϵͳ�Ѿ� ���� ���ţ� ���� �����أ�Ҳ���� �û���������������
					}
				}
			}
		} else if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {

			// ����Ѿ����ú�������������service
			if (pref.getBoolean("useBlackList", false)) {
				Intent service = new Intent(context, TelService.class);
				context.startService(service);
			}
		}

	}

}
