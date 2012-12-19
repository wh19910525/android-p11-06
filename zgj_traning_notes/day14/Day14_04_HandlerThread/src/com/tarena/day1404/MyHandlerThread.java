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
		Log.i("info", "开始运行工作线程");
		// 创建消息队列
		Looper.prepare();
		Log.i("info", "已创建消息队列");
		// 获取Looper对象
		looper = Looper.myLooper();
		synchronized (this) {
			this.notifyAll();
		}
		Log.i("info", "获取looper对象");
		// 使用looper轮询消息队列
		Log.i("info", "开始轮询");
		Looper.loop();
		Log.i("info", "轮询结束");
		Log.i("info", "工作线程执行结束");
	}

	public void quit() {
		if (looper != null)
			looper.quit();
	}
}
