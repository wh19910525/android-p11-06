package com.tarena.day0901;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class Day09_01_ViewPagerActivity extends Activity {
	
	private ViewPager pager;

	private ArrayList<View> getViews() {
		ArrayList<View> views = new ArrayList<View>();
		ImageView iv = new ImageView(this);
		iv.setScaleType(ScaleType.FIT_CENTER);
		iv.setImageResource(R.drawable.p01);
		views.add(iv);

		iv = new ImageView(this);
		iv.setScaleType(ScaleType.FIT_CENTER);
		iv.setImageResource(R.drawable.p02);
		iv.setTag("p01");
		views.add(iv);

		iv = new ImageView(this);
		iv.setScaleType(ScaleType.FIT_CENTER);
		iv.setImageResource(R.drawable.p03);
		views.add(iv);

		iv = new ImageView(this);
		iv.setScaleType(ScaleType.FIT_CENTER);
		iv.setImageResource(R.drawable.ic_launcher);
		views.add(iv);

		return views;
	}

	private void setupView() {
		pager = (ViewPager) findViewById(R.id.pager);
		pager.setAdapter(new MyAdapter(getViews()));//
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}

	class MyAdapter extends PagerAdapter {//
		private ArrayList<View> views;

		public MyAdapter(ArrayList<View> views) {
			this.views = views;
		}

		@Override
		public void destroyItem(View pager, int position, Object arg2) {
			// TODO Auto-generated method stub
			Log.i("info", "destroyItem");
			// 根据位置从集合中获取要移除的view对象
			View view = views.get(position);
			// 从pager中移除该view
			((ViewPager) pager).removeView(view);
		}

		@Override
		public void finishUpdate(View arg0) {
			// TODO Auto-generated method stub
			Log.i("info", "finishUpdate");
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			Log.i("info", "getCount");
			return views.size();
		}

		@Override
		public Object instantiateItem(View pager, int position) {
			// TODO Auto-generated method stub
			Log.i("info", "instantiateItem");
			// 根据索引从集合中获取view对象
			View view = views.get(position);
			// 将view对象添加到pager
			((ViewPager) pager).addView(view);
			return view;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			Log.i("info", "isViewFromObject");
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
			// TODO Auto-generated method stub
		}

		@Override
		public Parcelable saveState() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
			// TODO Auto-generated method stub
			Log.i("info", "startUpdate");
		}
	}
}