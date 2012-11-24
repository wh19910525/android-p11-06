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
import android.widget.Toast;

public class Day03_02_AlertDialogActivity extends Activity {
	private EditText etName, etPassword;
	private AlertDialog dialog;

	private void setupView() {
		View root = LayoutInflater.from(this).inflate(R.layout.dialog_login,
				null);
		etName = (EditText) root.findViewById(R.id.etName);
		etPassword = (EditText) root.findViewById(R.id.etPassword);

		dialog = new Builder(this).setTitle("登录")
				.setIcon(R.drawable.ic_launcher).setView(root)
				.setCancelable(false).show();
	}

	public void doClick(View v) {
		// finish();
	}

	private int count;

	public void login(View v) {
		switch (v.getId()) {
		case R.id.btnLogin:
			// 获取用户的输入
			String name = etName.getText().toString();
			String password = etPassword.getText().toString();
			// 登录验证
			if ("admin".equals(name) && "123456".equals(password)) {
				// 登录成功
				dialog.dismiss();
			} else {
				if (++count < 3) {
					Toast.makeText(this, "您的输入有误，请重试", 3000).show();
				} else {
					Toast.makeText(this, "连续三次输入错误，程序退出", 3000).show();
					dialog.dismiss();
					finish();
				}
			}
			break;

		case R.id.btnCancel:
			dialog.dismiss();
			finish();
			break;
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}
}