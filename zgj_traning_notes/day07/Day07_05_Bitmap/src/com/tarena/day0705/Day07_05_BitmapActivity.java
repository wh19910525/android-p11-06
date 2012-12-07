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

		// 加载位图
		/**第一种*/
		 Bitmap bm = loadBitmap("/mnt/sdcard/p01.jpg");

		/**第二种*/
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
		/**第三种*/
		// Bitmap bm = loadBitmap(is);

		/**第四种*/
//		Bitmap bm = loadBitmap(R.drawable.p01);

		// 显示在ImageView中
		ivPic.setImageBitmap(bm);

	}
	
//	*******************请参照对应的上下代码******************************

	/**第四种*/
	private Bitmap loadBitmap(int res) {
		return BitmapFactory.decodeResource(getResources(), res);
	}

	/**第二种*/
	private Bitmap loadBitmap(byte[] data) {
		return BitmapFactory.decodeByteArray(data, 0, data.length);
	}

	/***第三种*/
	private Bitmap loadBitmap(InputStream is) {
		return BitmapFactory.decodeStream(is);
	}

	private Bitmap loadBitmap(String path) {//第一种
		Options opts = new Options();
//		 opts.inSampleSize = 2;
		// 设置仅加载图片边界信息
		opts.inJustDecodeBounds = true;
		// 加载图片的边界信息
		BitmapFactory.decodeFile(path, opts);
		// 计算收缩比例
		int x = opts.outWidth / 100;
		int y = opts.outHeight / 100;
		int scale = x > y ? x : y;
		// 设置加载边界信息属性为false
		opts.inJustDecodeBounds = false;
		// 设置收缩比例
		opts.inSampleSize = scale;
		// 加载图片
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