package com.tarena.day0902;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class Day09_02_ShapeActivity extends Activity {
	private ImageView ivTest;

	private void setupView() {
		ivTest = (ImageView) findViewById(R.id.ivTest);
		// ivTest.setImageResource(R.drawable.cursor);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
		
		// 获取手机屏幕宽度的像素值
		// getResources().getDisplayMetrics().widthPixels;
	}
}