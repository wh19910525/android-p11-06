package com.tarena.day0302;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class Day03_02_AlertDialogActivity extends Activity {
	private EditText etName, etPassword;

	private void setupView() {
		// 从xml文件扩充一个View对象, 加载布局
		View root = LayoutInflater.from(this).inflate(R.layout.dialog_login,null);
		etName = (EditText) root.findViewById(R.id.etName);
		etPassword = (EditText) root.findViewById(R.id.etPassword);

		new Builder(this).setTitle("登录").setIcon(R.drawable.ic_launcher)
				.setView(root).setCancelable(false).show();
	}

	public void doClick(View v) {
//		finish();
	}

	public void login(View v) {

	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}
}