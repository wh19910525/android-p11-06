package com.tarena.utils;

import android.os.Handler;
import android.os.Message;

public abstract class MyAsyncTask<Params, Progress, Result> {
	private static final int MSG_TAG_FINISHED = 1;
	private static final int MSG_TAG_PROGRESS = 2;
	private Handler handler;

	public MyAsyncTask() {
		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case MSG_TAG_FINISHED:// 异步任务执行完成
					onPostExecute((Result) msg.obj);
					break;

				case MSG_TAG_PROGRESS:// 异步任务执行过程中，进度更新的消息处理
					onUpdateProgress((Progress) msg.obj);
					break;
				}
			}
		};
	}

	public void execute(final Params params) {
		// 准备工作
		onPreExecute();
		// 启动工作线程，执行异步任务
		new Thread() {
			public void run() {
				// 执行异步任务
				Result result = doInBackground(params);
				// 发送消息回主线程
				Message msg = Message.obtain(handler, MSG_TAG_FINISHED, result);
				msg.sendToTarget();
			};
		}.start();
	}

	public void publishProgress(Progress progress) {
		Message msg = Message.obtain(handler, MSG_TAG_PROGRESS, progress);
		msg.sendToTarget();
	}

	/**
	 * 在工作线程中运行的具体业务方法
	 */
	public abstract Result doInBackground(Params params);

	/**
	 * 开始执行异步任务前的准备工作
	 */
	public abstract void onPreExecute();

	/**
	 * 异步任务执行完成后，在主线程中更新界面的方法
	 */
	public abstract void onPostExecute(Result result);

	/**
	 * 在主线程中更新进度的方法
	 */
	public abstract void onUpdateProgress(Progress progress);
}
