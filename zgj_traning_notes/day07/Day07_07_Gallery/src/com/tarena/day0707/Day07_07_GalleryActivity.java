package com.tarena.day0707;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageView;

import com.tarena.biz.ImageBiz;
import com.tarena.day0707.adapter.ImageAdapter;
import com.tarena.entity.ImageInfo;
import com.tarena.utils.BitmapUtils;

public class Day07_07_GalleryActivity extends Activity {
	private static final String DIR = "/mnt/sdcard/imags/";
	private Gallery galThumb;
	private ImageView ivPic;
	private ImageBiz biz;
	private ImageAdapter adapter;

	private void setupView() {
		galThumb = (Gallery) findViewById(R.id.galThumbnails);
		adapter = new ImageAdapter(this, biz.getImages(DIR));
		galThumb.setAdapter(adapter);

		ivPic = (ImageView) findViewById(R.id.ivPic);
	}

	private void addListener() {
		
		/***��Gallery���������� setOnItemSelectedListener(OnItemSelectedListener listener)*/
		
		galThumb.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				ImageInfo img = (ImageInfo) adapter.getItem(position);
				String path = DIR + img.getTitle();
				Bitmap bm = BitmapUtils.loadBitmap(path, 5);
				if (bm != null)
					ivPic.setImageBitmap(bm);//���ͼƬ 
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
	}
}