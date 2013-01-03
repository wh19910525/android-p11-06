package com.tarena.day2205;

import com.tarena.biz.CallsBiz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class DetailsActivity extends Activity {
	private ListView lvData;
	private CallsBiz biz;
	private CallsAdapter adapter;
	private String number;

	private void setupView() {
		lvData = (ListView) findViewById(R.id.lvData);
		adapter = new CallsAdapter(this, biz.getCallsInfo(number));
		lvData.setAdapter(adapter);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		biz = new CallsBiz(this);
		number = getIntent().getStringExtra("number");
		setupView();
	}
}
