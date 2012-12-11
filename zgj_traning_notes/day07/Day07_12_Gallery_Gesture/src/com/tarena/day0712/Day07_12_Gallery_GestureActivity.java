package com.tarena.day0712;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageView;

import com.tarena.biz.ImageBiz;
import com.tarena.day0712.adapter.ImageAdapter;
import com.tarena.entity.ImageInfo;
import com.tarena.utils.BitmapUtils;

public class Day07_12_Gallery_GestureActivity extends Activity {

	private static final String DIR = "/mnt/sdcard/imags/";
	private Gallery galThumb;
	private ImageView ivPic;
	private ImageBiz biz;
	private ImageAdapter adapter;
	private GestureDetector detector;

	private void setupView() {
		//获取 Gallery控件 的引用
		galThumb = (Gallery) findViewById(R.id.galThumbnails);
		//在getImages()方法里获取数据集，并且实例化adapter
		adapter = new ImageAdapter(this, biz.getImages(DIR));
		//设置Gallery的adapter
		galThumb.setAdapter(adapter);

		ivPic = (ImageView) findViewById(R.id.ivPic);
	}

	private void addListener() {
		
		/***给Gallery设置侦听器 setOnItemSelectedListener(OnItemSelectedListener listener)*/
		
		galThumb.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				// TODO Auto-generated method stub
				// 获取图片信息
				ImageInfo img = (ImageInfo) adapter.getItem(position);
				String path = DIR + img.getTitle();
				Bitmap bm = BitmapUtils.loadBitmap(path, 2);
				if (bm != null)
					ivPic.setImageBitmap(bm);// 设置显示图片 
				else
					ivPic.setImageResource(R.drawable.ic_launcher);//若所加载的图片为空,则使用默认的资源图片
			}

			@Override
			//此方法虽没有重写,但它是指Gallery控件如没有一个被选中,则执行此方法
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
			}
		});
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		biz = new ImageBiz();
		setupView();
		addListener();
		detector = new GestureDetector(new MyGestureListener());//
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return detector.onTouchEvent(event);
	}
	
	private class MyGestureListener extends SimpleOnGestureListener {

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			// TODO Auto-generated method stub
			int currentPosition = galThumb.getSelectedItemPosition();//当前所选择的 的 图片位置，从0开始；
			if (e1.getX() - e2.getX() > 50 && Math.abs(velocityX) > 30) {//如何 计算
				// 从右向左 下一幅
				if(++currentPosition==galThumb.getCount()){
					currentPosition = 0;
				}
			} else if (e2.getX() - e1.getX() > 50 && Math.abs(velocityY) > 30) {
				// 从左向右，上一幅
				if(--currentPosition<0){
					currentPosition = galThumb.getCount()-1;
				}
			}
			
			galThumb.setSelection(currentPosition);//如何 就能加载到ImageView里
			return super.onFling(e1, e2, velocityX, velocityY);
		}
	}
}