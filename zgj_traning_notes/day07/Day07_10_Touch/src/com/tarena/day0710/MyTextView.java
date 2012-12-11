package com.tarena.day0710;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class MyTextView extends TextView {
	private static final String TAG = "MyTextView.";
	public MyTextView(Context context) {
		super(context);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.i("info", TAG+"dispatchTouchEvent(action="+event.getAction()+")");
		return super.dispatchTouchEvent(event);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.i("info", TAG+"onTouchEvent(action="+event.getAction()+")");
		return super.onTouchEvent(event);
		//return false;//为什么返回 false,和  上边的 返回值 有什么区别
	}
}
