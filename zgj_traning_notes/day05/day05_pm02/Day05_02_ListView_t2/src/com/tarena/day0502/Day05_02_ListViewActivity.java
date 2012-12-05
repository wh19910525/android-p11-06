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
	private MusicAdapter adapter;//�̳� BaseAdapter
	private ListView lvMusics;
	private AlertDialog dialog;

	private void setupView() {
		//��ȡListView������
		lvMusics = (ListView) findViewById(R.id.lvMusics);
		
		//ʵ����adapter�������� MusicBiz �Ĺ��캯���� ��ȡ ���ݼ�
		adapter = new MusicAdapter(this, biz.getMusics());//ע�� �� ����������
		
		//����listView��adapter
		lvMusics.setAdapter(adapter);

		dialog = new Builder(this).setTitle("����")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setPositiveButton("ȷ��", null).create();
	}

	private void addListener() {//
		lvMusics.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {//������� �Ƕ� ListView �ؼ������ģ��� �� ��� ����ListView��� ��Ŀʱ�����ô˷���

			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,//
					ContextMenuInfo menuInfo) {
				// TODO Auto-generated method stub
				menu.setHeaderIcon(android.R.drawable.ic_menu_camera);
				menu.setHeaderTitle("����");
				menu.add(2, MENU_CONTEXT_DETAILS, 1, "����");
				menu.add(2, MENU_CONTEXT_UPDATE, 2, "�޸�");
				menu.add(2, MENU_CONTEXT_DELETE, 3, "ɾ��");
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
	public boolean onCreateOptionsMenu(Menu menu) {//�ο� day03_12
		// TODO Auto-generated method stub
		menu.add(1, MENU_OPTS_ADD, 1, "���").setIcon(
				android.R.drawable.ic_menu_add);
		menu.add(1, MENU_OPTS_EXIT, 2, "�˳�").setIcon(
				android.R.drawable.ic_menu_close_clear_cancel);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {//�ڶ� �� ���������� ���� setResult ��������
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		switch (resultCode) {
		case RESULT_OK:
			int opType = data.getIntExtra(GlobalUtils.EXTRA_OP_TYPE,
					GlobalUtils.OP_TYPE_ADD);
			Music music = (Music) data
					.getSerializableExtra(GlobalUtils.EXTRA_OP_DATA);//��ȡ �� putExtra()���͵� ��EXTRA_OP_DATA ��Ӧ��ֵ  �ࣻ
			if (music != null) {
				switch (opType) {
				case GlobalUtils.OP_TYPE_ADD:// ���
					biz.addMusic(music);//�������
					break;

				case GlobalUtils.OP_TYPE_UPDATE:// �޸�
					biz.modifyMusic(music);//�޸�����
					break;
				}
				//���½���
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
		case MENU_OPTS_ADD:// ���
			Intent intent = new Intent(this, MusicOpActivity.class);
			intent.putExtra(GlobalUtils.EXTRA_OP_TYPE, GlobalUtils.OP_TYPE_ADD);//
			intent.putExtra(GlobalUtils.EXTRA_MUSIC_ID, adapter.getCount() + 1);//�ڶ��������� ��һ�� activity��ʹ��
			// startActivity(intent);
			startActivityForResult(intent, 0);//��activity1 ��� ������� ������ ��һ�� activity2֮�󣬵��� activity2�� ʹ���� setResult ���� ֮����ô ���Զ� ����activity1���onActivityResult������ 
			break;

		case MENU_OPTS_EXIT:// �˳�
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {//�� ��� ���� ListView ��������� ѡ��ʱ��ִ�� �η���
		// TODO Auto-generated method stub
		
		// ��ȡ�����Ĳ˵���Ϣ,��ǿתΪAdapterCotnextMenuInfo
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item.getMenuInfo();
		
		// ����menuinfo�е�position���ԣ���adapter�� ��ȡ ��������item��Ӧ�� ������
		Music music = (Music) adapter.getItem(menuInfo.position);

		switch (item.getItemId()) {//getItemId ��ȡ ��������� ѡ���
		case MENU_CONTEXT_DETAILS:// ����
			dialog.setMessage(music.toString());
			dialog.show();
			break;
		case MENU_CONTEXT_UPDATE:// �޸�
			Intent intent = new Intent(this, MusicOpActivity.class);
			// ���ò�������Ϊ�޸�
			intent.putExtra(GlobalUtils.EXTRA_OP_TYPE,
					GlobalUtils.OP_TYPE_UPDATE);
			// Ҫ�޸ĵ�����
			intent.putExtra(GlobalUtils.EXTRA_OP_DATA, music);
			// startActivity(intent);
			startActivityForResult(intent, 0);
			break;
		case MENU_CONTEXT_DELETE:// ɾ��
			biz.removeMusic(music);// �Ӽ�����ɾ��ָ��������
			adapter.notifyDataSetChanged();// ����listview
			break;
		}
		return super.onContextItemSelected(item);
	}
}