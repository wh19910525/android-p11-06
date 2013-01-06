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
	 * ���ⲿ�����ģ����*/
	
	//�����绰״̬������
	private class MyPhoneStateListener extends PhoneStateListener {

		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
			switch (state) {
			case TelephonyManager.CALL_STATE_IDLE:// ����
				Log.i("info", "���У�" + incomingNumber);
				am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);//��������Ϊ��ͨģʽ
				// Log.i("info", "����");
				break;
			case TelephonyManager.CALL_STATE_RINGING:// ����
				Log.i("info", "����:" + incomingNumber);
				if ("555666".equals(incomingNumber)) {
					
					/**��ʾ��ָ��������Ϊ����*/
					// am.setRingerMode(AudioManager.RINGER_MODE_SILENT);
					// Log.i("info", "�Ѿ����þ���");

					/**
					 * ServiceManager��һ�����ص�API,ֻ��ͨ�����������ķ���
					 * ��������getService(String name)���صĶ�����Binder ��  ITelephony������
					 * ���Կ��Խ������¼�������1��and 2��*/
					
					try {
						Method method = Class.forName(
								"android.os.ServiceManager").getDeclaredMethod(
								"getService", String.class);//��ʾgetService()�����Ĳ�������ΪString
						
						//1��invoke()�Ĳ����ֱ��ʾ,�ĸ�������ô˷����͵��÷����Ĳ���
						IBinder binder = (IBinder) method.invoke(null, new String[] { TELEPHONY_SERVICE });
						
						//2����IBinder����ת��ΪITelephony����
						ITelephony tel = ITelephony.Stub.asInterface(binder);
						
						//�������е�һЩ����1.tel.call("�����к���")��2.tel.dial("�����к���")������ʾ���ŵ���Ļ
						//�������ϵ�2����ҪrootȨ�޲ſ���,���ﲻ����
						
						tel.endCall();//����ָ����������� ģ�����Ӳ���ָ�����������
						
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
			case TelephonyManager.CALL_STATE_OFFHOOK:// ժ��
				Log.i("info", "ժ��:" + incomingNumber);
				break;
			}
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		/**������������(������ָ������)������,�Ǿ��������𶯻�����ͨģʽ*/
		
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
		
		//��ӵ绰״̬������
		listener = new MyPhoneStateListener();
		tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//�ͷż�����
		tm.listen(listener, PhoneStateListener.LISTEN_NONE);
	}
}