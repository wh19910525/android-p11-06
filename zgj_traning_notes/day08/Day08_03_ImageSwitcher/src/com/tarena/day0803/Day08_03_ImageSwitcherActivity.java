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
		//��ȡ Gallery�ؼ� ������
		galThumbs = (Gallery) findViewById(R.id.galThumb);
		//��getImageInfos()�������ȡ���ݼ�������ʵ����adapter
		adapter = new ImageAdapter(this, biz.getImageInfos(DIR));
		//����Gallery��adapter
		galThumbs.setAdapter(adapter);

		//��ȡ imageSwitcher�ؼ� ������
		isPic = (ImageSwitcher) findViewById(R.id.isPic);
		
		isPic.setFactory(new ViewFactory() {// ʵ�ֲ����ù����ڲ��ӿڵ�makeView������������ʾ��ͼ��

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
		/***��Gallery���������� setOnItemSelectedListener(OnItemSelectedListener listener)*/
		galThumbs.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				// TODO Auto-generated method stub
				// ��ȡͼƬ��Ϣ
				ImageInfo img = (ImageInfo) adapter.getItem(position);//����positionλ�ã���ȡ��ǰͼƬ
				Bitmap bm = BitmapUtils.loadBitmap(DIR + img.getTitle(), 2);

				// �����л�����
				if (position > currentPosition) {
					// ��һ��
					isPic.setInAnimation(Day08_03_ImageSwitcherActivity.this, R.anim.right_in);// �������붯��������Ҫ
					isPic.setOutAnimation(Day08_03_ImageSwitcherActivity.this, R.anim.left_out);// �����г�����������Ҫ
					// ������ʾͼƬ
					isPic.setImageDrawable(new BitmapDrawable(bm));//ע�� ���� �Ǹ� ImageSwitcher�ؼ� ��ʾ

				} else if (position < currentPosition) {
					// ��һ��
					isPic.setInAnimation(Day08_03_ImageSwitcherActivity.this, R.anim.left_in);
					isPic.setOutAnimation(Day08_03_ImageSwitcherActivity.this, R.anim.right_out);
					// ������ʾͼƬ
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
			if (e1.getX() - e2.getX() > 20 && Math.abs(velocityX) > 50) {//��� ���㣿
				// ��һ��
				Log.i("info", "��һ��,currentPosition="+currentPosition);
				index = currentPosition+1;
				if (index == galThumbs.getCount()) {//��ȡ Gallery ��  ͼƬ�� ������
					Toast.makeText(Day08_03_ImageSwitcherActivity.this, "�Ѿ������һ��ͼƬ", 3000).show();
					index = galThumbs.getCount() - 1;
				}
			} else if (e2.getX() - e1.getX() > 20 && Math.abs(velocityX) > 50) {
				// ��һ��
				Log.i("info", "��һ��,currentPosition="+currentPosition);
				index = currentPosition-1;
				if (index < 0) {
					Toast.makeText(Day08_03_ImageSwitcherActivity.this, "�Ѿ��ǵ�һ��ͼƬ", 3000).show();
					index = 0;
					Log.i("info", "currentPosition="+currentPosition+",index="+index);
				}
			}

			galThumbs.setSelection(index);//ִ���� ��� ���� ֮�󣬻��Զ� ѡ�� Gallery�index ������ ͼƬ���Ӷ� �ͻ� ���� Gallery ���õ���������
			return super.onFling(e1, e2, velocityX, velocityY);
		}
	}
}