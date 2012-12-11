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
		iv.setTag("p01");//setTag(Onbect)��ʾ��View���һ����������ݣ��Ժ������getTag()���������ȡ������
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
		// ���ò��ֲ���
		LayoutParams params = new LayoutParams(0, 60, 1.0f);
		tv.setLayoutParams(params);
		// �����ı����ݺ͸�ʽ
		tv.setText(text);
		tv.setTextSize(25);
		tv.setTextColor(Color.RED);
		tv.setGravity(Gravity.CENTER);

		return tv;
	}

	private void setupView() {
		// ��ʼ��pager
		pager = (ViewPager) findViewById(R.id.pager);
		views = getViews();
		adapter = new ViewAdapter(views);//
		pager.setAdapter(adapter);

		// ��ʼ��imageView
		ivCursor = (ImageView) findViewById(R.id.ivCursor);
		
		// ��ȡ�ֻ���Ļ��ȵ�����ֵ�� ����ؼ��Ŀ�
		int width = getResources().getDisplayMetrics().widthPixels / views.size();
		// ���ÿؼ��Ĳ��ֲ���
		LayoutParams params = new LayoutParams(width, 5);//������ �� �� ��
		ivCursor.setLayoutParams(params);

		// ��ʼ��tas
		tabs = (LinearLayout) findViewById(R.id.layoutTabs);
		for (View view : views) {
			TextView tab = createTab(view.getTag().toString());//ͨ�� getTag()��ȡ getViews() �����õ� setTag��
			tabs.addView(tab);
		}
	}

	private void addListener() {
		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				// ���ö���
				int width = ivCursor.getWidth();//��� �����ʲô�ã�
				TranslateAnimation anim = 
						new TranslateAnimation(currentPosition * width, position * width, 0, 0);//ǰ����������ʲô�ã�
				anim.setDuration(1000);//�ƶ� ��Ҫ�� ʱ��
				anim.setFillAfter(true);//��ʾ �ƶ���� ͼ��
				// �ƶ��α�
				ivCursor.startAnimation(anim);//���� �������ο� day08_01
				// ���õ�ǰλ��
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