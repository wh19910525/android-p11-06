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

	private void setupView() {//参考：Day05_01_ListView_t2
		
		//获取 Gallery控件 的引用
		galApps = (Gallery) findViewById(R.id.galTest);
		
		//获取数据集，并且实例化adapter
		adapter = new AppAdapter(this, biz.getAppInfos());
		
		//设置Gallery的adapter
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