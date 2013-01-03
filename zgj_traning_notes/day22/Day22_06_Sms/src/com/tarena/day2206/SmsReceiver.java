package com.tarena.day2206;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {

	/**��������֤ͨ��ģ����ֱ�ӷ���Ϣ��ģ����,��ģ�����յ���Ϣ��ϵͳ�ᷢ�㲥��ͨ�����㲥���������ն�Ӧ�Ĺ㲥,
	 * ����ȡ����
	 * �����͵ĺ���Ϊ5556�����㲥�Զ������ٹ㲥�ļ����´�������ģ�����������յ�����*/
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getAction();
		Log.i("wh", "���ն���");
		if ("android.provider.Telephony.SMS_RECEIVED".equals(action)) {
			// ��ȡ����Ϣ����
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
