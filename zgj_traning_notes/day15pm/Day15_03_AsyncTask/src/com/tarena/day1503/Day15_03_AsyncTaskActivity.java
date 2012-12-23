package com.tarena.day1503;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.conn.ConnectTimeoutException;

import com.tarena.utils.HttpUtils;
import com.tarena.utils.StreamUtils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Day15_03_AsyncTaskActivity extends Activity {
	
	private ImageView ivPic;
	private MyTask task;
	
	private void setupView() {
		ivPic = (ImageView) findViewById(R.id.ivPic);
	}

	public void doClick(View v) {
		task.execute("http://192.168.1.101:8080/test/imgs/p3.jpg");
	}

	private class MyTask extends AsyncTask<String, String, Bitmap> {//

		@Override
		protected Bitmap doInBackground(String... params) {
			Bitmap bm = null;
			try {
				HttpEntity entity = HttpUtils.getEntity(params[0], null, HttpUtils.METHOD_GET);
				publishProgress("连接服务端，获取响应实体对象");

				InputStream in = HttpUtils.getStream(entity);
				publishProgress("获取实体输入流");

				StreamUtils.save(in, "/mnt/sdcard/imgs/p10.jpg");
				publishProgress("图片保存成功");

				bm = BitmapFactory.decodeFile("/mnt/sdcard/imgs/p10.jpg");
				publishProgress("图片加载成功");
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
		protected void onPostExecute(Bitmap result) {
			ivPic.setImageBitmap(result);
		}

		@Override
		protected void onPreExecute() {
			Log.i("info", "准备连接服务端，加载图片");
		}

		@Override
		protected void onProgressUpdate(String... values) {
			Toast.makeText(Day15_03_AsyncTaskActivity.this, values[0], 3000).show();
		}

		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
		task = new MyTask();
	}

}