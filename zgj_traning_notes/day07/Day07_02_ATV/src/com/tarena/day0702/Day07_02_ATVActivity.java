package com.tarena.day0702;

import android.app.Activity;
import android.os.Bundle;
import android.text.AutoText;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class Day07_02_ATVActivity extends Activity {
	
	private String[] data = { "abfdd", "abf", "abd", "ab","sleep", "eat", "read", "play", "playGame", "review"};
	
	private AutoCompleteTextView atvHobby;
	private MultiAutoCompleteTextView matvHobby;
	private ArrayAdapter<String> adapter;

	private void setupView() {
		atvHobby = (AutoCompleteTextView) findViewById(R.id.atvHobby);
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);//�Լ���main���ִﲻ��Ч��,���Գ���ʹ��ϵͳ����ز���
		atvHobby.setAdapter(adapter);
		atvHobby.setThreshold(1);//Ĭ�����������ַ� ����ʾ������ �� Ϊ1���ַ���ʾ��
		matvHobby = (MultiAutoCompleteTextView) findViewById(R.id.matvHobby);
		//���ü����Ϊ���ż����
		matvHobby.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		matvHobby.setAdapter(adapter);
		matvHobby.setThreshold(1);//Ĭ�����������ַ� ����ʾ������ �� Ϊ1���ַ���ʾ��
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}
}