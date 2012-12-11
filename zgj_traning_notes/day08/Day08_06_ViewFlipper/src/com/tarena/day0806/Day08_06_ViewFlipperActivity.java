package com.tarena.day0806;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.ViewFlipper;

import com.tarena.biz.Biz;

public class Day08_06_ViewFlipperActivity extends Activity {

	private ViewFlipper vfContainer;
	private Biz biz;
	private LayoutInflater inflater;
	private GestureDetector detector;
	
	private class GestureListener extends SimpleOnGestureListener {
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			if (e1.getX() - e2.getX() > 0) {
				// 下一页
				vfContainer.showNext();
			} else if (e2.getX() - e1.getX() > 0) {
				// 上一页
				vfContainer.showPrevious();
			}

			// TODO Auto-generated method stub
			return super.onFling(e1, e2, velocityX, velocityY);
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		biz = new Biz();
		inflater = LayoutInflater.from(this);//
		detector = new GestureDetector(new GestureListener());
		setupView();
	}
	
	private void setupView() {
		vfContainer = (ViewFlipper) findViewById(R.id.vfContainer);
		// 设置切换动画
		vfContainer.setInAnimation(this, android.R.anim.fade_in);
		vfContainer.setOutAnimation(this, android.R.anim.fade_out);
		
		// 添加child
		ListView child = (ListView) inflater.inflate(R.layout.child, null);//
		child.setAdapter(new SimpleAdapter(this, biz.getStudents(),//对比 day_06_05
				R.layout.item, new String[] { "id", "name", "sex", "age" },
				new int[] { R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4 }));
		vfContainer.addView(child);

		child = (ListView) inflater.inflate(R.layout.child, null);
		child.setAdapter(new SimpleAdapter(this, biz.getCourses(),
				R.layout.item, new String[] { "id", "name", "teacher" },
				new int[] { R.id.tv1, R.id.tv2, R.id.tv3 }));
		vfContainer.addView(child);

		child = (ListView) inflater.inflate(R.layout.child, null);
		child.setAdapter(new SimpleAdapter(this, biz.getScores(),
				R.layout.item, new String[] { "id", "stuName", "courseName", "score" },
				new int[] { R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4 }));
		vfContainer.addView(child);
	}
	
	/**
	 * 一般情况下,只要重写onTouchEvent方法就可以监听触碰事件,但在本例中因 ListView自身有消费事件的能力,不会执行本例重写的
	 * onTouchEvent方法。
	 * 所以需要在事件派发的方法中,先让它执行重写的onTouchEvent方法,这样手势检测器 GestureDetector对象才可以检测到
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		onTouchEvent(ev);//如何 才能 知道 一个控件 是否 具有 自身消费事件的能力；参考 day07_10
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		Log.i("info", "onTouchEvent");
		detector.onTouchEvent(event);//
		return super.onTouchEvent(event);
	}
}