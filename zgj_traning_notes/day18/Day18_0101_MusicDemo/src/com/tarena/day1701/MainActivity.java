package com.tarena.day1701;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.tarena.dal.MusicProviderInfo.MusicInfo;
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
	private MusicObserver observer;

	private void setupView() {
		lvMusics = (ListView) findViewById(R.id.lvMusics);
		adapter = new MusicAdapter(this, biz.getMusics());
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

		// 创建观察者对象
		Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0:
					Log.i("info", "handleMessage");
					adapter.changeData(biz.getMusics());//若数据变化,观察者发回消息,对界面进行更新
					break;
				}
			}
		};
		observer = new MusicObserver(handler);
		// 注册到客户端
		/**注册后,当给定的Uri发生改变时，回调该实例对象去处理,比如本案例中当执行增删改方法后,此Uri下的数据发生变化，此时就会调用ContentObserver对象进行处理
		************
		*与之前在onResume()方法中更新数据相比，使用观察者可以再没有更新数据的时候不执行
		*adapter.changeData(biz.getMusics())方法,而在onResume()方法中更新的话,只要界面
		*属于交互状态，不论数据是否发生变化都会调用adapter.changeData(biz.getMusics())，从而降低了效率
		*同时，可以注册多个观察者对象,对指定的Uri进行观察。
		**************
		* registerContentObserver()参数说明：
		* notifyForDescendents 为false 表示精确匹配，即只匹配该Uri

                                                                     为true 表示可以同时匹配其派生的Uri
                                                                  
		*/
		
		getContentResolver().registerContentObserver(MusicInfo.CONTENT_URI, true, observer);
//		getContentResolver().registerContentObserver(UserInfo.CONTENT_URI, true, observer);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		//在Activity结束时结束观察
		getContentResolver().unregisterContentObserver(observer);
	}
}