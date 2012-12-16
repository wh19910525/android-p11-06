package com.tarena.day1101;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.tarena.utils.HttpUtils;

public class Day11_01_HandlerActivity extends Activity {
	
	private static String uri = "http://192.168.1.101:8080/test/imgs/p2.jpg";
	private ImageView ivPic;
	private Handler handler;//

	private void setupView() {
		ivPic = (ImageView) findViewById(R.id.ivPic);
	}

	public void doClick(View v) {
		new Thread() {
			public void run() {
				Bitmap bm = null;
				try {
					Message msg = Message.obtain(handler, 1);//
					 handler.sendMessage(msg);
					//msg.sendToTarget();//可以用这一句 替换 上边的话
					
					// 加载图片
					HttpEntity entity = HttpUtils.getEntity(uri, null, HttpUtils.METHOD_GET);
					InputStream in = HttpUtils.getStream(entity);
					bm = BitmapFactory.decodeStream(in);

					// 发送消息回主线程
					//msg = Message.obtain(handler, 0, bm);//这一句可以 替换 以下 四句
					 msg = Message.obtain();
					 msg.what = 0;
					 msg.obj = bm;
					 msg.setTarget(handler);//
					 
					handler.sendMessage(msg);
					//msg.sendToTarget();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		}.start();
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();

		Callback callback = new Callback() {

			@Override
			public boolean handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch (msg.what) {
				case 0:// 下载成功
					Bitmap bm = (Bitmap) msg.obj;
					if (bm == null)
						ivPic.setImageResource(R.drawable.ic_launcher);
					else
						ivPic.setImageBitmap(bm);
					Toast.makeText(Day11_01_HandlerActivity.this, "case 0", 3000).show();
					break;
					
				case 1:// 开始下载
					Toast.makeText(Day11_01_HandlerActivity.this, "开始下载", 3000).show();
					break;
				}
				Log.i("info", "这是callback对象的handleMessage方法，运行在线程：" + Thread.currentThread().getName());
				return false;//如果 返回是 true，那么将不会在 Handler自己的hadleMessang里处理，否则 会在 Handler自己的handleMessage函数里接着处理。
			}
		};
		
		handler = new Handler(callback) {//如果有callback参数，首先 执行 callback里的handleMessage函数，然后 执行 handler里的handleMessage函数,他们的参数是同一个msg
			/**
			 * 消息处理方法
			 */
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				Log.i("info", "这是handler对象的handleMessage方法，运行在线程：" + Thread.currentThread().getName());
//				 switch (msg.what) {
//				 case 0:// 下载完成
//					 Bitmap bm = (Bitmap) msg.obj;
//					 // 更新界面,显示图片
//					 if (bm != null)
//						 ivPic.setImageBitmap(bm);
//					 else
//						 ivPic.setImageResource(R.drawable.ic_launcher);
//					 Log.i("wanghai", "case 0");
//					 break;
//				 case 1:// 开始下载
//					 Toast.makeText(Day11_01_HandlerActivity.this, "开始加载图片,请稍候...", 3000).show();
//					 Log.i("wanghai", "case 1");
//					 break;
//				 }
			}
		};
	}
}