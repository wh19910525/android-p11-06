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
		case R.id.btnLogin:// 登录
			// 非空验证
			if (isEmpty(etNmae)) {
				etNmae.setError("用户名不能为空");
				return;
			}
			if (isEmpty(etPass)) {
				etPass.setError("密码不能为空");
				return;
			}
			// 获取输入
			String name = etNmae.getText().toString();
			String pass = etPass.getText().toString();
			// 登录验证
			if ("w".equals(name) && ".".equals(pass)
					|| "zhangsan".equals(name) && "111111".equals(pass)) {
				// 启动MainActivity
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

				//第一种
				Intent intent = new Intent("com.tarena.action.TEST");
				startActivity(intent);//被启动的 activity里的action是 这个intent，那么将启动这个activity；
				//第二种
//				Intent intent = new Intent(this, MainActivity.class);
//				startActivity(intent);
				//第三种
//				Intent intent = new Intent();
//				intent.setClass(this, MainActivity.class);
//				startActivity(intent);
				//第四种
//				Intent intent = new Intent();
//				ComponentName component = new ComponentName(this,MainActivity.class);
//				intent.setComponent(component);
//				startActivity(intent);
				//第五种
//				Intent intent = new Intent();
//				ComponentName component = new ComponentName(this, "com.tarena.day0401.MainActivity");
//				intent.setComponent(component);
//				startActivity(intent);
				//第六种
//				Intent intent = new Intent();
//				ComponentName component = new ComponentName(this.getPackageName(), //第一个参数是 包名
//				"com.tarena.day0401.MainActivity");
//				intent.setComponent(component);
//				startActivity(intent);
				
				
				
				finish();
			} else {
				Toast.makeText(this, "用户名或密码错误", 3000).show();
				etPass.setText("");
			}
			break;

		case R.id.btnCancel:// 取消
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