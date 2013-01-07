package com.tarena.day2505;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;
import android.view.View.OnCreateContextMenuListener;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;

import com.tarena.biz.ProcessBiz;
import com.tarena.entity.ProcessInfo;

public class ProcessInfosActivity extends Activity {
	private static final int MENU_CTX_DETAILS = 1;
	private static final int MENU_CTX_KILL = 2;
	private ListView lvProcesses;
	private ProcessAdapter adapter;
	private ProcessBiz biz;

	private void setupView() {
		lvProcesses = (ListView) findViewById(R.id.lvProcesses);
		adapter = new ProcessAdapter(this, biz.getRunningRrocesses());
		lvProcesses.setAdapter(adapter);
	}

	private void addListener() {
		lvProcesses
				.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {

					@Override
					public void onCreateContextMenu(ContextMenu menu, View v,
							ContextMenuInfo menuInfo) {
						// TODO Auto-generated method stub
						menu.setHeaderTitle("操作");
						menu.add(1, MENU_CTX_DETAILS, 1, "详情");
						menu.add(1, MENU_CTX_KILL, 2, "结束进程");
					}
				});
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		ProcessInfo process = (ProcessInfo) adapter.getItem(menuInfo.position);
		switch (item.getItemId()) {

		case MENU_CTX_DETAILS:
			Intent intent = new Intent(this, ProcessDetailsActivity.class);
			intent.putExtra("process", process);
			startActivity(intent);
			break;
		case MENU_CTX_KILL:
			for (String pkgName : process.getPkgList()) {
				biz.killProcess(pkgName);
			}
			adapter.changeData(biz.getRunningRrocesses());
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		biz = new ProcessBiz(this);
		setupView();
		addListener();
	}
}