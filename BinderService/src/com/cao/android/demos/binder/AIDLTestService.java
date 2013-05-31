
package com.cao.android.demos.binder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.cao.android.demos.binder.aidl.AIDLActivity;
import com.cao.android.demos.binder.aidl.AIDLService;
import com.cao.android.demos.binder.aidl.Rect1;

public class AIDLTestService extends Service {

	private AIDLActivity callback;//

	private void Log(String str) {
		Log.d(Constant.TAG, "------ " + str + "------");
	}

	@Override
	public void onCreate() {
		Log("service create");
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log("service start id=" + startId);
	}

	@Override
	public IBinder onBind(Intent t) { //
		Log("service on bind");
		return mBinder;
	}

	@Override
	public void onDestroy() {
		Log("service on destroy");
		super.onDestroy();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		Log("service on unbind");
		return super.onUnbind(intent);
	}

	public void onRebind(Intent intent) {
		Log("service on rebind");
		super.onRebind(intent);
	}

	//Stub 是 AIDLService 接口里 的 内部类，他是 android自动生成的，他 继承了 我们的aidl接口，因此 需要 实现 接口里的 方法;
	private final AIDLService.Stub mBinder = new AIDLService.Stub() { 

		@Override
		public void registerTestCall(AIDLActivity cb) throws RemoteException {
			Log("AIDLService.registerTestCall");
			callback = cb;
		}
		
		@Override
		public void invokCallBack() throws RemoteException {
			Log("AIDLService.invokCallBack");
			Rect1 rect = new Rect1();
			rect.bottom=-1;
			rect.left=-1;
			rect.right=1;
			rect.top=1;
			callback.performAction(rect);
		}
	};
}
