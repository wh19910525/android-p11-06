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
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);//自己的main布局达不到效果,可以尝试使用系统的相关布局
		atvHobby.setAdapter(adapter);
		atvHobby.setThreshold(1);//默认输入两个字符 才提示，现在 改 为1个字符提示；
		matvHobby = (MultiAutoCompleteTextView) findViewById(R.id.matvHobby);
		//设置间隔符为逗号间隔符
		matvHobby.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
		matvHobby.setAdapter(adapter);
		matvHobby.setThreshold(1);//默认输入两个字符 才提示，现在 改 为1个字符提示；
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}
}