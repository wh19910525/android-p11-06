package com.tarena.day2206;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class SmsService extends Service {
	
	private SmsReceiver receiver;

	//��Serviceģ����һ�򿪾�������
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		receiver = new SmsReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction("android.provider.Telephony.SMS_RECEIVED");
		filter.setPriority(1000);//�趨���㲥�����ȼ�Ϊ���,���ȼ����λ1000
		registerReceiver(receiver, filter);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
