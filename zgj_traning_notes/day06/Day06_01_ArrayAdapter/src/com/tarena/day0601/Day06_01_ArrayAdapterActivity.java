package com.tarena.day0601;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.tarena.biz.WeekBiz;
import com.tarena.day0601.adapter.WeekAdapter;

public class Day06_01_ArrayAdapterActivity extends Activity {
	
	private WeekBiz biz;
	// private WeekAdapter adapter;
	private ArrayAdapter<String> adapter;//
	private ListView lvWeek;

	private void setupView() {
		//获取ListView的引用
		lvWeek = (ListView) findViewById(R.id.lvWeek);
		
		//实例化adapter，并且 通过 getDays() 获取 数据集
//		adapter = new WeekAdapter(this, biz.getDays());
		
		adapter = new ArrayAdapter<String>(this, R.layout.item_week, R.id.tvDay, biz.getDays());		
//		adapter = new ArrayAdapter<String>(Day06_01_ArrayAdapterActivity.this, R.layout.item_week_1, biz.getDays());		
//		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, biz.getDays());
		
		//设置listView的adapter
		lvWeek.setAdapter(adapter);
		
//		adapter.add(object)
//		adapter.insert(object, index)
//		adapter.remove(object)
	}

	private void addListener() {
		lvWeek.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> listView, View item, int position, long itemId) {
				// TODO Auto-generated method stub
				String day = adapter.getItem(position).toString();// 获取 adapter里 被点击的item对应的 数据项
				Toast.makeText(Day06_01_ArrayAdapterActivity.this,
						"您单击了：" + day, 3000).show();
			}
		});
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		biz = new WeekBiz();
		setupView();
		addListener();
	}
}