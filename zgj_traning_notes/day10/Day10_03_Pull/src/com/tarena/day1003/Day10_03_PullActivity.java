package com.tarena.day1003;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.conn.ConnectTimeoutException;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tarena.entity.Student;
import com.tarena.utils.HttpUtils;

public class Day10_03_PullActivity extends Activity {
	private TextView tvContent;

	private void setupView() {
		tvContent = (TextView) findViewById(R.id.tvContent);
	}

	public void doClick(View v) {
		try {
			HttpEntity entity = HttpUtils.getEntity(
					"http://192.168.1.102:8080/stu_server/student", null,
					HttpUtils.METHOD_GET);
			InputStream is = HttpUtils.getStream(entity);
			// 解析xml,获取实体集合
			ArrayList<Student> students = StudentXmlParser
					.parse(new InputStreamReader(is));
			// 将实体集合的内容显示再textView中
			StringBuilder sb = new StringBuilder();
			for (Student stu : students) {
				sb.append(stu.toString());
			}
			tvContent.setText(sb.toString());
		} catch (ConnectTimeoutException e) {
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