package com.tarena.day0710;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {
	private static final String TAG = "MyLinearLayout.";
	public MyLinearLayout(Context context) {
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
		return false;
	}
}
