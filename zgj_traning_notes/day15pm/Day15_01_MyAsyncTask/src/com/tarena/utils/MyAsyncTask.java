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
				case MSG_TAG_FINISHED:// �첽����ִ�����
					onPostExecute((Result) msg.obj);
					break;

				case MSG_TAG_PROGRESS:// �첽����ִ�й����У����ȸ��µ���Ϣ����
					onUpdateProgress((Progress) msg.obj);
					break;
				}
			}
		};
	}

	public void execute(final Params params) {
		// ׼������
		onPreExecute();
		// ���������̣߳�ִ���첽����
		new Thread() {
			public void run() {
				// ִ���첽����
				Result result = doInBackground(params);
				// ������Ϣ�����߳�
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
	 * �ڹ����߳������еľ���ҵ�񷽷�
	 */
	public abstract Result doInBackground(Params params);

	/**
	 * ��ʼִ���첽����ǰ��׼������
	 */
	public abstract void onPreExecute();

	/**
	 * �첽����ִ����ɺ������߳��и��½���ķ���
	 */
	public abstract void onPostExecute(Result result);

	/**
	 * �����߳��и��½��ȵķ���
	 */
	public abstract void onUpdateProgress(Progress progress);
}
