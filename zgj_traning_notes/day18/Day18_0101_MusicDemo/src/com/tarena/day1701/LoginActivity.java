package com.tarena.day1701;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
	private SharedPreferences pref;//SharedPreferences�ɽ���ƫ������

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
			// ��¼�ɹ�
			// ��ס�û���
			if (chkSaveName.isChecked()) {
//				Editor edit = pref.edit();
//				edit.putString(key, value)
				pref.edit().putString("username", name).commit();//�˾�ʵ��Ϊ����2��ļ�д
			} else {
				pref.edit().remove("username").commit();
			}
			// ��ת
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);

			finish();

		} else {
			// ��¼ʧ��
			Toast.makeText(this, "�û������������", 3000).show();
			etPass.setText("");
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		//ƫ������Ϊ:Ĭ���������µ���������Է��ʴ�ƫ������
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		setupView();
	}
}
