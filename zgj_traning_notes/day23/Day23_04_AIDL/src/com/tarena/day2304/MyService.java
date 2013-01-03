package com.tarena.day2304;

import com.tarena.aidl.IPlayControllor;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
   AIDL : Android Interface Definition Language
     创建跨进程调用的Service的步骤:
  1、创建一个aidl接口
  2、创建一个 binder类 扩展 接口.Stub
  3、在Service的onBinder中返回 binder对象
  
  此项目是和项目Day23_0401_BindService 结合执行的 ,Day23_0401_BindService
  是要绑定此项目的Service的*/

public class MyService extends Service {
	public class MyBinder extends IPlayControllor.Stub {

		@Override
		public void play() throws RemoteException {
			// TODO Auto-generated method stub
			Log.i("info", "play()");
		}

		@Override
		public void pause() throws RemoteException {
			// TODO Auto-generated method stub
			Log.i("info", "pause()");
		}

		@Override
		public void next() throws RemoteException {
			// TODO Auto-generated method stub
			Log.i("info", "next()");
		}

		@Override
		public void previous() throws RemoteException {
			// TODO Auto-generated method stub
			Log.i("info", "previous()");
		}

		@Override
		public void seekTo(int position) throws RemoteException {
			// TODO Auto-generated method stub
			Log.i("info", "seekTo(" + position + ")");
		}

	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return new MyBinder();
	}

}
