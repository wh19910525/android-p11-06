package com.tarena.day1904;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Day19_04_ApplicationActivity extends Activity {
	
	private TextView tvInfo;
	private MyApplication app;

	private void setupView() {
		tvInfo = (TextView) findViewById(R.id.tvInfo);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
		StringBuilder sb = new StringBuilder();
		ArrayList<String> list = app.getList();//
		for (String item : list) {
			sb.append(item).append('\n');
		}
		tvInfo.setText(sb.toString());
	}

	public void doClick(View v) {
		app.addItem("abc");
		app.addItem("xyz");

		Intent intent = new Intent(this, SecondActivity.class);
		startActivity(intent);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		app = (MyApplication) getApplication();//
		setupView();
	}
}