package com.tarena.day2302;

import com.tarena.biz.MessageBiz;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MessageActivity extends Activity {
	private ListView lvData;
	private MessageBiz biz;
	private MessageAdapter adapter;
	private int thread_id;

	private void setupView() {
		lvData = (ListView) findViewById(R.id.lvData);
		adapter = new MessageAdapter(this, biz.getMessages(thread_id));
		lvData.setAdapter(adapter);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		biz = new MessageBiz(this);
		thread_id = getIntent().getIntExtra("thread_id", 0);
		setupView();
	}
}
