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
	private class MyGestureListener extends SimpleOnGestureListener {

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// TODO Auto-generated method stub
			int currentPosition = galThumb.getSelectedItemPosition();
			if (e1.getX() - e2.getX() > 50 && Math.abs(velocityX) > 30) {
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
			
			galThumb.setSelection(currentPosition);
			return super.onFling(e1, e2, velocityX, velocityY);
		}

	}

	private static final String DIR = "/mnt/sdcard/imgs/";
	private Gallery galThumb;
	private ImageView ivPic;
	private ImageBiz biz;
	private ImageAdapter adapter;
	private GestureDetector detector;

	private void setupView() {
		galThumb = (Gallery) findViewById(R.id.galThumbnails);
		adapter = new ImageAdapter(this, biz.getImages(DIR));
		galThumb.setAdapter(adapter);

		ivPic = (ImageView) findViewById(R.id.ivPic);
	}

	private void addListener() {
		galThumb.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				ImageInfo img = (ImageInfo) adapter.getItem(position);
				String path = DIR + img.getTitle();
				Bitmap bm = BitmapUtils.loadBitmap(path, 2);
				if (bm != null)
					ivPic.setImageBitmap(bm);
				else
					ivPic.setImageResource(R.drawable.ic_launcher);
			}

			@Override
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
		detector = new GestureDetector(new MyGestureListener());
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return detector.onTouchEvent(event);
	}
}