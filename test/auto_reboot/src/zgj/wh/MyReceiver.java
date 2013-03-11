package zgj.wh;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
	
	private static final String ACTION_COMP = "android.intent.action.BOOT_COMPLETED"; 
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		Log.i("wanghai", "onReceive");

		String action = intent.getAction();

	      Intent newIntent = new Intent(context, zgj.wh.Auto_rebootActivity.class);   
	      
	      newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  //注意，必须添加这个标记，否则启动会失败   
	  
	      context.startActivity(newIntent); 


	}

}
