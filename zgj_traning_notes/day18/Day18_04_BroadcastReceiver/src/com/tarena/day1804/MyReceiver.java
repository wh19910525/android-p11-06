package com.tarena.day1804;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("info", "onReceive--2");
		String action = intent.getAction();
		/**
		 ע�⣺�ڷ������������,������ʹ�öԻ����Toast������Ϣ
		 ���Է�֪ͨ������Ϣ����ʾ*/
		
		if ("com.tarena.action.MYBROADCAST".equals(action)) {
			Toast.makeText(context, "�յ��Զ���㲥", 3000).show();
			// Intent target = new Intent(context, Day18_04_BroadcastReceiverActivity.class);
			
			/**�������𶯵�Activity�ŵ��µ�����ջ��,���Ǹ��ݹ㲥����������,һ�����������������������µ�Activity,*/
			// target.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			// context.startActivity(target);
		} else if (Intent.ACTION_BOOT_COMPLETED.equals(action)) {

		} else if (Intent.ACTION_BATTERY_LOW.equals(action)) {

		}

	}

}
