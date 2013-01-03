package com.tarena.day1701;

import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;

/***
 * ʵ�ֹ۲��Ŀ�ģ�
 *  ʹ�ò��裺
  1)����һ�����ݹ۲����࣬�̳�ContentObserver
  2)��д��onChange����
  3)�ڿͻ��˴���һ���۲��߶���
    ����ContentResolver�����
    registerContentObserver(Uri,boolean,ContentObserver)ע��۲��߶���
  ��Activity����ʱ���Ե��� ����ContentResolver�����unregisterContentObserver(observer)����۲�
    
  ע��:
   ContentProvider����Ĭ�Ͼ�֧�ֹ۲��ߣ������Ҫ֧�ֹ۲���
   Ӧ������ɾ�ķ����е���(�ɲο���ĿDay18_01_MusicProvider�µ���MusicProvider.java)
   contentResolver�����notifyChange(Uri, boolean, ContentObserver)
 *
 */
public class MusicObserver extends ContentObserver {
	private Handler handler;
	/***
	�̳�ContentObserver��Ҫ��д�乹�췽����onChange����
	 */

	public MusicObserver(Handler handler) {
		super(handler);
		this.handler = handler;
	}

	@Override
	public void onChange(boolean selfChange) {
		Log.i("info", "onChange");
		handler.sendEmptyMessage(0);
	}

}
