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
		//��ȡ Gallery�ؼ� ������
		galThumb = (Gallery) findViewById(R.id.galThumbnails);
		//��getImages()�������ȡ���ݼ�������ʵ����adapter
		adapter = new ImageAdapter(this, biz.getImages(DIR));
		//����Gallery��adapter
		galThumb.setAdapter(adapter);

		ivPic = (ImageView) findViewById(R.id.ivPic);
	}

	private void addListener() {
		
		/***��Gallery���������� setOnItemSelectedListener(OnItemSelectedListener listener)*/
		
		galThumb.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				// TODO Auto-generated method stub
				// ��ȡͼƬ��Ϣ
				ImageInfo img = (ImageInfo) adapter.getItem(position);
				String path = DIR + img.getTitle();
				Bitmap bm = BitmapUtils.loadBitmap(path, 2);
				if (bm != null)
					ivPic.setImageBitmap(bm);// ������ʾͼƬ 
				else
					ivPic.setImageResource(R.drawable.ic_launcher);//�������ص�ͼƬΪ��,��ʹ��Ĭ�ϵ���ԴͼƬ
			}

			@Override
			//�˷�����û����д,������ָGallery�ؼ���û��һ����ѡ��,��ִ�д˷���
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
			int currentPosition = galThumb.getSelectedItemPosition();//��ǰ��ѡ��� �� ͼƬλ�ã���0��ʼ��
			if (e1.getX() - e2.getX() > 50 && Math.abs(velocityX) > 30) {//��� ����
				// �������� ��һ��
				if(++currentPosition==galThumb.getCount()){
					currentPosition = 0;
				}
			} else if (e2.getX() - e1.getX() > 50 && Math.abs(velocityY) > 30) {
				// �������ң���һ��
				if(--currentPosition<0){
					currentPosition = galThumb.getCount()-1;
				}
			}
			
			galThumb.setSelection(currentPosition);//��� ���ܼ��ص�ImageView��
			return super.onFling(e1, e2, velocityX, velocityY);
		}
	}
}