package wh.zgj;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.Gravity;
import android.widget.Toast;

public class SMsreceiver extends BroadcastReceiver {
	//当接收到短信时，自动 触发 此方法
	public  void onReceive(Context context, Intent intent){

		Bundle bundle = intent.getExtras();
		Object message[] = (Object[])bundle.get("puts");
		SmsMessage smsMessage[] = new SmsMessage[message.length];
		for (int n = 0; n < message.length; n++)
		{
			smsMessage[n] = SmsMessage.createFromPdu((byte[])message[n]);
		}
		
		Toast toast = Toast.makeText(context, "短信内容："+smsMessage[0].getMessageBody(),Toast.LENGTH_LONG);
		
		//设置toast显示的位置
		toast.setGravity(Gravity.BOTTOM|Gravity.LEFT, 0, 200);
		//显示 toast
		toast.show();
	}
}
