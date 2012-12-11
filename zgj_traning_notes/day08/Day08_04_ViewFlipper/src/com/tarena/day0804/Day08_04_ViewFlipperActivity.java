package com.tarena.day0804;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ViewFlipper;

public class Day08_04_ViewFlipperActivity extends Activity {
	
	private ViewFlipper vfContainer;
	private GestureDetector detector;

	private void setupView() {
		vfContainer = (ViewFlipper) findViewById(R.id.vfContainer);
		vfContainer.setInAnimation(this, android.R.anim.fade_in);
		vfContainer.setOutAnimation(this, android.R.anim.fade_out);
	}

	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btnPrevious:// 上一页
			vfContainer.showPrevious();
			break;

		case R.id.btnNext:// 下一页
			vfContainer.showNext();
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(1, 1, 1, "退出").setIcon(
				android.R.drawable.ic_menu_close_clear_cancel);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case 1:
			finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private class GestureListener extends SimpleOnGestureListener {
		@Override
		public boolean onDoubleTap(MotionEvent e) {
			if (vfContainer.isFlipping())
				vfContainer.stopFlipping();
			else
				vfContainer.startFlipping();

			// TODO Auto-generated method stub
			return super.onDoubleTap(e);
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			if (e1.getX() - e2.getX() > 20) {
				// 下一页
				vfContainer.showNext();
			} else if (e2.getX() - e1.getX() > 20) {
				// 上一页
				vfContainer.showPrevious();
			}

			// TODO Auto-generated method stub
			return super.onFling(e1, e2, velocityX, velocityY);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return detector.onTouchEvent(event);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
		detector = new GestureDetector(new GestureListener());
	}
}