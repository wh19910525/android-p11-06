package com.tarena.day0706;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class Day07_06_BitmapActivity extends Activity {
	
	private ImageView ivPic;

	private void setupView() {
		ivPic = (ImageView) findViewById(R.id.ivPic);
	}

	public void doClick(View v) {
		// ����λͼ����
		Options opts = new Options();
		opts.inSampleSize = 4;//������������
		Bitmap bm1 = BitmapFactory.decodeFile("/mnt/sdcard/zgj.jpg");
		Log.i("info", "bm=" + bm1);
//		Bitmap bm2 = Bitmap.createBitmap(bm1, 0, bm1.getHeight() / 2,
//				bm1.getWidth() / 2, bm1.getHeight() / 2);
		
		/**createBitmap(bm1, 0, 0, bm1.getWidth(), bm1.getHeight(), matrix, true)
		 * 
		 * ��ԭʼλͼ����ͼ������һ�ָ߼��ķ�ʽ��������Matrix(����)��ʵ����ת�ȸ߼���ʽ��ͼ
                              ����˵����
����                 Bitmap source��Ҫ���н�ͼ��ԭʼλͼ
����                int x:��ʼx����
����                int y����ʼy����
          int width��Ҫ�ص�ͼ�Ŀ��
          int height��Ҫ�ص�ͼ�Ŀ��*/
		
		Matrix matrix = new Matrix();
		matrix.setRotate(45);//����ͼƬ��б�ĽǶ�
		matrix.setScale(0.3f, 0.3f);//����ͼƬ����������
		Bitmap bm2 = Bitmap.createBitmap(bm1, 0, 0, bm1.getWidth(), bm1.getHeight(), matrix, true);
//		Bitmap.createBitmap(800, 600, Config.ARGB_8888);
		Log.i("info", "bm=" + bm2);
		// ��ʾλͼ
		ivPic.setImageBitmap(bm2);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}
}