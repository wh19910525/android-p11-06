package com.tarena.day0503;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends Activity {
	
	private EditText etInput;

	private void setupView() {
		etInput = (EditText) findViewById(R.id.etInput);
	}

	public void doClick(View v) {
		String value = etInput.getText().toString();
		// setResult(resultCode)
		Intent intent = new Intent();
		intent.putExtra("key", value);
		setResult(RESULT_OK, intent);//
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		setupView();
	}
}
