package com.tarena.day0703;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class Day07_03_GridViewActivity extends Activity {
	private ArrayList<String> getData() {
		ArrayList<String> data = new ArrayList<String>();
		for (int i = 1; i <= 50; i++) {
			data.add("app" + i);
		}
		return data;
	}

	private GridView gvLaunchers;
	private ArrayAdapter<String> adapter;

	private void setupView() {
		gvLaunchers = (GridView) findViewById(R.id.gvLaunchers);
		adapter = new ArrayAdapter<String>(this, R.layout.item,R.id.tvTitle, getData());
		gvLaunchers.setAdapter(adapter);
		
		
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}
}