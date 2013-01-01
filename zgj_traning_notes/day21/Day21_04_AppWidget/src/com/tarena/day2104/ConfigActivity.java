package com.tarena.day2104;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class ConfigActivity extends Activity {
	
	private int appWidgetId;
	private EditText etInput;

	private void setupView() {
		etInput = (EditText) findViewById(R.id.etInput);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Log.i("info", "ConfigActivity.onCreate");

//�������� ��ʲô��
		// ��ȡ�����������������ʵ����id
		appWidgetId = getIntent().getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		// ���δ��ȷ��ȡid������������Ϊ
		if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
			setResult(RESULT_CANCELED);//
			finish();
		}

		setupView();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i("info", "onStart");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("info", "onResume");
	}

	public void doClick(View v) {
		int numb = Integer.parseInt(etInput.getText().toString());

		Intent intent = new Intent("com.tarena.action.CONFIGURE_OK");
		intent.putExtra("numb", numb);
		sendBroadcast(intent);

		// ���÷���ֵΪRESULT_OK�������ɹ�
		Intent data = new Intent();
		data.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);//
		setResult(RESULT_OK, data);//
		finish();
	}
}