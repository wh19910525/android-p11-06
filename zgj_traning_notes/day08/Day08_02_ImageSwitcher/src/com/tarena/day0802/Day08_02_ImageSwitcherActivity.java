package com.tarena.day0802;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewSwitcher.ViewFactory;

public class Day08_02_ImageSwitcherActivity extends Activity {
	
	private ImageSwitcher isTest;

	private void setupView() {
		isTest = (ImageSwitcher)findViewById(R.id.isTest);
		isTest.setInAnimation(this, android.R.anim.fade_in);// 设置切入动画
		isTest.setOutAnimation(this, android.R.anim.fade_out);// 设置切出动画
		isTest.setFactory(new ViewFactory() {// 实现并设置工厂内部接口的makeView方法，用来显示视图。
			
			public View makeView() {
				// TODO Auto-generated method stub
				Log.i("info", "makeView()");
				//创建ImageView对象
				ImageView iv = new ImageView(Day08_02_ImageSwitcherActivity.this);
				//设置该对象的布局参数为填充父容器
				LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
				iv.setLayoutParams(params);
				//设置iamgeview对象的 伸缩模式
				iv.setScaleType(ScaleType.FIT_CENTER);
				
				return iv;
			}
		});
	}
	
	public void doClick(View v){
		switch (v.getId()) {
		case R.id.btnTest1:
			isTest.setImageResource(R.drawable.p01); // 设置图片来源
			break;

		case R.id.btnTest2:
			isTest.setImageResource(R.drawable.p02);
			break;
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}
}