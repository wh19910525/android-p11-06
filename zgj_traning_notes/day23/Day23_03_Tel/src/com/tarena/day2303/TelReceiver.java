package com.tarena.day2303;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TelReceiver extends BroadcastReceiver {

	/**
	 * �������ڲ���ģ�����绰�ĺ���*/
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		
		/**���е绰�������ʱ��, ϵͳ���Զ�����android.intent.action.NEW_OUTGOING_CALL�㲥, 
		 * �˹㲥���������ڽ��� ������ĺ���,�����ǽ�ֹ�Ļ�����ͨ����
		 * ֻ������Ǳ���Ŀ��ָ������,������������յ�����,����ϵͳ���ǻᷢ���㲥*/
		
		String action = intent.getAction();
		if (Intent.ACTION_NEW_OUTGOING_CALL.equals(action)) {
			// ��ȡ��������
			// String number = getResultData();
			String number = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
			Log.i("info", "number:" + number);

			if ("110".equals(number)) {
				
		/**ע��绰�Ĳ����񷢶��Ż�������ͨ�㲥һ��,��ʹ�˹㲥Ϊ����㲥,����abortBroadcast()����
		   Ҳ��������Ϲ㲥�ļ�������*/
				
//				 if (isOrderedBroadcast()) {
//				 Log.i("info", "isOrderedBroadcast");
//				 abortBroadcast();
				setResultData(null);//�˷�������ʹָ���ĺ��벦����ȥ
//				 }
			}
		}
	}

}
