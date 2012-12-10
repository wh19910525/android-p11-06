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
		// 加载位图对象
		Options opts = new Options();
		opts.inSampleSize = 4;//设置收缩比例
		Bitmap bm1 = BitmapFactory.decodeFile("/mnt/sdcard/zgj.jpg");
		Log.i("info", "bm=" + bm1);
//		Bitmap bm2 = Bitmap.createBitmap(bm1, 0, bm1.getHeight() / 2,
//				bm1.getWidth() / 2, bm1.getHeight() / 2);
		
		/**createBitmap(bm1, 0, 0, bm1.getWidth(), bm1.getHeight(), matrix, true)
		 * 
		 * 从原始位图剪切图像，这是一种高级的方式。可以用Matrix(矩阵)来实现旋转等高级方式截图
                              参数说明：
　　                 Bitmap source：要从中截图的原始位图
　　                int x:起始x坐标
　　                int y：起始y坐标
          int width：要截的图的宽度
          int height：要截的图的宽度*/
		
		Matrix matrix = new Matrix();
		matrix.setRotate(45);//设置图片倾斜的角度
		matrix.setScale(0.3f, 0.3f);//设置图片的收缩比例
		Bitmap bm2 = Bitmap.createBitmap(bm1, 0, 0, bm1.getWidth(), bm1.getHeight(), matrix, true);
//		Bitmap.createBitmap(800, 600, Config.ARGB_8888);
		Log.i("info", "bm=" + bm2);
		// 显示位图
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