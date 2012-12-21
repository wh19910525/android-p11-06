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
		
		//��ƫ���ļ��ж�ȡ�û���
		String name = pref.getString("userName", null);
		//����������û�������ʾ���ı������ҽ���ѡ��ѡ��
		if(name!=null){
			etName.setText(name);
			chkSaveName.setChecked(true);
		}else{//���� ����ѡ��ȡ��ѡ��
			chkSaveName.setChecked(false);
		}
	}

	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btnLogin:
			// ��ȡ�û�����
			String name = etName.getText().toString();
			String password = etPassword.getText().toString();
			// ��¼��֤
			if ("admin".equals(name) && "password".equals(password)) {
				// ��ס�û���
				Editor editor = pref.edit();
				if (chkSaveName.isChecked()) {
					editor.putString("userName", name).commit();
				} else {
					editor.remove("userName").commit();
				}
				// ��¼�ɹ�
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
				finish();
			} else {
				// ��¼ʧ��
				Toast.makeText(this, "�û������������", 3000).show();
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