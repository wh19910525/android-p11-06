package wh.zgj;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.Gravity;
import android.widget.Toast;

public class SMsreceiver extends BroadcastReceiver {
	//�����յ�����ʱ���Զ� ���� �˷���
	public  void onReceive(Context context, Intent intent){

		Bundle bundle = intent.getExtras();
		Object message[] = (Object[])bundle.get("puts");
		SmsMessage smsMessage[] = new SmsMessage[message.length];
		for (int n = 0; n < message.length; n++)
		{
			smsMessage[n] = SmsMessage.createFromPdu((byte[])message[n]);
		}
		
		Toast toast = Toast.makeText(context, "�������ݣ�"+smsMessage[0].getMessageBody(),Toast.LENGTH_LONG);
		
		//����toast��ʾ��λ��
		toast.setGravity(Gravity.BOTTOM|Gravity.LEFT, 0, 200);
		//��ʾ toast
		toast.show();
	}
}
