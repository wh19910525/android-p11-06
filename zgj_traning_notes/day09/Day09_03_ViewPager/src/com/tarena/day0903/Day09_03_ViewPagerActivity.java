package com.tarena.day0903;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class Day09_03_ViewPagerActivity extends Activity {
	
	private ViewPager pager;
	private LinearLayout tabs;
	private ImageView ivCursor;
	private ArrayList<View> views;
	private ViewAdapter adapter;//
	private int currentPosition;

	private ArrayList<View> getViews() {//
		
		ArrayList<View> views = new ArrayList<View>();
		ImageView iv = new ImageView(this);
		iv.setImageResource(R.drawable.p01);
		iv.setScaleType(ScaleType.FIT_CENTER);
		iv.setTag("p01");//setTag(Onbect)表示给View添加一个格外的数据，以后可以用getTag()将这个数据取出来。
		views.add(iv);

		iv = new ImageView(this);
		iv.setImageResource(R.drawable.p02);
		iv.setScaleType(ScaleType.FIT_CENTER);
		iv.setTag("p02");
		views.add(iv);

		iv = new ImageView(this);
		iv.setImageResource(R.drawable.p03);
		iv.setScaleType(ScaleType.FIT_CENTER);
		iv.setTag("p03");
		views.add(iv);

		iv = new ImageView(this);
		iv.setImageResource(R.drawable.ic_launcher);
		iv.setScaleType(ScaleType.FIT_CENTER);
		iv.setTag("p04");
		views.add(iv);
		
		return views;
	}

	private TextView createTab(String text) {//
		TextView tv = new TextView(this);
		// 设置布局参数
		LayoutParams params = new LayoutParams(0, 60, 1.0f);
		tv.setLayoutParams(params);
		// 设置文本内容和格式
		tv.setText(text);
		tv.setTextSize(25);
		tv.setTextColor(Color.RED);
		tv.setGravity(Gravity.CENTER);

		return tv;
	}

	private void setupView() {
		// 初始化pager
		pager = (ViewPager) findViewById(R.id.pager);
		views = getViews();
		adapter = new ViewAdapter(views);//
		pager.setAdapter(adapter);

		// 初始化imageView
		ivCursor = (ImageView) findViewById(R.id.ivCursor);
		
		// 获取手机屏幕宽度的像素值， 计算控件的宽
		int width = getResources().getDisplayMetrics().widthPixels / views.size();
		// 设置控件的布局参数
		LayoutParams params = new LayoutParams(width, 5);//参数是 宽 和 高
		ivCursor.setLayoutParams(params);

		// 初始化tas
		tabs = (LinearLayout) findViewById(R.id.layoutTabs);
		for (View view : views) {
			TextView tab = createTab(view.getTag().toString());//通过 getTag()获取 getViews() 里设置的 setTag；
			tabs.addView(tab);
		}
	}

	private void addListener() {
		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				// 设置动画
				int width = ivCursor.getWidth();//这个 宽度有什么用，
				TranslateAnimation anim = 
						new TranslateAnimation(currentPosition * width, position * width, 0, 0);//前两个参数有什么用；
				anim.setDuration(1000);//移动 需要的 时间
				anim.setFillAfter(true);//显示 移动后的 图标
				// 移动游标
				ivCursor.startAnimation(anim);//启动 动画，参考 day08_01
				// 设置当前位置
				currentPosition = position;
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
		addListener();
	}
}