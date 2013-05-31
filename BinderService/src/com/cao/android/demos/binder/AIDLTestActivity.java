
package com.cao.android.demos.binder;

import java.text.MessageFormat;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.cao.android.demos.binder.aidl.AIDLActivity;
import com.cao.android.demos.binder.aidl.AIDLService;
import com.cao.android.demos.binder.aidl.Rect1;

public class AIDLTestActivity extends Activity {
	
	private Button btnOk;
	private Button btnCancel;
	private Button btnCallBack;

	private void Log(String str) {
		Log.d(Constant.TAG, "------ " + str + "------");
	}
	
	//Stub 是 AIDLActivity 接口里 的 内部类，他是 android自动生成的，他 继承了 我们的aidl接口，因此 需要 实现 接口里的 方法;
	private AIDLActivity mCallback = new AIDLActivity.Stub() {

		@Override
		public void performAction(Rect1 rect) throws RemoteException {
			Log("AIDLActivity.performAction");
			Log(MessageFormat.format("rect[bottom={0},top={1},left={2},right={3}]", rect.bottom,rect.top,rect.left,rect.right));
			Toast.makeText(AIDLTestActivity.this, "this toast is called from service", 1).show();
		
		}
	};

	AIDLService mService;
	private ServiceConnection mConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			Log("connect service");
			mService = AIDLService.Stub.asInterface(service); //获取 服务端 进程里的 对象，也就是 现在的这个 mService是 服务端 进程里的；
			try {
				mService.registerTestCall(mCallback); //把 activity 进程里的 mCallback 赋值给 服务器 进程；
			} catch (RemoteException e) {

			}
		}

		public void onServiceDisconnected(ComponentName className) {
			Log("disconnect service");
			mService = null;
		}
	};

	/*
	 * 获取服务端得aidl对象后 mService = AIDLService.Stub.asInterface(service); 就可以在客户端使用它了，
	 * 对mService对象 方法的调用  不是在客户端执行，而是 在服务端执行。
	 * 
	 * */
	
	@Override
	public void onCreate(Bundle icicle) {
		
		Log("AIDLTestActivity.onCreate");
		super.onCreate(icicle);
		setContentView(R.layout.aidl_activity);
		
		btnOk = (Button) findViewById(R.id.btn_ok);
		btnCancel = (Button) findViewById(R.id.btn_cancel);
		btnCallBack = (Button) findViewById(R.id.btn_call_back);
		
		btnOk.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log("AIDLTestActivity.btnOk");
				Bundle args = new Bundle();
				Intent intent = new Intent();
				intent.setAction("com.cao.android.demos.binder.AIDLTestService");
				intent.putExtras(args);
				bindService(intent, mConnection, Context.BIND_AUTO_CREATE); //绑定 service
			//	startService(intent); 
			}
		});
		
		btnCancel.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log("AIDLTestActivity.btnCancel");
				unbindService(mConnection);
				// stopService(intent);
			}
		});
		
		btnCallBack.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log("AIDLTestActivity.btnCallBack");
				try {
					mService.invokCallBack();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
