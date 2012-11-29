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
			if ("wh".equals(name) && "123".equals(pass)
					|| "zhangsan".equals(name) && "111111".equals(pass)) {
				// 启动MainActivity
				/**开启新的Activity,有几下几种方式:*/
				/**第一种:*/
				// Intent intent = new Intent(this, MainActivity.class);
				/**第二种:*/
				// Intent intent = new Intent();
				// intent.setClass(this, MainActivity.class);
				/**第三种:*/
				// Intent intent = new Intent();
				// ComponentName component = new ComponentName(this,
				// MainActivity.class);
				/**第四种:*/
				// ComponentName component = new ComponentName(this,
				// "com.tarena.day0401.MainActivity");
				/**第五种:*/
				// ComponentName component = new ComponentName(
				// this.getPackageName(),
				// "com.tarena.day0401.MainActivity");
				/**其中第三、四、五种方式需要先将ComponenName对象添加到Intent对象中。
				 * 但是以上的五种方式均属于显示意图*/
				// intent.setComponent(component);
				//
				/**第六种,但区别与以上几种此为:隐式意图
				 * 这个例子为action测试
				 * 共有3种测试原则:
				 * 		1.action的测试原则:
				 * 		2.category的测试原则
				 * 		3.data的测试原则
				 * 具体内容,查看当天文本笔记*/
				Intent intent = new Intent("com.tarena.action.TEST");
				
//				intent.putExtra("userName", name);
//				intent.putExtra("testkey", 15);
//				intent.putExtras(intent);
				
				/**Bundle 类似于Map,储存 键值对.
				 * 但是键只能是String类型,值可以是数据基本类型或基本类型数组。
				 * 作用用于将相关参数传给新建的Activity,或者采用上面被注释的例子,采用
				 * intent.putExtra()直接进行传值*/
				
				Bundle data = new Bundle();//
				data.putString("userName", name);
				data.putInt("testKey", 25);				
				intent.putExtras(data);//
				
				startActivity(intent);
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