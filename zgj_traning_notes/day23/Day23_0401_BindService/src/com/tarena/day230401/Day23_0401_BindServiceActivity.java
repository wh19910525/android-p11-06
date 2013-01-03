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
     创建跨进程调用的Service的步骤:
  1、创建一个aidl接口
  2、创建一个 binder类 扩展 接口.Stub
  3、在Service的onBinder中返回 binder对象
  
  ****以上三部骤是欲被绑定的Service需要完成的****
  
   注意：在第一个进程写的以.aidl 命名的文档，必须在欲要执行绑定Service的进程同时存在这样的文档
      此时gen 目录下会自动生成原以.aidl为后缀的文档的.java 文档
      
      此项目是和项目Day23_04_AIDL结合执行的,此项目是要绑定项目Day23_04_AIDL的Service的*/


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
		bindService(intent, conn, BIND_AUTO_CREATE);//BIND_AUTO_CREATE表示如果没有被绑定,则自动绑定
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unbindService(conn);
	}
}