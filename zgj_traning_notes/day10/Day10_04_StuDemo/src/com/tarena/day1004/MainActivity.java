package com.tarena.day1004;

import java.util.ArrayList;

import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.tarena.biz.StudentBiz;
import com.tarena.entity.Student;
import com.tarena.utils.HttpUtils;

public class MainActivity extends Activity {
	private ListView lvStudent;
	private StudentAdapter adapter;
	private StudentBiz biz;

	private void setupView() {
		lvStudent = (ListView) findViewById(R.id.lvStudents);
		ArrayList<Student> students = biz.getStudent(HttpUtils.BASE_URL
				+ "/student", null, HttpUtils.METHOD_GET);
		Log.i("info", "size=" + students.size());
		adapter = new StudentAdapter(this, students);
		lvStudent.setAdapter(adapter);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		biz = new StudentBiz();
		setupView();
	}
}