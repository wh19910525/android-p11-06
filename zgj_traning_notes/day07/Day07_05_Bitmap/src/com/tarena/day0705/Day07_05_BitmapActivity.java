package com.tarena.day0705;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Day07_05_BitmapActivity extends Activity {
	private ImageView ivPic;

	private void setupView() {
		ivPic = (ImageView) findViewById(R.id.ivPic);
	}

	public void doClick(View v) {

		// ����λͼ
		/**��һ��*/
		 Bitmap bm = loadBitmap("/mnt/sdcard/p01.jpg");

		/**�ڶ���*/
		// InputStream is = new FileInputStream("/mnt/sdcard/imgs/p01.jpg");
		// ByteArrayOutputStream out = new ByteArrayOutputStream();
		// int len = -1;
		// byte[] buffer = new byte[1024];
		// while ((len = is.read(buffer)) != -1) {
		// out.write(buffer, 0, len);
		// out.flush();
		// }
		// out.close();
		// is.close();
		// Bitmap bm = loadBitmap(out.toByteArray());
		/**������*/
		// Bitmap bm = loadBitmap(is);

		/**������*/
//		Bitmap bm = loadBitmap(R.drawable.p01);

		// ��ʾ��ImageView��
		ivPic.setImageBitmap(bm);

	}
	
//	*******************����ն�Ӧ�����´���******************************

	/**������*/
	private Bitmap loadBitmap(int res) {
		return BitmapFactory.decodeResource(getResources(), res);
	}

	/**�ڶ���*/
	private Bitmap loadBitmap(byte[] data) {
		return BitmapFactory.decodeByteArray(data, 0, data.length);
	}

	/***������*/
	private Bitmap loadBitmap(InputStream is) {
		return BitmapFactory.decodeStream(is);
	}

	private Bitmap loadBitmap(String path) {//��һ��
		Options opts = new Options();
//		 opts.inSampleSize = 2;
		// ���ý�����ͼƬ�߽���Ϣ
		opts.inJustDecodeBounds = true;
		// ����ͼƬ�ı߽���Ϣ
		BitmapFactory.decodeFile(path, opts);
		// ������������
		int x = opts.outWidth / 100;
		int y = opts.outHeight / 100;
		int scale = x > y ? x : y;
		// ���ü��ر߽���Ϣ����Ϊfalse
		opts.inJustDecodeBounds = false;
		// ������������
		opts.inSampleSize = scale;
		// ����ͼƬ
		Bitmap bm = BitmapFactory.decodeFile(path, opts);

		return bm;
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
		// BitmapFactory.decodeByteArray(data, offset, length)
		// BitmapFactory.decodeFile(pathName)
		// BitmapFactory.decodeResource(res, id)
		// BitmapFactory.decodeStream(is)

	}
}