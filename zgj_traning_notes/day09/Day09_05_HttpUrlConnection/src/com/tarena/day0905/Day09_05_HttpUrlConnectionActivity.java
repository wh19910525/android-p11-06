package com.tarena.day0905;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;

import com.tarena.utils.HttpUtils;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Day09_05_HttpUrlConnectionActivity extends Activity {
	private String uri = "http://192.168.188.36:8080/stu_server/student";
	private TextView tvContent;
	private RadioGroup rgSex;

	private void setupView() {
		rgSex = (RadioGroup) findViewById(R.id.rgSex);
		tvContent = (TextView) findViewById(R.id.tvContent);
	}

	// post����
	public void doPost(View v) {
		// ��ȡ�û�������
		String sex = "male";
		if (rgSex.getCheckedRadioButtonId() == R.id.rdoFemale)
			sex = "female";
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("sex", sex);
		
		try {
			InputStream is = HttpUtils.doPost(uri, params);
			if (is != null) {
				ByteArrayOutputStream os = readStream(is);
				tvContent.setText(new String(os.toByteArray()));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// try {
		// URL url = new URL(uri);
		// HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// conn.setRequestMethod("POST");
		// conn.setConnectTimeout(3000);
		// conn.setReadTimeout(3000);
		//
		// //�����ȡָ�����˵������
		// conn.setDoOutput(true);
		// // ����ʵ����Ϣͷ
		// conn.setRequestProperty("Content-Type",
		// "application/x-www-form-urlencoded");
		// conn.setRequestProperty("Content-Length", content.length() + "");
		// // ��ȡָ�����˵������
		// OutputStream out = conn.getOutputStream();
		// // �������������(����ʵ������)
		// out.write(content.getBytes());
		// out.close();
		//
		// if (conn.getResponseCode() == 200) {
		// InputStream is = conn.getInputStream();
		// if (is != null) {
		// ByteArrayOutputStream os = readStream(is);
		// tvContent.setText(new String(os.toByteArray()));
		// }
		// }
		// } catch (MalformedURLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (ProtocolException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	// get����
	public void doClick(View v) {
		// ��ȡ�û�������
		String sex = "male";
		if (rgSex.getCheckedRadioButtonId() == R.id.rdoFemale)
			sex = "female";
		
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("sex", sex);
		
		try {
			InputStream is = HttpUtils.doGet(uri, params);
			if (is != null) {
				ByteArrayOutputStream os = readStream(is);
				tvContent.setText(new String(os.toByteArray()));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		try {
//			URL url = new URL(uri + "?sex=" + sex);
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//			conn.setRequestMethod("GET");
//			conn.setConnectTimeout(3000);
//			conn.setReadTimeout(3000);
//			if (conn.getResponseCode() == 200) {
//
//				InputStream is = conn.getInputStream();
//				Log.i("info", "is=" + is);
//				if (is != null) {
//					ByteArrayOutputStream out = readStream(is);
//
//					tvContent.setText(new String(out.toByteArray()));
//				}
//			}
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	private ByteArrayOutputStream readStream(InputStream is) throws IOException {
		// ����
		BufferedInputStream in = new BufferedInputStream(is);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int len = -1;
		byte[] buffer = new byte[1024];
		while ((len = in.read(buffer)) != -1) {
			out.write(buffer, 0, len);
			out.flush();
		}
		out.close();
		in.close();
		is.close();
		return out;
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}
}