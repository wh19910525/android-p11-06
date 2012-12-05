package com.tarena.day0602;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;

import com.tarena.day0602.adapter.MyArrayAdapter;

public class Day06_02_MyArrayAdapterActivity extends Activity {
	private ListView lvData;
	private MyArrayAdapter adapter;

	/**
	 * �����ʼ������
	 */
	private void setupView() {
		lvData = (ListView) findViewById(R.id.lvData);
		adapter = new MyArrayAdapter(this, android.R.layout.simple_list_item_1,
				getData());
		lvData.setAdapter(adapter);
	}

	/**
	 * ��ȡ���ݵķ���
	 * @return
	 */
	private ArrayList<String> getData() {
		ArrayList<String> data = new ArrayList<String>();
		for (int i = 1; i <= 20; i++) {
			data.add("item" + i);
		}
		return data;
	}
	
	/**
	 * ����¼��������ķ���
	 */
	private void addListener(){
		lvData.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
			
			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				// TODO Auto-generated method stub
				menu.setHeaderIcon(R.drawable.ic_launcher);
				menu.setHeaderTitle("����");
				menu.add(1,1,1,"ɾ��");
			}
		});
	}
	
	/**
	 * �����Ĳ˵���ĵ����¼�������
	 */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo)item.getMenuInfo();
		String data = adapter.getItem(menuInfo.position).toString();
		switch (item.getItemId()) {
		case 1://ɾ��
			adapter.remove(data);
			break;
		}
		return super.onContextItemSelected(item);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
		addListener();
	}
}