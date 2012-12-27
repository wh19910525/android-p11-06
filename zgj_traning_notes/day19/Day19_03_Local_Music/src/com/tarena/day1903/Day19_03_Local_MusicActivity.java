package com.tarena.day1903;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;

import com.tarena.biz.MusicBiz;
import com.tarena.entity.Music;

public class Day19_03_Local_MusicActivity extends Activity {
	
	private static final int MENU_CONTEXT_DETAILS = 1;
	private static final int MENU_CONTEXT_DELETE = 2;
	private static final int MENU_OPTS_FLUSH = 3;
	private static final int MENU_OPTS_EXIT = 4;

	private ListView lvMusics;
	private MusicBiz biz;
	private MusicAdapter adapter;
	private AlertDialog dialog1, dialog2;
	private MediaScannerReceiverwww receiver;

	private void setupView() {
		lvMusics = (ListView) findViewById(R.id.lvMusics);
		adapter = new MusicAdapter(this, biz.getMusics());
		lvMusics.setAdapter(adapter);

		dialog1 = new Builder(this).setTitle("提示")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setCancelable(false).setMessage("正在扫描SDCard，请稍候...").create();
		dialog2 = new Builder(this).setTitle("详情")
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

				menu.add(1, MENU_CONTEXT_DETAILS, 1, "详情");
				menu.add(1, MENU_CONTEXT_DELETE, 2, "删除");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, MENU_OPTS_FLUSH, 1, "刷新");
		menu.add(1, MENU_OPTS_EXIT, 2, "退出");
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_OPTS_FLUSH:// 刷新
			Intent intent = new Intent(Intent.ACTION_MEDIA_MOUNTED);
			intent.setData(Uri.parse("file://" + Environment.getExternalStorageDirectory()));//这里是 必须的
			sendBroadcast(intent);//
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
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		Music m = (Music) adapter.getItem(menuInfo.position);

		switch (item.getItemId()) {
		case MENU_CONTEXT_DETAILS:// 详情
			dialog2.setMessage(m.toString());
			dialog2.show();
			break;
		case MENU_CONTEXT_DELETE:// 删除
			// 从数据库删除
			int count = biz.delete(m.getId());
			// 如果删除成功 数据库里的文件索引
			if (count > 0) {
				// 删除真正的文件
				File file = new File(m.getPath());
				file.delete();
				if (!file.exists()) {
					// 刷新界面
					adapter.removeItem(menuInfo.position);
				}

			}
			break;
		}
		// TODO Auto-generated method stub
		return super.onContextItemSelected(item);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}


	class MediaScannerReceiverwww extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
            Log.i("myLoger", "Component: " + intent.getComponent());  
            
           Log.i("myLoger", "Aciton: " +  intent.getAction());  
           Log.i("myLoger", "Categories: " +  intent.getCategories());  

           Log.i("myLoger", "Data: " + intent.getData());  
           Log.i("myLoger", "DataType: " + intent.getType());  
           Log.i("myLoger", "DataSchema: " + intent.getScheme());  
             
           Log.i("myLoger"," Receive SDCard Mount/UnMount!");  
			if (Intent.ACTION_MEDIA_SCANNER_STARTED.equals(action)) {
				dialog1.show();
				Log.i("wh", "+++++++++++");
			} else if (Intent.ACTION_MEDIA_SCANNER_FINISHED.equals(action)) {
				dialog1.dismiss();
				// 刷新界面
				adapter.changeData(biz.getMusics());
				Log.i("zgj", "+++++++++++");
			}
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		biz = new MusicBiz(this);//创建了一个getContentResolver
		setupView();
		addListener();

		receiver = new MediaScannerReceiverwww();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
		filter.addAction(Intent.ACTION_MEDIA_SCANNER_STARTED);
		filter.addDataScheme("file");//这里 必不可少
		registerReceiver(receiver, filter);
	}

}