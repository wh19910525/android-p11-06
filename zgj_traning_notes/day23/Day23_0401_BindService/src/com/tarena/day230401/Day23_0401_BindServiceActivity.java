package com.tarena.day230401;

import com.tarena.aidl.IPlayControllor;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;


/**
  AIDL : Android Interface Definition Language
     ��������̵��õ�Service�Ĳ���:
  1������һ��aidl�ӿ�
  2������һ�� binder�� ��չ �ӿ�.Stub
  3����Service��onBinder�з��� binder����
  
  ****�����������������󶨵�Service��Ҫ��ɵ�****
  
   ע�⣺�ڵ�һ������д����.aidl �������ĵ�����������Ҫִ�а�Service�Ľ���ͬʱ�����������ĵ�
      ��ʱgen Ŀ¼�»��Զ�����ԭ��.aidlΪ��׺���ĵ���.java �ĵ�
      
      ����Ŀ�Ǻ���ĿDay23_04_AIDL���ִ�е�,����Ŀ��Ҫ����ĿDay23_04_AIDL��Service��*/


public class Day23_0401_BindServiceActivity extends Activity {
	private IPlayControllor controllor;
	ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			// TODO Auto-generated method stub
			controllor = IPlayControllor.Stub.asInterface(binder);
		}
	};

	public void doClick(View v) {
		try {
			switch (v.getId()) {
			case R.id.btnPlay:
				controllor.play();
				break;
			case R.id.btnPause:
				controllor.pause();
				break;
			case R.id.btnPrevious:
				controllor.previous();
				break;
			case R.id.btnNext:
				controllor.next();
				break;
			case R.id.btnSeekTo:
				controllor.seekTo(50000);
				break;
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Intent intent = new Intent("com.tarena.action.BIND_SERVICE");
		bindService(intent, conn, BIND_AUTO_CREATE);//BIND_AUTO_CREATE��ʾ���û�б���,���Զ���
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unbindService(conn);
	}
}