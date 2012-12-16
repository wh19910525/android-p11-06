package com.tarena.day1103;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.conn.ConnectTimeoutException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
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
import com.tarena.day1103.adapter.MusicAdapter;
import com.tarena.entity.Music;
import com.tarena.utils.HttpUtils;
import com.tarena.utils.StreamUtils;

public class MusicClientActivity extends Activity {
	
	private static final int MENU_CONTEXT_DETAILS = 1;
	private static final int MENU_CONTEXT_DOWNLOAD = 2;
	private static final int MSG_TAG_STARTED = 1;
	private static final int MSG_TAG_FINISHED = 2;

	private ListView lvMusics;
	private MusicBiz biz;
	private MusicAdapter adapter;
	private AlertDialog dialog;
	
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_TAG_STARTED:// ��ʼ����
				Toast.makeText(MusicClientActivity.this, "������������xml�����Ժ�...",
						3000).show();
				break;

			case MSG_TAG_FINISHED:// �������
				// ���½���
				adapter.changeData((ArrayList<Music>) msg.obj);
				break;
			}
		};
	};

	private void setupView() {
		// ��ʼ��listView
		lvMusics = (ListView) findViewById(R.id.lvMusics);
		adapter = new MusicAdapter(this, null, lvMusics);//
		lvMusics.setAdapter(adapter);

		// ��ʼ���Ի���
		dialog = new Builder(this).setTitle("����")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setPositiveButton("ȷ��", null).setCancelable(true).create();

		// ���������̣߳���������xml
		new Thread() {
			public void run() {
				// ��������xml����ȡʵ�弯��
				handler.sendEmptyMessage(MSG_TAG_STARTED);
				ArrayList<Music> musics = biz.getMusics(
						"http://192.168.1.101:8080/musiconline/sounds.xml",
						null, HttpUtils.METHOD_POST);
				// ������Ϣ�����̸߳��½���
				Message msg = Message.obtain(handler, MSG_TAG_FINISHED, musics);
				msg.sendToTarget();
			};
		}.start();
	}

	private void addListener() {
		lvMusics.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				// TODO Auto-generated method stub
				menu.setHeaderIcon(android.R.drawable.ic_dialog_info);
				menu.setHeaderTitle("����");
				menu.add(1, MENU_CONTEXT_DETAILS, 1, "����");
				menu.add(1, MENU_CONTEXT_DOWNLOAD, 2, "����");
			}
		});
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// ��ȡ��ǰmusic��Ϣ
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		Music music = (Music) adapter.getItem(menuInfo.position);

		switch (item.getItemId()) {
		case MENU_CONTEXT_DETAILS:// ����
			dialog.setMessage(music.toString());
			dialog.show();
			break;

		case MENU_CONTEXT_DOWNLOAD:// ����
			try {
				String uri = HttpUtils.BASE_URL + music.getMusicPath();
				String path = "/mnt/sdcard/" + music.getMusicPath();
				HttpEntity entity = HttpUtils.getEntity(uri, null,
						HttpUtils.METHOD_GET);
				InputStream in = HttpUtils.getStream(entity);
				StreamUtils.save(in, path);
			} catch (ConnectTimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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