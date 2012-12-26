package com.tarena.day1803;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewSwitcher.ViewFactory;

import com.tarena.biz.ImageBiz;
import com.tarena.day1803.adapter.ImageAdapter;
import com.tarena.entity.ImageInfo;

public class Day18_03_MSActivity extends Activity {
	
	private static final int MENU_OPTS_FLUSH = 1;
	private static final int MENU_OPTS_EXIT = 2;

	private ImageSwitcher isPic;
	private Gallery galThumbs;
	private ImageBiz biz;
	private ImageAdapter adapter;
	private AlertDialog dialog;
	private GestureDetector detector;
	private InnerReceiver receiver;
	
	private void setupView() {
		isPic = (ImageSwitcher) findViewById(R.id.isPic);
		isPic.setFactory(new ViewFactory() {

			@Override
			public View makeView() {
				ImageView iv = new ImageView(Day18_03_MSActivity.this);
				LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
				iv.setLayoutParams(params);
				iv.setScaleType(ScaleType.FIT_CENTER);
				return iv;
			}
		});
		isPic.setInAnimation(this, android.R.anim.fade_in);
		isPic.setOutAnimation(this, android.R.anim.fade_out);

		galThumbs = (Gallery) findViewById(R.id.galThumbs);
		adapter = new ImageAdapter(this, biz.getImageInfos());
		galThumbs.setAdapter(adapter);

		dialog = new Builder(this).setTitle("提示").setMessage("正在扫描sd卡,请稍候...")
				.setCancelable(false).create();
	}

	private void addListener() {
		galThumbs.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				// TODO Auto-generated method stub
				ImageInfo img = (ImageInfo) adapter.getItem(position);
				Bitmap bm = biz.getBitmap(img.getId());
				isPic.setImageDrawable(new BitmapDrawable(bm));
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);//在Activity结束时解除对广播的注册
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return detector.onTouchEvent(event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, MENU_OPTS_FLUSH, 1, "刷新");
		menu.add(1, MENU_OPTS_EXIT, 2, "退出");
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_OPTS_FLUSH:
			Log.i("info", "ssssss");
			
			/**Intent.ACTION_MEDIA_MOUNTED/UNMOUNTED *
			 * 分别表示当新的外部存储媒体成功的添加到设备或从设备上移除
			 * 在此代码中只是假设发生这样的事件，并发出广播
			 */
			Intent intent = new Intent(Intent.ACTION_MEDIA_MOUNTED);
			
			//Uri.parse(String str)对字符串进行解析，并解析成Uri
			intent.setData(Uri.parse("file://" + Environment.getExternalStorageDirectory()));
			//File getExternalStorageDirectory(),返回外部存储的根目录(/mnt/sdcard)
			  
			sendBroadcast(intent);//
			break;

		case MENU_OPTS_EXIT:
			finish();
			break;
		}
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}

	private class MyGestureListener extends SimpleOnGestureListener {

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			int currentPosition = galThumbs.getSelectedItemPosition();
			Log.i("info", "count=" + galThumbs.getCount());
			if (e1.getX() - e2.getX() > 20 && Math.abs(velocityX) > 100) {
				// 下一幅
				if (++currentPosition == galThumbs.getCount()) {
					Log.i("info", "currentPosition=" + currentPosition);
					currentPosition = 0;
				}
			} else if (e2.getX() - e1.getX() > 20 && Math.abs(velocityX) > 100) {
				// 上一幅
				if (--currentPosition < 0) {
					currentPosition = galThumbs.getCount() - 1;
				}
			}

			galThumbs.setSelection(currentPosition);
			return super.onFling(e1, e2, velocityX, velocityY);
		}
	}

	private class InnerReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			if (Intent.ACTION_MEDIA_SCANNER_STARTED.equals(action)) {
				dialog.show();
			} else if (Intent.ACTION_MEDIA_SCANNER_FINISHED.equals(action)) {
				dialog.dismiss();
				adapter.changeData(biz.getImageInfos());
			}
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		biz = new ImageBiz(this);
		setupView();
		addListener();
		detector = new GestureDetector(new MyGestureListener());
		receiver = new InnerReceiver();
		
		//在代码中注册BroadcastReciver属于动态注册
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_MEDIA_SCANNER_STARTED);
		filter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
		filter.addDataScheme("file");
		registerReceiver(receiver, filter);//对指定广播进行注册
	}

}