package com.tarena.day0605;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.tarena.biz.StudentBiz;

public class Day06_05_SimpleAdapterActivity extends Activity {
	private ListView lvStudents;
	private StudentBiz biz;
	private SimpleAdapter adapter;

	private void setupView() {
		lvStudents = (ListView) findViewById(R.id.lvStudents);
		String[] from = { "icon","id", "name", "sex", "age" };
		int[] to = { R.id.ivHead,R.id.tvId, R.id.tvName, R.id.tvSex, R.id.tvAge };

		adapter = new SimpleAdapter(this, biz.getStudents(), R.layout.item,
				from, to);
		lvStudents.setAdapter(adapter);
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