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

	private void setupView() {
		lvMusics = (ListView) findViewById(R.id.lvMusics);
		adapter = new MusicAdapter(this, biz.getMusics());
		lvMusics.setAdapter(adapter);

		dialog1 = new Builder(this).setTitle("��ʾ")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setCancelable(false).setMessage("����ɨ��SDCard�����Ժ�...").create();
		dialog2 = new Builder(this).setTitle("����")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setPositiveButton("ȷ��", null).create();
	}

	private void addListener() {
		lvMusics.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				// TODO Auto-generated method stub
				menu.setHeaderTitle("����");

				menu.add(1, MENU_CONTEXT_DETAILS, 1, "����");
				menu.add(1, MENU_CONTEXT_DELETE, 2, "ɾ��");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(1, MENU_OPTS_FLUSH, 1, "ˢ��");
		menu.add(1, MENU_OPTS_EXIT, 2, "�˳�");
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU_OPTS_FLUSH:// ˢ��
			Intent intent = new Intent(Intent.ACTION_MEDIA_MOUNTED);
			intent.setData(Uri.parse("file://"
					+ Environment.getExternalStorageDirectory()));
			sendBroadcast(intent);
			break;
		case MENU_OPTS_EXIT:// �˳�
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
		case MENU_CONTEXT_DETAILS:// ����
			dialog2.setMessage(m.toString());
			dialog2.show();
			break;
		case MENU_CONTEXT_DELETE:// ɾ��
			// �����ݿ�ɾ��
			int count = biz.delete(m.getId());
			// ���ɾ���ɹ�
			if (count > 0) {
				// ɾ���ļ�
				File file = new File(m.getPath());
				file.delete();
				if (!file.exists()) {
					// ˢ�½���
					adapter.removeItem(menuInfo.position);
				}

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

		receiver = new MediaScannerReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
		filter.addAction(Intent.ACTION_MEDIA_SCANNER_STARTED);
		filter.addDataScheme("file");
		registerReceiver(receiver, filter);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unregisterReceiver(receiver);
	}

	private MediaScannerReceiver receiver;

	class MediaScannerReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			if (Intent.ACTION_MEDIA_SCANNER_STARTED.equals(action)) {
				dialog1.show();
			} else if (Intent.ACTION_MEDIA_SCANNER_FINISHED.equals(action)) {
				dialog1.dismiss();
				// ˢ�½���
				adapter.changeData(biz.getMusics());
			}
		}
	}
}