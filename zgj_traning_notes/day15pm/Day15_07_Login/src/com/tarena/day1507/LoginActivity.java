package com.tarena.day1507;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private EditText etName, etPassword;
	private CheckBox chkSaveName;
	private SharedPreferences pref;

	private void setupView() {
		etName = (EditText) findViewById(R.id.etUserName);
		etPassword = (EditText) findViewById(R.id.etUserPass);
		chkSaveName = (CheckBox) findViewById(R.id.chkSaveName);
		
		//从偏好文件中读取用户名
		String name = pref.getString("userName", null);
		//如果保存了用户名，显示在文本框中且将复选框选中
		if(name!=null){
			etName.setText(name);
			chkSaveName.setChecked(true);
		}else{//否则 将复选框取消选中
			chkSaveName.setChecked(false);
		}
	}

	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btnLogin:
			// 获取用户输入
			String name = etName.getText().toString();
			String password = etPassword.getText().toString();
			// 登录验证
			if ("admin".equals(name) && "password".equals(password)) {
				// 记住用户名
				Editor editor = pref.edit();
				if (chkSaveName.isChecked()) {
					editor.putString("userName", name).commit();
				} else {
					editor.remove("userName").commit();
				}
				// 登录成功
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
				finish();
			} else {
				// 登录失败
				Toast.makeText(this, "用户名或密码错误", 3000).show();
				etPassword.setText("");
			}
			break;
		case R.id.btnReset:
			etName.setText("");
			etPassword.setText("");
			break;
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		setupView();
	}
}