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
					//msg.sendToTarget();//��������һ�� �滻 �ϱߵĻ�
					
					// ����ͼƬ
					HttpEntity entity = HttpUtils.getEntity(uri, null, HttpUtils.METHOD_GET);
					InputStream in = HttpUtils.getStream(entity);
					bm = BitmapFactory.decodeStream(in);

					// ������Ϣ�����߳�
					//msg = Message.obtain(handler, 0, bm);//��һ����� �滻 ���� �ľ�
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
				case 0:// ���سɹ�
					Bitmap bm = (Bitmap) msg.obj;
					if (bm == null)
						ivPic.setImageResource(R.drawable.ic_launcher);
					else
						ivPic.setImageBitmap(bm);
					Toast.makeText(Day11_01_HandlerActivity.this, "case 0", 3000).show();
					break;
					
				case 1:// ��ʼ����
					Toast.makeText(Day11_01_HandlerActivity.this, "��ʼ����", 3000).show();
					break;
				}
				Log.i("info", "����callback�����handleMessage�������������̣߳�" + Thread.currentThread().getName());
				return false;//��� ������ true����ô�������� Handler�Լ���hadleMessang�ﴦ������ ���� Handler�Լ���handleMessage��������Ŵ���
			}
		};
		
		handler = new Handler(callback) {//�����callback���������� ִ�� callback���handleMessage������Ȼ�� ִ�� handler���handleMessage����,���ǵĲ�����ͬһ��msg
			/**
			 * ��Ϣ������
			 */
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				Log.i("info", "����handler�����handleMessage�������������̣߳�" + Thread.currentThread().getName());
//				 switch (msg.what) {
//				 case 0:// �������
//					 Bitmap bm = (Bitmap) msg.obj;
//					 // ���½���,��ʾͼƬ
//					 if (bm != null)
//						 ivPic.setImageBitmap(bm);
//					 else
//						 ivPic.setImageResource(R.drawable.ic_launcher);
//					 Log.i("wanghai", "case 0");
//					 break;
//				 case 1:// ��ʼ����
//					 Toast.makeText(Day11_01_HandlerActivity.this, "��ʼ����ͼƬ,���Ժ�...", 3000).show();
//					 Log.i("wanghai", "case 1");
//					 break;
//				 }
			}
		};
	}
}