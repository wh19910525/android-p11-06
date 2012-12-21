package com.tarena.day1201;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.conn.ConnectTimeoutException;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;
import android.widget.Toast;

import com.tarena.biz.MusicBiz;
import com.tarena.day1201.adapter.MusicAdapter;
import com.tarena.entity.Music;
import com.tarena.utils.HttpUtils;
import com.tarena.utils.MusicXmlParseTask;
import com.tarena.utils.MusicXmlParser;

public class MusicClientActivity extends Activity {
	private static final int MENU_CONTEXT_DETAILS = 1;
	private static final int MENU_CONTEXT_DOWNLOAD = 2;
	private static final int MSG_TAG_STARTED = 1;
	private static final int MSG_TAG_FINISHED = 2;

	private ListView lvMusics;
	private MusicBiz biz;
	private MusicAdapter adapter;
	private AlertDialog dialog;

	/**
	 * 使用新的数据集更新 界面
	 * 
	 * @param musics
	 */
	public void updateListView(ArrayList<Music> musics) {
		adapter.changeData(musics);
	}

	private void setupView() {
		// 初始化listView
		lvMusics = (ListView) findViewById(R.id.lvMusics);
		adapter = new MusicAdapter(this, null, lvMusics);
		lvMusics.setAdapter(adapter);

		// 初始化对话框
		dialog = new Builder(this).setTitle("详情")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setPositiveButton("确定", null).setCancelable(true).create();

		// 启动工作线程，联网解析xml
		MusicXmlParseTask task = new MusicXmlParseTask(this);
		task.execute(HttpUtils.BASE_URL + "sounds.xml");
	}

	private void addListener() {
		lvMusics.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				// TODO Auto-generated method stub
				menu.setHeaderIcon(android.R.drawable.ic_dialog_info);
				menu.setHeaderTitle("操作");
				menu.add(1, MENU_CONTEXT_DETAILS, 1, "详情");
				menu.add(1, MENU_CONTEXT_DOWNLOAD, 2, "下载");
			}
		});
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// 获取当前music信息
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		Music music = (Music) adapter.getItem(menuInfo.position);

		switch (item.getItemId()) {
		case MENU_CONTEXT_DETAILS:// 详情
			dialog.setMessage(music.toString());
			dialog.show();
			break;

		case MENU_CONTEXT_DOWNLOAD:// 下载
			String uri = HttpUtils.BASE_URL + music.getMusicPath();
			String path = "/mnt/sdcard/" + music.getMusicPath();

			// 启动Service，执行下载
			Intent intent = new Intent(this, MusicDownloadService.class);
			intent.putExtra("uri", uri);
			intent.putExtra("path", path);
			startService(intent);

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
		biz = new MusicBiz();
		setupView();
		addListener();

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		adapter.quit();
	}
}