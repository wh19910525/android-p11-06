package com.tarena.day2303;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.android.internal.telephony.ITelephony;

import android.app.Activity;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class Day23_03_TelActivity extends Activity {

	private AudioManager am;
	private TelephonyManager tm;
	private MyPhoneStateListener listener;
	
	/**
	 * 从外部打进此模拟器*/
	
	//创建电话状态侦听器
	private class MyPhoneStateListener extends PhoneStateListener {

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:// 空闲
				Log.i("info", "空闲：" + incomingNumber);
				am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);//设置来电为普通模式
				// Log.i("info", "铃声");
				break;
			case TelephonyManager.CALL_STATE_RINGING:// 响铃
				Log.i("info", "响铃:" + incomingNumber);
				if ("555666".equals(incomingNumber)) {
					
					/**表示对指定来电设为静音*/
					// am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
					// Log.i("info", "已经设置静音");

					/**
					 * ServiceManager是一个隐藏的API,只能通过反射获得它的方法
					 * 并且它的getService(String name)返回的对象是Binder 和  ITelephony的子类
					 * 所以可以进行以下几步操作1、and 2、*/
					
					try {
						Method method = Class.forName(
								"android.os.ServiceManager").getDeclaredMethod(
								"getService", String.class);//表示getService()方法的参数类型为String
						
						//1、invoke()的参数分别表示,哪个对象调用此方法和调用方法的参数
						IBinder binder = (IBinder) method.invoke(null, new String[] { TELEPHONY_SERVICE });
						
						//2、将IBinder对象转化为ITelephony对象
						ITelephony tel = ITelephony.Stub.asInterface(binder);
						
						//其他还有的一些方法1.tel.call("被呼叫号码")，2.tel.dial("被呼叫号码")但会显示拨号的屏幕
						//但是以上的2种需要root权限才可以,这里不可以
						
						tel.endCall();//结束指定号码的来电 模拟器接不到指定号码的来电
						
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				break;
			case TelephonyManager.CALL_STATE_OFFHOOK:// 摘机
				Log.i("info", "摘机:" + incomingNumber);
				break;
			}
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		/**用于设置来电(或者是指定来电)的音乐,是静音还是震动或是普通模式*/
		
		am = (AudioManager) getSystemService(AUDIO_SERVICE);//

		tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		Log.i("info", "getDeviceId : " + tm.getDeviceId());
		Log.i("info", "getDataState : " + tm.getDataState());
		Log.i("info", "getCallState : " + tm.getCallState());
		Log.i("info", "getDeviceSoftwareVersion : " + tm.getDeviceSoftwareVersion());
		Log.i("info", "getLine1Number : " + tm.getLine1Number());
		Log.i("info", "getNetworkCountryIso : " + tm.getNetworkCountryIso());
		Log.i("info", "getNetworkOperator : " + tm.getNetworkOperator());
		Log.i("info", "getNetworkOperatorName : " + tm.getNetworkOperatorName());
		Log.i("info", "getNetworkType : " + tm.getNetworkType());
		Log.i("info", "getPhoneType : " + tm.getPhoneType());
		Log.i("info", "getSimCountryIso : " + tm.getSimCountryIso());
		Log.i("info", "getSimOperator : " + tm.getSimOperator());
		Log.i("info", "getSimOperatorName : " + tm.getSimOperatorName());
		Log.i("info", "getSimSerialNumber : " + tm.getSimSerialNumber());
		Log.i("info", "getSimState : " + tm.getSimState());
		Log.i("info", "getSubscriberId : " + tm.getSubscriberId());
		Log.i("info", "getCellLocation : " + tm.getCellLocation());
		
		//添加电话状态监听器
		listener = new MyPhoneStateListener();
		tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//释放监听器
		tm.listen(listener, PhoneStateListener.LISTEN_NONE);
	}
}