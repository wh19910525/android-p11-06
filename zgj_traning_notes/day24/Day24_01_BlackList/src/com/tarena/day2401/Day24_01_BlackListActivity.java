package com.tarena.day2401;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;

import com.tarena.biz.BlackListBiz;

public class Day24_01_BlackListActivity extends Activity {
	
	private static final int MENU_CTX_REMOVE = 1;
	private ListView lvNumbers;
	private EditText etPhoneNumber;
	private CheckBox chkUseBlackList;
	private BlackListBiz biz;
	private ArrayAdapter<String> adapter;
	private SharedPreferences pref;

	private void setupView() {
		etPhoneNumber = (EditText) findViewById(R.id.etPhoneNumber);
		chkUseBlackList = (CheckBox) findViewById(R.id.chkUseBlackList);
		if (pref.getBoolean("useBlackList", false)) {
			chkUseBlackList.setChecked(true);
			chkUseBlackList.setText("���ú�����");
			startService(new Intent(this, TelService.class));
		} else {
			chkUseBlackList.setChecked(false);
			chkUseBlackList.setText("ͣ�ú�����");
		}

		lvNumbers = (ListView) findViewById(R.id.lvNumbers);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, biz.getNumbers());
		lvNumbers.setAdapter(adapter);
	}

	private void addListener() {
		chkUseBlackList.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						// TODO Auto-generated method stub
						Intent service = new Intent(
								Day24_01_BlackListActivity.this, TelService.class);
						if (isChecked) {
							chkUseBlackList.setText("���ú�����");
							startService(service);
						} else {
							chkUseBlackList.setText("ͣ�ú�����");
							stopService(service);
						}
						// ��ƫ�������б���������Ƿ����õ�״̬
						pref.edit().putBoolean("useBlackList", isChecked).commit();
					}
				});

		/**
		 * listview�Ĵ��������Ĳ˵�������
		 */
		lvNumbers.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

					@Override
					public void onCreateContextMenu(ContextMenu menu, View v,
							ContextMenuInfo menuInfo) {
						// TODO Auto-generated method stub
						menu.setHeaderTitle("����");
						menu.add(1, MENU_CTX_REMOVE, 1, "ɾ��");
					}
				});
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		String number = adapter.getItem(menuInfo.position).toString();

		int count = biz.removeNumber(number);
		if (count > 0) {
			adapter.remove(number);
		}
		return super.onContextItemSelected(item);
	}

	public void doClick(View v) {
		String number = etPhoneNumber.getText().toString();
		long rowId = biz.addNumber(number);
		if (rowId != -1) {
			// �������ɹ�������listview
			adapter.add(number);
			// ����ı���
			etPhoneNumber.setText("");
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		biz = new BlackListBiz(this);
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		setupView();
		addListener();
	}

}
