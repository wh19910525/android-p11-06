package com.tarena.day0710;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout.LayoutParams;

public class Day07_10_TouchActivity extends Activity {
	
	private static final String TAG = "Activity.";

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.i("info", TAG + "dispatchTouchEvent(action=" + event.getAction() + ")");
		return super.dispatchTouchEvent(event);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.i("info", TAG + "onTouchEvent(action=" + event.getAction() + ")");
		return super.onTouchEvent(event);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ����һ���ؼ�
		MyTextView tv = new MyTextView(this);
		LayoutParams params = new LayoutParams(300, 300);
		tv.setLayoutParams(params);
		tv.setBackgroundColor(Color.RED);

		tv.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Log.i("info", "MyTextView�ļ�������onTouch����");
				//return true;
				return false;
			}
		});

		// ����һ������
		MyLinearLayout layout = new MyLinearLayout(this);
		layout.addView(tv);//Ϊʲôִ���� ���֮����ִ�� setContentView(tv); �д�

		// ����������ͼ
		//setContentView(tv);
		setContentView(layout);
	}

}