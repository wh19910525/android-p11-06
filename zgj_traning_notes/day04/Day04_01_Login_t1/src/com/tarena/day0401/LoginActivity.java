package com.tarena.day0401;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	private EditText etNmae, etPass;

	private void setupView() {
		etNmae = (EditText) findViewById(R.id.etName);
		etPass = (EditText) findViewById(R.id.etPass);
	}

	private boolean isEmpty(EditText et) {
		if (et.getText() == null || "".equals(et.getText().toString().trim())) {
			return true;
		}
		return false;
	}

	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btnLogin:// ��¼
			// �ǿ���֤
			if (isEmpty(etNmae)) {
				etNmae.setError("�û�������Ϊ��");
				return;
			}
			if (isEmpty(etPass)) {
				etPass.setError("���벻��Ϊ��");
				return;
			}
			// ��ȡ����
			String name = etNmae.getText().toString();
			String pass = etPass.getText().toString();
			// ��¼��֤
			if ("w".equals(name) && ".".equals(pass)
					|| "zhangsan".equals(name) && "111111".equals(pass)) {
				// ����MainActivity
				// Intent intent = new Intent(this, MainActivity.class);
				// Intent intent = new Intent();
				// intent.setClass(this, MainActivity.class);

				// Intent intent = new Intent();
				// ComponentName component = new ComponentName(this,
				// MainActivity.class);
				// ComponentName component = new ComponentName(this,
				// "com.tarena.day0401.MainActivity");
				// ComponentName component = new ComponentName(
				// this.getPackageName(),
				// "com.tarena.day0401.MainActivity");
				// intent.setComponent(component);
				//

				//��һ��
				Intent intent = new Intent("com.tarena.action.TEST");
				startActivity(intent);//�������� activity���action�� ���intent����ô���������activity��
				//�ڶ���
//				Intent intent = new Intent(this, MainActivity.class);
//				startActivity(intent);
				//������
//				Intent intent = new Intent();
//				intent.setClass(this, MainActivity.class);
//				startActivity(intent);
				//������
//				Intent intent = new Intent();
//				ComponentName component = new ComponentName(this,MainActivity.class);
//				intent.setComponent(component);
//				startActivity(intent);
				//������
//				Intent intent = new Intent();
//				ComponentName component = new ComponentName(this, "com.tarena.day0401.MainActivity");
//				intent.setComponent(component);
//				startActivity(intent);
				//������
//				Intent intent = new Intent();
//				ComponentName component = new ComponentName(this.getPackageName(), //��һ�������� ����
//				"com.tarena.day0401.MainActivity");
//				intent.setComponent(component);
//				startActivity(intent);
				
				
				
				finish();
			} else {
				Toast.makeText(this, "�û������������", 3000).show();
				etPass.setText("");
			}
			break;

		case R.id.btnCancel:// ȡ��
			finish();
			break;
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		setupView();
	}
}