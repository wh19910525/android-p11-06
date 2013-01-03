package com.tarena.day2205;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import cm.tarena.entity.CallLogInfo;

import com.tarena.biz.CallsBiz;

public class Day22_05_CallsActivity extends Activity {
	private ListView lvData;
	private CallsBiz biz;
	private CallLogAdapter adapter;

	private void setupView() {
		lvData = (ListView) findViewById(R.id.lvData);
		adapter = new CallLogAdapter(this, biz.getCallLogs());
		lvData.setAdapter(adapter);
	}

	private void addListener() {
		lvData.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				CallLogInfo info = (CallLogInfo) adapter.getItem(position);
				Intent intent = new Intent(Day22_05_CallsActivity.this,
						DetailsActivity.class);
				intent.putExtra("number", info.getNumber());
				startActivity(intent);

			}

		});
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		biz = new CallsBiz(this);
		setupView();
		addListener();
	}
}