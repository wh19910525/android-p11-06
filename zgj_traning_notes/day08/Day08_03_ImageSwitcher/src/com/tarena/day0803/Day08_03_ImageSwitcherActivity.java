package com.tarena.day0803;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Toast;
import android.widget.ViewSwitcher.ViewFactory;

import com.tarena.biz.ImageBiz;
import com.tarena.entity.ImageInfo;
import com.tarena.utils.BitmapUtils;

public class Day08_03_ImageSwitcherActivity extends Activity {
	
	private static final String DIR = "/mnt/sdcard/imags/";
	private ImageSwitcher isPic;
	private Gallery galThumbs;
	private ImageBiz biz;
	private ImageAdapter adapter;
	private LayoutInflater inflater;
	private int currentPosition=-1;
	private GestureDetector detcor;

	private void setupView() {
		//获取 Gallery控件 的引用
		galThumbs = (Gallery) findViewById(R.id.galThumb);
		//在getImageInfos()方法里获取数据集，并且实例化adapter
		adapter = new ImageAdapter(this, biz.getImageInfos(DIR));
		//设置Gallery的adapter
		galThumbs.setAdapter(adapter);

		//获取 imageSwitcher控件 的引用
		isPic = (ImageSwitcher) findViewById(R.id.isPic);
		
		isPic.setFactory(new ViewFactory() {// 实现并设置工厂内部接口的makeView方法，用来显示视图。

			public View makeView() {
				// TODO Auto-generated method stub

				ImageView iv = new ImageView(Day08_03_ImageSwitcherActivity.this);
				LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT);
				iv.setLayoutParams(params);
				iv.setScaleType(ScaleType.FIT_CENTER);
				return iv;
			}
		});
	}

	private void addListener() {
		/***给Gallery设置侦听器 setOnItemSelectedListener(OnItemSelectedListener listener)*/
		galThumbs.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				// TODO Auto-generated method stub
				// 获取图片信息
				ImageInfo img = (ImageInfo) adapter.getItem(position);//根据position位置，获取当前图片
				Bitmap bm = BitmapUtils.loadBitmap(DIR + img.getTitle(), 2);

				// 设置切换动画
				if (position > currentPosition) {
					// 下一幅
					isPic.setInAnimation(Day08_03_ImageSwitcherActivity.this, R.anim.right_in);// 设置切入动画，很重要
					isPic.setOutAnimation(Day08_03_ImageSwitcherActivity.this, R.anim.left_out);// 设置切出动画，很重要
					// 设置显示图片
					isPic.setImageDrawable(new BitmapDrawable(bm));//注意 这里 是给 ImageSwitcher控件 显示

				} else if (position < currentPosition) {
					// 上一幅
					isPic.setInAnimation(Day08_03_ImageSwitcherActivity.this, R.anim.left_in);
					isPic.setOutAnimation(Day08_03_ImageSwitcherActivity.this, R.anim.right_out);
					// 设置显示图片
					isPic.setImageDrawable(new BitmapDrawable(bm));
				}
				currentPosition = position;
				Toast.makeText(Day08_03_ImageSwitcherActivity.this, "currentPosition = " + currentPosition, 3000).show();
			}

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

		detcor = new GestureDetector(new GestureListener());
		setupView();
		addListener();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return detcor.onTouchEvent(event);
	}

	private class GestureListener extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			// TODO Auto-generated method stub
			int index = 0;
			if (e1.getX() - e2.getX() > 20 && Math.abs(velocityX) > 50) {//如何 计算？
				// 下一幅
				Log.i("info", "下一幅,currentPosition="+currentPosition);
				index = currentPosition+1;
				if (index == galThumbs.getCount()) {//获取 Gallery 里  图片的 数量；
					Toast.makeText(Day08_03_ImageSwitcherActivity.this, "已经是最后一幅图片", 3000).show();
					index = galThumbs.getCount() - 1;
				}
			} else if (e2.getX() - e1.getX() > 20 && Math.abs(velocityX) > 50) {
				// 上一幅
				Log.i("info", "上一幅,currentPosition="+currentPosition);
				index = currentPosition-1;
				if (index < 0) {
					Toast.makeText(Day08_03_ImageSwitcherActivity.this, "已经是第一幅图片", 3000).show();
					index = 0;
					Log.i("info", "currentPosition="+currentPosition+",index="+index);
				}
			}

			galThumbs.setSelection(index);//执行完 这个 方法 之后，会自动 选择 Gallery里，index 索引的 图片，从而 就会 触发 Gallery 设置的侦听器；
			return super.onFling(e1, e2, velocityX, velocityY);
		}
	}
}