package com.tarena.day1408;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream.PutField;

import org.apache.http.HttpEntity;
import org.apache.http.conn.ConnectTimeoutException;

import com.tarena.utils.HttpUtils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Day14_08_MyAsyncTaskActivity extends Activity {
	
	private ImageView ivPic;

	private void setupView() {
		ivPic = (ImageView) findViewById(R.id.ivPic);
	}

	public void doClick(View v) {
		MyTask task = new MyTask();//
		task.execute("http://10.28.9.164:8080/test/imgs/p4.jpg");
	}

	private abstract class MyAsyncTask<Params, Progress, Result> {//
		
		private Handler handler;//

		public MyAsyncTask() {
			Log.i("father", "ok!");
			handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					// TODO Auto-generated method stub
					Log.i("info", "abstract class: "+Thread.currentThread().getName());
					switch (msg.what) {
					case 0:// 异步任务执行完成
						onPostExecute((Result) msg.obj);//
						break;

					case 1:// 进度更新
						onUpdateProgress((Progress) msg.obj);//
						break;
					}
				}
			};
		}

		public void execute(final Params uri) {
			// 准备工作
			onPreExecute();//
			// 启动工作线程，执行异步任务
			new Thread() {
				public void run() {
					// 执行异步任务
					Result bm = doInBackground(uri);//

					// 发送消息回主线程
					Message msg = Message.obtain();
					msg.what = 0;
					msg.obj = bm;
					handler.sendMessage(msg);
				};
			}.start();
		}

		public void publishProgress(Progress progress) {
			Message msg = Message.obtain();
			msg.what = 1;
			msg.obj = progress;
			handler.sendMessage(msg);
		}

		public abstract void onPreExecute();

		public abstract Result doInBackground(Params uri);

		public abstract void onPostExecute(Result result);

		public abstract void onUpdateProgress(Progress progress);
	}

	private class MyTask extends MyAsyncTask<String, String, Bitmap> {

		@Override
		public void onPreExecute() {
			// TODO Auto-generated method stub
			Log.i("info", "onPreExeucte");
		}

		@Override
		public Bitmap doInBackground(String uri) {
			Bitmap bm = null;
			try {
				// 加载图片
				HttpEntity entity = HttpUtils.getEntity(uri, null, HttpUtils.METHOD_GET);
				publishProgress("连接服务端，获取响应实体");

				InputStream is = HttpUtils.getStream(entity);
				publishProgress("获取实体输入流");

				bm = BitmapFactory.decodeStream(is);
				publishProgress("加载完成");
			} catch (ConnectTimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bm;
		}

		@Override
		public void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			ivPic.setImageBitmap(result);
		}

		@Override
		public void onUpdateProgress(String progress) {
			// TODO Auto-generated method stub
			Toast.makeText(Day14_08_MyAsyncTaskActivity.this, progress, 3000).show();
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}

}