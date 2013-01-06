package com.tarena.day2206;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.sax.StartElementListener;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {

	/**��������֤ͨ��ģ����ֱ�ӷ���Ϣ��ģ����,��ģ�����յ���Ϣ�󣬶���ϵͳ�ᷢ android.provider.Telephony.SMS_RECEIVED �㲥��
	 * Ȼ�� ���㲥���������ն�Ӧ�Ĺ㲥, ����ȡ����
	 * �����͵ĺ���Ϊ5556�����㲥�Զ������ٹ㲥�ļ����´�������ģ������������ʾ�յ�����*/
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getAction();
		Log.i("info", "���ն���");
		if ("android.provider.Telephony.SMS_RECEIVED".equals(action)) {
			// ��ȡ����Ϣ���ݣ���ʽ�̶�
			Object[] pdus = (Object[]) intent.getExtras().get("pdus");
			if (pdus != null) {
				for (Object pdu : pdus) {
					SmsMessage msg = SmsMessage.createFromPdu((byte[]) pdu);
					String number = msg.getDisplayOriginatingAddress();//��ȡ ���ͷ��� ����
					Log.i("info", "===zgj"+number);
					Log.i("info", "===zgj"+msg.getDisplayMessageBody());//��ȡ ���ͷ� ���� ��������

					if ("5556".equals(number))
						abortBroadcast();//ϵͳ�Ѿ� ���� ���ţ� ���� �����أ��û��������������ţ�������������� ��ƭ �û���
				}
			}

			// if(isOrderedBroadcast())
			// abortBroadcast();
		}
		
		//����service
		if ("android.intent.action.BOOT_COMPLETED".equals(action)) {
			Intent mIntent = new Intent(context, SmsService.class);
		    context.startService(mIntent);
			
		}
		
	}

}
