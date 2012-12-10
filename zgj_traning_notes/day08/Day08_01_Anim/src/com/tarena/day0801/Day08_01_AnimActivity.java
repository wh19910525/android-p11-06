package com.tarena.day0801;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class Day08_01_AnimActivity extends Activity {
	
	private TextView tvTest;

	private void setupView() {
		tvTest = (TextView) findViewById(R.id.tvTest);
	}

	public void doClick(View v) {
		// 获取动画对象
		 Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate);
//		Animation anim = new TranslateAnimation(0, 100, 0, 200);
//		anim.setDuration(2000);
//		anim.setFillAfter(true);
//		Animation anim = new TranslateAnimation(fromXType, fromXValue, toXType, toXValue, fromYType, fromYValue, toYType, toYValue)
				
		// 启动动画
		// tvTest.setAnimation(anim);
		tvTest.startAnimation(anim);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}
}