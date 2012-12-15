package com.tarena.day1001;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class Day10_01_HttpClientActivity extends Activity {
	
	private ImageView ivPic;

	private void setupView() {
		ivPic = (ImageView) findViewById(R.id.ivPic);
	}

	public void doClick(View v) 
			throws ConnectTimeoutException, ClientProtocolException,IllegalStateException, IOException{
		Bitmap bm = null;
		// 加载图片
		//try {
			HttpClient client = new DefaultHttpClient();//创建一个DefaultHttpClient对象
			client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000);
			HttpGet request = new HttpGet("http://10.28.9.164:8080/test/imgs/p2.jpg");//创建一个请求对象
			HttpResponse response = client.execute(request);//客户端执行请求，获得响应对象
			
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {//获取状态行的状态码, 如果状态码为200，则获取响应实体对象
				HttpEntity entity = response.getEntity();
				response.getAllHeaders();
				InputStream is = entity.getContent();//
				bm = BitmapFactory.decodeStream(is);
				is.close();
			}
			ivPic.setImageBitmap(bm);
/*			
		} catch (ConnectTimeoutException e) {
			// TODO: handle exception
			Log.i("info", "连接超时");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 显示图片
		ivPic.setImageBitmap(bm); */
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}
}