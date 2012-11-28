package com.tarena.day0311;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Day03_11_DialogActivity extends Activity {
	
	private static final int DIALOG_1 = 1;
	private static final int DIALOG_2 = 2;
	
	private EditText etInput;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btn1:
			showDialog(DIALOG_1);
			break;

		case R.id.btn2:
			showDialog(DIALOG_2);
			break;
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		Log.i("info", "onCreateDialog---id=" + id);//没有 打印 出来 log 信息
		Dialog dialog = null;
		switch (id) {
		case DIALOG_1:
			dialog = new Builder(this).setTitle("对话框1")
					.setIcon(R.drawable.ic_launcher).setMessage("这是对话框1")
					.setCancelable(false).setPositiveButton("确定", null)
					.create();
			break;

		case DIALOG_2:
			etInput = new EditText(this);
			dialog = new Builder(this).setTitle("对话框2")
					.setIcon(R.drawable.ic_launcher).setView(etInput)//设置 输入框
					.setCancelable(false)
					.setPositiveButton("确定", new OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Toast.makeText(Day03_11_DialogActivity.this,
									"您输入的姓名是:" + etInput.getText().toString(),
									3000).show();

							// removeDialog(DIALOG_2);
						}
					}).setNegativeButton("取消", null).create();
			break;
		}
		return dialog;
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		// TODO Auto-generated method stub
		Log.i("info", "onPrepareDialog----id=" + id);
		// Log.w(tag, msg)
		// Log.d(tag, msg)
		// Log.e(tag, msg)
		if (id == DIALOG_2)
			etInput.setText("wanghai");
		super.onPrepareDialog(id, dialog);
	}

}