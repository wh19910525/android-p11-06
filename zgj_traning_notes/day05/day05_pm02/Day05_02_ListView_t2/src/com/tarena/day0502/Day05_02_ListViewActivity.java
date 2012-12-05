package com.tarena.day0502;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

import com.tarena.biz.MusicBiz;
import com.tarena.day0502.adapter.MusicAdapter;
import com.tarena.entity.Music;
import com.tarena.utils.GlobalUtils;

public class Day05_02_ListViewActivity extends Activity {
	
	private static final int MENU_OPTS_ADD = 1;
	private static final int MENU_OPTS_EXIT = 2;
	private static final int MENU_CONTEXT_DETAILS = 3;
	private static final int MENU_CONTEXT_UPDATE = 4;
	private static final int MENU_CONTEXT_DELETE = 5;

	private MusicBiz biz;
	private MusicAdapter adapter;//继承 BaseAdapter
	private ListView lvMusics;
	private AlertDialog dialog;

	private void setupView() {
		//获取ListView的引用
		lvMusics = (ListView) findViewById(R.id.lvMusics);
		
		//实例化adapter，并且在 MusicBiz 的构造函数里 获取 数据集
		adapter = new MusicAdapter(this, biz.getMusics());//注意 这 两个参数；
		
		//设置listView的adapter
		lvMusics.setAdapter(adapter);

		dialog = new Builder(this).setTitle("详情")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setPositiveButton("确定", null).create();
	}

	private void addListener() {//
		lvMusics.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {//这个方法 是对 ListView 控件监听的，当 用 鼠标 长按ListView里的 条目时，调用此方法

			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,//
					ContextMenuInfo menuInfo) {
				// TODO Auto-generated method stub
				menu.setHeaderIcon(android.R.drawable.ic_menu_camera);
				menu.setHeaderTitle("操作");
				menu.add(2, MENU_CONTEXT_DETAILS, 1, "详情");
				menu.add(2, MENU_CONTEXT_UPDATE, 2, "修改");
				menu.add(2, MENU_CONTEXT_DELETE, 3, "删除");
			}
		});
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		biz = new MusicBiz();
		setupView();
		addListener();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {//参考 day03_12
		// TODO Auto-generated method stub
		menu.add(1, MENU_OPTS_ADD, 1, "添加").setIcon(
				android.R.drawable.ic_menu_add);
		menu.add(1, MENU_OPTS_EXIT, 2, "退出").setIcon(
				android.R.drawable.ic_menu_close_clear_cancel);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {//第二 和 第三个参数 就是 setResult 传过来的
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		switch (resultCode) {
		case RESULT_OK:
			int opType = data.getIntExtra(GlobalUtils.EXTRA_OP_TYPE,
					GlobalUtils.OP_TYPE_ADD);
			Music music = (Music) data
					.getSerializableExtra(GlobalUtils.EXTRA_OP_DATA);//获取 用 putExtra()发送的 键EXTRA_OP_DATA 对应的值  类；
			if (music != null) {
				switch (opType) {
				case GlobalUtils.OP_TYPE_ADD:// 添加
					biz.addMusic(music);//添加数据
					break;

				case GlobalUtils.OP_TYPE_UPDATE:// 修改
					biz.modifyMusic(music);//修改数据
					break;
				}
				//更新界面
				adapter.notifyDataSetChanged();//
			}
			break;

		case RESULT_CANCELED:
			break;
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case MENU_OPTS_ADD:// 添加
			Intent intent = new Intent(this, MusicOpActivity.class);
			intent.putExtra(GlobalUtils.EXTRA_OP_TYPE, GlobalUtils.OP_TYPE_ADD);//
			intent.putExtra(GlobalUtils.EXTRA_MUSIC_ID, adapter.getCount() + 1);//第二个参数在 另一个 activity里使用
			// startActivity(intent);
			startActivityForResult(intent, 0);//在activity1 里，用 这个方法 启动的 另一个 activity2之后，当在 activity2里 使用了 setResult 函数 之后，那么 会自动 启动activity1里的onActivityResult函数； 
			break;

		case MENU_OPTS_EXIT:// 退出
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {//当 点击 监听 ListView 弹出见面的 选项时，执行 次方法
		// TODO Auto-generated method stub
		
		// 获取上下文菜单信息,并强转为AdapterCotnextMenuInfo
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		
		// 根据menuinfo中的position属性，从adapter中 获取 被长按的item对应的 数据项
		Music music = (Music) adapter.getItem(menuInfo.position);

		switch (item.getItemId()) {//getItemId 获取 弹出见面的 选项号
		case MENU_CONTEXT_DETAILS:// 详情
			dialog.setMessage(music.toString());
			dialog.show();
			break;
		case MENU_CONTEXT_UPDATE:// 修改
			Intent intent = new Intent(this, MusicOpActivity.class);
			// 设置操作类型为修改
			intent.putExtra(GlobalUtils.EXTRA_OP_TYPE,
					GlobalUtils.OP_TYPE_UPDATE);
			// 要修改的数据
			intent.putExtra(GlobalUtils.EXTRA_OP_DATA, music);
			// startActivity(intent);
			startActivityForResult(intent, 0);
			break;
		case MENU_CONTEXT_DELETE:// 删除
			biz.removeMusic(music);// 从集合中删除指定数据项
			adapter.notifyDataSetChanged();// 更新listview
			break;
		}
		return super.onContextItemSelected(item);
	}
}