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
			if ("wh".equals(name) && "123".equals(pass)
					|| "zhangsan".equals(name) && "111111".equals(pass)) {
				// ����MainActivity
				/**�����µ�Activity,�м��¼��ַ�ʽ:*/
				/**��һ��:*/
				// Intent intent = new Intent(this, MainActivity.class);
				/**�ڶ���:*/
				// Intent intent = new Intent();
				// intent.setClass(this, MainActivity.class);
				/**������:*/
				// Intent intent = new Intent();
				// ComponentName component = new ComponentName(this,
				// MainActivity.class);
				/**������:*/
				// ComponentName component = new ComponentName(this,
				// "com.tarena.day0401.MainActivity");
				/**������:*/
				// ComponentName component = new ComponentName(
				// this.getPackageName(),
				// "com.tarena.day0401.MainActivity");
				/**���е������ġ����ַ�ʽ��Ҫ�Ƚ�ComponenName������ӵ�Intent�����С�
				 * �������ϵ����ַ�ʽ��������ʾ��ͼ*/
				// intent.setComponent(component);
				//
				/**������,�����������ϼ��ִ�Ϊ:��ʽ��ͼ
				 * �������Ϊaction����
				 * ����3�ֲ���ԭ��:
				 * 		1.action�Ĳ���ԭ��:
				 * 		2.category�Ĳ���ԭ��
				 * 		3.data�Ĳ���ԭ��
				 * ��������,�鿴�����ı��ʼ�*/
				Intent intent = new Intent("com.tarena.action.TEST");
				
//				intent.putExtra("userName", name);
//				intent.putExtra("testkey", 15);
//				intent.putExtras(intent);
				
				/**Bundle ������Map,���� ��ֵ��.
				 * ���Ǽ�ֻ����String����,ֵ���������ݻ������ͻ�����������顣
				 * �������ڽ���ز��������½���Activity,���߲������汻ע�͵�����,����
				 * intent.putExtra()ֱ�ӽ��д�ֵ*/
				
				Bundle data = new Bundle();//
				data.putString("userName", name);
				data.putInt("testKey", 25);				
				intent.putExtras(data);//
				
				startActivity(intent);
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