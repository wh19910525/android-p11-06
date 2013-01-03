package com.tarena.day2302;

import android.app.Activity;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.tarena.biz.MessageBiz;
import com.tarena.entity.ThreadInfo;

public class Day23_02Activity extends Activity {
	private class SmsObserver extends ContentObserver {
		public SmsObserver() {
			super(handler);
		}

		@Override
		public void onChange(boolean selfChange) {
			handler.sendEmptyMessage(0);
		}
	}

	private ListView lvData;
	private MessageBiz biz;
	private ThreadAdapter adapter;
	private SmsObserver observer;
	
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			adapter.changeData(biz.getThreads());
		};
	};

	private void setupView() {
		lvData = (ListView) findViewById(R.id.lvData);
		adapter = new ThreadAdapter(this, biz.getThreads());
		lvData.setAdapter(adapter);
	}

	private void addListener() {
		lvData.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
				// TODO Auto-generated method stub
				ThreadInfo info = (ThreadInfo) adapter.getItem(position);

				Intent intent = new Intent(Day23_02Activity.this,
						MessageActivity.class);
				intent.putExtra("thread_id", info.getThreadId());
				startActivity(intent);
			}

		});
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		biz = new MessageBiz(this);
		setupView();
		addListener();

		//注册观察者
		observer = new SmsObserver();
		getContentResolver().registerContentObserver(
				Uri.parse("content://sms/"), true, observer);//参数为true时,支持派生uri
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		//取消观察者的注册
		getContentResolver().unregisterContentObserver(observer);
	}
}