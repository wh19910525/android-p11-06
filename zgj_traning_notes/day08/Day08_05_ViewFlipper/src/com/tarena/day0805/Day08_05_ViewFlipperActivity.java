package com.tarena.day0805;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class Day08_05_ViewFlipperActivity extends Activity {

	private ViewFlipper vfContainer;
	private LayoutInflater inflater;
	private GestureDetector detector;

	private void setupView() {
		// ��������
		vfContainer = (ViewFlipper) findViewById(R.id.vfContainer);
		//�����л�����
		vfContainer.setInAnimation(this,android.R.anim.fade_in);
		vfContainer.setOutAnimation(this,android.R.anim.fade_out);
		
		// �����������child
		View child = inflater.inflate(R.layout.child, null);//
		ImageView iv = (ImageView) child.findViewById(R.id.ivTest);
		TextView tv = (TextView) child.findViewById(R.id.tvTest);
		iv.setImageResource(R.drawable.p01);
		tv.setText("p01");
		vfContainer.addView(child);//

		child = inflater.inflate(R.layout.child, null);//Ϊʲô����Ҫÿ��ִ�� ��һ�仰
		iv = (ImageView) child.findViewById(R.id.ivTest);
		tv = (TextView) child.findViewById(R.id.tvTest);
		iv.setImageResource(R.drawable.p02);
		tv.setText("p02");
		vfContainer.addView(child);

		child = inflater.inflate(R.layout.child, null);
		iv = (ImageView) child.findViewById(R.id.ivTest);
		tv = (TextView) child.findViewById(R.id.tvTest);
		iv.setImageResource(R.drawable.p03);
		tv.setText("p03");
		vfContainer.addView(child);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		inflater = LayoutInflater.from(this);//
		detector = new GestureDetector(new GestureListener());
		setupView();
	}
	
	private class GestureListener extends SimpleOnGestureListener {
		@Override
		public boolean onDoubleTap(MotionEvent e) {//˫�� ִ�� �˺���
			if (vfContainer.isFlipping())//�ж��Ƿ� ���� �л�
				vfContainer.stopFlipping();
			else
				vfContainer.startFlipping();
			// TODO Auto-generated method stub
			return super.onDoubleTap(e);
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			if (e1.getX() - e2.getX() > 20) {
				// ��һҳ
				vfContainer.showNext();
			} else if (e2.getX() - e1.getX() > 20) {
				// ��һҳ
				vfContainer.showPrevious();
			}
			// TODO Auto-generated method stub
			return super.onFling(e1, e2, velocityX, velocityY);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		detector.onTouchEvent(event);
		return super.onTouchEvent(event);
	}
}