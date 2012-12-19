package com.tarena.day1404;

import android.os.Looper;
import android.util.Log;

public class MyHandlerThread extends Thread {
	private Looper looper;

	public Looper getLooper() {
		if (!isAlive())
			return null;
		if (looper == null) {
			synchronized (this) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return looper;
	}

	@Override
	public void run() {
		Log.i("info", "��ʼ���й����߳�");
		// ������Ϣ����
		Looper.prepare();
		Log.i("info", "�Ѵ�����Ϣ����");
		// ��ȡLooper����
		looper = Looper.myLooper();
		synchronized (this) {
			this.notifyAll();
		}
		Log.i("info", "��ȡlooper����");
		// ʹ��looper��ѯ��Ϣ����
		Log.i("info", "��ʼ��ѯ");
		Looper.loop();
		Log.i("info", "��ѯ����");
		Log.i("info", "�����߳�ִ�н���");
	}

	public void quit() {
		if (looper != null)
			looper.quit();
	}
}
