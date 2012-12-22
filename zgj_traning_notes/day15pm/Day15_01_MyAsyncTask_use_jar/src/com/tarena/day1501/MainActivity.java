package com.tarena.day1501;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.conn.ConnectTimeoutException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.tarena.utils.HttpUtils;
import com.tarena.utils.MyAsyncTask;

public class MainActivity extends Activity {
	
	private ImageView ivPic;

	private void setupView() {
		ivPic = (ImageView) findViewById(R.id.ivPic);
	}

	public void doClick(View v) {
		// ִ���첽�������ͼƬ
		MyTask task = new MyTask();
		task.execute("http://192.168.1.101:8080/test/imgs/p3.jpg");
	}

	private class MyTask extends MyAsyncTask<String, String, Bitmap> {

		@Override
		public void onPreExecute() {
			// TODO Auto-generated method stub
			Toast.makeText(MainActivity.this, "׼���첽����", 3000).show();
		}

		@Override
		public Bitmap doInBackground(String params) {
			Bitmap bm = null;
			try {
				HttpEntity entity = HttpUtils.getEntity(params, null, HttpUtils.METHOD_GET);
				publishProgress("��ȡ��Ӧʵ�����");

				InputStream is = HttpUtils.getStream(entity);
				publishProgress("��ȡʵ��������");

				bm = BitmapFactory.decodeStream(is);
				publishProgress("ͼƬ���سɹ�");
				
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
			Toast.makeText(MainActivity.this, progress, 3000).show();
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