package com.tarena.day0703;

import com.tarena.biz.AppBiz;
import com.tarena.day0703.adapter.AppAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Gallery;

public class Day07_04_GalleryActivity extends Activity {
	private Gallery galApps;
	private AppBiz biz;
	private AppAdapter adapter;

	private void setupView() {
		galApps = (Gallery) findViewById(R.id.galTest);
		adapter = new AppAdapter(this, biz.getAppInfos());
		galApps.setAdapter(adapter);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		biz = new AppBiz();
		setupView();
	}
}