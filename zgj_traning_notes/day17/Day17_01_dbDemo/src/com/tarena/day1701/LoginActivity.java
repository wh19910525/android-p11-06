package com.tarena.day1701;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.tarena.biz.MusicBiz;
import com.tarena.biz.UserBiz;
import com.tarena.entity.Music;
import com.tarena.entity.User;
import com.tarena.utils.GlobalUtils;

public class LoginActivity extends Activity {
	private EditText etName, etPass;
	private CheckBox chkSaveName;
	private SharedPreferences pref;

	private void setupView() {
		etName = (EditText) findViewById(R.id.etUserName);
		etPass = (EditText) findViewById(R.id.etPassword);
		chkSaveName = (CheckBox) findViewById(R.id.chkSaveName);

		String name = pref.getString("username", null);
		if (name != null) {
			chkSaveName.setChecked(true);
			etName.setText(name);
		}
	}

	public void doClick(View v) {
		String name = etName.getText().toString();
		String password = etPass.getText().toString();
		User user = new User(name, password);

		if (new UserBiz(this).isExists(user)) {
			// 登录成功
			// 记住用户名
			if (chkSaveName.isChecked()) {
				pref.edit().putString("username", name).commit();
			} else {
				pref.edit().remove("username").commit();
			}
			// 跳转
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);

			finish();

		} else {
			// 登录失败
			Toast.makeText(this, "用户名或密码错误", 3000).show();
			etPass.setText("");
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		setupView();
	}
}
