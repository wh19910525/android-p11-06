package com.tarena.day2304;

import com.tarena.aidl.IPlayControllor;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
   AIDL : Android Interface Definition Language
     ��������̵��õ�Service�Ĳ���:
  1������һ��aidl�ӿ�
  2������һ�� binder�� ��չ �ӿ�.Stub
  3����Service��onBinder�з��� binder����
  
  ����Ŀ�Ǻ���ĿDay23_0401_BindService ���ִ�е� ,Day23_0401_BindService
  ��Ҫ�󶨴���Ŀ��Service��*/

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
