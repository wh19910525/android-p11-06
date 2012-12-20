package com.tarena.day1406;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Day14_06_MyIntentServiceActivity extends Activity {
	
	private EditText etName;

	private void setupView() {
		etName = (EditText) findViewById(R.id.etName);
	}

	public void doClick(View v) {
		// ��ȡ����·��
		String path = etName.getText().toString();
		// ����Service
		Intent intent = new Intent(this, MyService.class);
		intent.putExtra("path", path);
		startService(intent);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}
}