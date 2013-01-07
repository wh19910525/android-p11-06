package com.tarena.day2502;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import com.tarena.biz.LauncherBiz;
import com.tarena.entity.Launcher;

public class LaunchersActivity extends Activity {
	
	private GridView gvLaunchers;
	private LauncherAdapter adapter;
	private LauncherBiz biz;

	private void setupView() {
		gvLaunchers = (GridView) findViewById(R.id.gvLaunchers);
		adapter = new LauncherAdapter(this, biz.getLaunchers());
		gvLaunchers.setAdapter(adapter);
	}

	private void addListener() {
		gvLaunchers.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				// TODO Auto-generated method stub
				Launcher launcher = (Launcher) adapter.getItem(position);
				startActivity(launcher.getIntent());
			}

		});
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		biz = new LauncherBiz(this);
		setupView();
		addListener();
	}
}