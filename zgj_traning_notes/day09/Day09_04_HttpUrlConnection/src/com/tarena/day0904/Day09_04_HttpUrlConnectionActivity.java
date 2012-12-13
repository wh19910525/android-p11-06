package com.tarena.day0904;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.app.Activity;
import android.content.ContentUris;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class Day09_04_HttpUrlConnectionActivity extends Activity {
	private String uri = "http://10.28.9.164:8080/test/imgs/p2.jpg";
//	private String uri = "http://localhost:8080/test/imgs/p2.jpg";
	private ImageView ivTest;

	private void setupView() {
		ivTest = (ImageView) findViewById(R.id.ivPic);
	}

	public void doClick(View v) {
		try {
			//����url����
			URL url = new URL(uri);//
			Log.i("info", "url after+++++++++++");
			//��ȡ���Ӷ���
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();//
			//�������󷽷�
			conn.setRequestMethod("GET");
			//��ʱ����
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			// conn.connect();
			//�����Ӧ��Ϊ200�����ȡʵ��������
			Log.i("=======", "----->"+conn.getResponseCode());//
			if (conn.getResponseCode() == 200) {
				//��ȡʵ��������
				InputStream in = conn.getInputStream();//
				Log.i("info", "in"+in);
				//����
				if (in != null) {
					// ����ͼƬ
					// Bitmap bm = BitmapFactory.decodeStream(in);
					// ����ͼƬ
					File file = new File("/mnt/sdcard/imags/" + uri.substring(uri.lastIndexOf("/") + 1));//�ο� day07_07
					Log.i("wanghai", "Ŀ¼��" + uri.substring(uri.lastIndexOf("/") + 1));
					Log.i("wanghai", "Ŀ¼0��" + (uri.lastIndexOf("/") + 1));
					Log.i("wanghai", "Ŀ¼1��" + uri.lastIndexOf("/") + 1);
					Log.i("wanghai", "Ŀ¼2��" + uri.lastIndexOf("/"));
					
					Log.i("getParentFile", " = " + file.getParentFile());
					if (!file.getParentFile().exists()) {//getParentFile()��ȡ �ļ�����·�������������ļ���
						file.getParentFile().mkdirs();//���� �ļ���
					}
					
					// file.createNewFile();
					// bm.compress(CompressFormat.JPEG, 100, new FileOutputStream(file));
					Log.i("info", "outbefore+++++++++++");

					FileOutputStream out = new FileOutputStream(file);
					BufferedOutputStream os = new BufferedOutputStream(out);
					BufferedInputStream is = new BufferedInputStream(in);
					int len = -1;
					byte[] buffer = new byte[1024];
					while ((len = is.read(buffer)) != -1) {
						os.write(buffer, 0, len);
						os.flush();
					}
					os.close();
					out.close();
					is.close();
					in.close();
					
					//�� �������sd�������ͼƬ
					Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());

					// ��ʾͼƬ
					ivTest.setImageBitmap(bm);
				}
				in.close();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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