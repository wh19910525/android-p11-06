package com.tarena.day0604;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Day06_04_ArrayAdapterActivity extends Activity {
	
	// private String[] getData() {
	// return getResources().getStringArray(R.array.test);
	// }

	private ListView lvData;
//	private ArrayAdapter<String> adapter;
	private ArrayAdapter adapter;

	private void setupView() {
		lvData = (ListView) findViewById(R.id.lvData);
//		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getData());
		adapter = ArrayAdapter.createFromResource(this, R.array.test, android.R.layout.simple_list_item_1);
		
		lvData.setAdapter(adapter);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}
}