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
				// ��һҳ
				vfContainer.showNext();
			} else if (e2.getX() - e1.getX() > 0) {
				// ��һҳ
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
		// �����л�����
		vfContainer.setInAnimation(this, android.R.anim.fade_in);
		vfContainer.setOutAnimation(this, android.R.anim.fade_out);
		
		// ���child
		ListView child = (ListView) inflater.inflate(R.layout.child, null);//
		child.setAdapter(new SimpleAdapter(this, biz.getStudents(),//�Ա� day_06_05
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
	 * һ�������,ֻҪ��дonTouchEvent�����Ϳ��Լ��������¼�,���ڱ������� ListView�����������¼�������,����ִ�б�����д��
	 * onTouchEvent������
	 * ������Ҫ���¼��ɷ��ķ�����,������ִ����д��onTouchEvent����,�������Ƽ���� GestureDetector����ſ��Լ�⵽
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		onTouchEvent(ev);//��� ���� ֪�� һ���ؼ� �Ƿ� ���� ���������¼����������ο� day07_10
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