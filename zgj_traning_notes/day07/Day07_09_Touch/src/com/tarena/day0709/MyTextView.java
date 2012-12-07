package com.tarena.day0709;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.TextView;

public class MyTextView extends TextView {
	public MyTextView(Context context){
		super(context);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.i("info", "onTouch()---action=" + event.getAction() + ",["
				+ event.getX() + "," + event.getY() + "]");
		// event.getAction();
		// event.getX();
		// event.getY();

		return true;
	}
}
