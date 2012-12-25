package com.tarena.day1701;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

import com.tarena.biz.MusicBiz;
import com.tarena.entity.Music;
import com.tarena.utils.GlobalUtils;

public class MainActivity extends Activity {
	
	private static final int MENU_OPTS_ADD = 1;
	private static final int MENU_OPTS_EXIT = 2;
	private static final int MENU_CONTEXT_DETAILS = 3;
	private static final int MENU_CONTEXT_DELETE = 4;
	private static final int MENU_CONTEXT_UPDATE = 5;
	private ListView lvMusics;
	private MusicBiz biz;
	private MusicAdapter adapter;
	private AlertDialog dialog;

	private void setupView() {
		lvMusics = (ListView) findViewById(R.id.lvMusics);
		adapter = new MusicAdapter(this, null);
		lvMusics.setAdapter(adapter);

		dialog = new Builder(this).setTitle("详情")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setPositiveButton("确定", null).create();
	}

	private void addListener() {
		lvMusics.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				// TODO Auto-generated method stub
				menu.setHeaderTitle("操作");
				menu.setHeaderIcon(android.R.drawable.ic_dialog_info);
				menu.add(2, MENU_CONTEXT_DETAILS, 1, "详情");
				menu.add(2, MENU_CONTEXT_UPDATE, 2, "修改");
				menu.add(2, MENU_CONTEXT_DELETE, 3, "删除");
			}
		});
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		adapter.changeData(biz.getMusics());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, MENU_OPTS_ADD, 1, "添加").setIcon(
				android.R.drawable.ic_menu_add);
		menu.add(1, MENU_OPTS_EXIT, 2, "退出").setIcon(
				android.R.drawable.ic_menu_close_clear_cancel);

		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Log.i("info", "onOptionsItemSelected");
		switch (item.getItemId()) {
		case MENU_OPTS_ADD:// 添加
			Log.i("info", "MENU_OPTS_ADD");
			Intent intent = new Intent(this, MusicOpActivity.class);
			intent.putExtra(GlobalUtils.EXTRA_OP_TYPE, GlobalUtils.OP_TYPE_ADD);
			startActivity(intent);
			Log.i("info", "MENU_OPTS_ADD");
			break;

		case MENU_OPTS_EXIT:// 退出
			finish();
			break;
		}

		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		Music m = (Music) adapter.getItem(menuInfo.position);
		switch (item.getItemId()) {
		case MENU_CONTEXT_DETAILS:// 详情
			dialog.setMessage(m.toString());
			dialog.show();
			break;
		case MENU_CONTEXT_UPDATE:// 修改
			Intent intent = new Intent(this, MusicOpActivity.class);
			intent.putExtra(GlobalUtils.EXTRA_OP_TYPE,
					GlobalUtils.OP_TYPE_UPDATE);
			intent.putExtra(GlobalUtils.EXTRA_OP_DATA, m);
			startActivity(intent);
			break;
		case MENU_CONTEXT_DELETE:// 删除
			int count = biz.removeMusic(m.getId());
			if (count > 0) {
				adapter.removeItem(menuInfo.position);
			}
			break;
		}

		// TODO Auto-generated method stub
		return super.onContextItemSelected(item);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		biz = new MusicBiz(this);
		setupView();
		addListener();
	}
}