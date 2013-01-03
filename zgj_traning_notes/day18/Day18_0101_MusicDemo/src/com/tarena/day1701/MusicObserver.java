package com.tarena.day1701;

import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;

/***
 * 实现观察的目的：
 *  使用步骤：
  1)创建一个内容观察者类，继承ContentObserver
  2)重写其onChange方法
  3)在客户端创建一个观察者对象
    调用ContentResolver对象的
    registerContentObserver(Uri,boolean,ContentObserver)注册观察者对象
  在Activity结束时可以调用 调用ContentResolver对象的unregisterContentObserver(observer)解除观察
    
  注意:
   ContentProvider不是默认就支持观察者，如果需要支持观察者
   应当在增删改方法中调用(可参考项目Day18_01_MusicProvider下的类MusicProvider.java)
   contentResolver对象的notifyChange(Uri, boolean, ContentObserver)
 *
 */
public class MusicObserver extends ContentObserver {
	private Handler handler;
	/***
	继承ContentObserver需要重写其构造方法和onChange方法
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
