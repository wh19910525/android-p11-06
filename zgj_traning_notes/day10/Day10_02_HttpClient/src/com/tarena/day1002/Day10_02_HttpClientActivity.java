package com.tarena.day1002;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.tarena.utils.HttpUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Day10_02_HttpClientActivity extends Activity {
	
	private RadioGroup rgSex;
	private TextView tvContent;

	private void setupView() {
		rgSex = (RadioGroup) findViewById(R.id.rgSex);
		tvContent = (TextView) findViewById(R.id.tvContent);
	}

	public void doPost(View v) {
		// 获取用户输入
		String sex = "male";
		if (rgSex.getCheckedRadioButtonId() == R.id.rdoFemale)
			sex = "female";

		// 创建请求参数集合
		ArrayList<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();//
		parameters.add(new BasicNameValuePair("sex", sex));

		try {
			HttpEntity entity = HttpUtils.getEntity("http://10.28.9.164:8080/stu_server/student", 
					parameters, HttpUtils.METHOD_GET);//
			String content = EntityUtils.toString(entity);
			tvContent.setText(content);
		} catch (ConnectTimeoutException e) {
			// TODO: handle exception
			Log.i("info", "连接超时");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// try {
		// // 获取图片
		// HttpClient client = new DefaultHttpClient();
		// HttpPost request = new HttpPost("http://192.168.188.36:8080/stu_server/student");
		// UrlEncodedFormEntity reqEntity = new UrlEncodedFormEntity(parameters);//使用HttpPost
		// request.setEntity(reqEntity);
		// HttpResponse response = client.execute(request);
		// if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
		// // Header[] headers = response.getAllHeaders();
		// // for(Header header : headers){
		// // header.getName();
		// // header.getValue();
		// // }
		// HttpEntity entity = response.getEntity();
		// entity.getContentLength();
		// entity.getContentType();
		//
		// // byte[] data = EntityUtils.toByteArray(entity);
		// String content = EntityUtils.toString(entity);
		// // String content = EntityUtils.toString(entity, "utf-8");
		// tvContent.setText(content);
		// }
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (ClientProtocolException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}
}