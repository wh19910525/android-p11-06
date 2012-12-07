package com.tarnea.day0701;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Day07_01_SpinnerActivity extends Activity {
	
	private String[] getData() {
		String[] data = { "�����", "���ж���", "�Ҷ���" };
		return data;
	}

	private Spinner spiAlign;
	private ArrayAdapter<String> adapter;

	private void setupView() {
		spiAlign = (Spinner) findViewById(R.id.spiAlign);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getData());
		//�������������ʽ,���û���������,��Ĭ�Ϸ�ʽ,���������д���
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	
		spiAlign.setAdapter(adapter);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}
}