package zgj.wh;

import android.app.Activity;
import android.os.Bundle;

import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class Zgj_day01_01_t1Activity extends Activity implements OnClickListener{
	private static final int BUTTON_LOGIN = 1;
	private static final int BUTTON_CANCEL = 2;

	private TextView tvName, tvPassword;
	private EditText etName, etPassword;
	private Button btnLogin, btnCancel;
	private LinearLayout root, row1, row2, row3;

	private ButtonClickListener listener;//这是一个 内部类

	public void onClick(View v) {//这是 OnClickListener 接口里的一个 方法；
		// TODO Auto-generated method stub
		switch (v.getId()){
		case BUTTON_LOGIN:
			Toast.makeText(this, "您单击了登录按钮", 3000).show();
			break;

		case BUTTON_CANCEL:
			Toast.makeText(this, "您单击了取消按钮", 3000).show();
			break;
		}
	}	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		// 创建布局参数对象
		LayoutParams params = new LayoutParams(0, LayoutParams.WRAP_CONTENT,
				1.0f);

		// 创建按钮的单击事件监听器对象
		listener = new ButtonClickListener();

		// 第一行
		tvName = new TextView(this);
		tvName.setText("用户名");

		etName = new EditText(this);
		etName.setLayoutParams(params);
		etName.setHint("请输入用户名");

		row1 = new LinearLayout(this);
		row1.addView(tvName);
		row1.addView(etName);
		// 第二行
		tvPassword = new TextView(this);
		tvPassword.setText("密    码");

		etPassword = new EditText(this);
		etPassword.setLayoutParams(params);
		// etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
		etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());//隐藏密码
		etPassword.setHint("请输入密码");

		row2 = new LinearLayout(this);
		row2.addView(tvPassword);
		row2.addView(etPassword);
		
		
		// 第三行
		btnLogin = new Button(this);
		btnLogin.setText("登录");
		btnLogin.setId(BUTTON_LOGIN);
		btnLogin.setOnClickListener(listener);//方法一：这里使用 内部类 ButtonClickListener 实现监听;
//		btnLogin.setOnClickListener(this);//方法二：这里使用 OnClickListener 接口  实现监听；使用一个接口 必须 直接在 本类里 实现这个接口的所有方法，否则 这个类 不能使用；

		btnCancel = new Button(this);
		//btnCancel.setLayoutParams(params);
		btnCancel.setText("取消");
		btnCancel.setId(BUTTON_CANCEL);
		// btnCancel.setOnClickListener(this);
		//btnCancel.setOnClickListener(new View.OnClickListener() {//方法三：这里使用 匿名类 实现监听
		//btnCancel.setOnClickListener(new Button.OnClickListener() {
		btnCancel.setOnClickListener(new EditText.OnClickListener() {		
			public void onClick(View v) {
				// TODO Auto-generated method stub
				etName.setText("");
				etPassword.setText("");
			}
		});
		
		row3 = new LinearLayout(this);
		row3.addView(btnLogin, params);
		row3.addView(btnCancel, params);
		
		// 跟容器
		root = new LinearLayout(this);
		root.setOrientation(LinearLayout.VERTICAL);
		root.addView(row1);
		root.addView(row2);
		root.addView(row3);
		
		// 将视图树添加到Activity的内容显示区
		setContentView(root);
    }
 
	private boolean isEmpty(EditText et) {
		if (et.getText() == null || "".equals(et.getText().toString().trim())) {
			return true;
		}
		return false;
	}

	class ButtonClickListener implements OnClickListener //如何才能调用这个监听器
	{
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// String text = ((Button)v).getText().toString();
			// if("登录".equals(text)){
			// //登录按钮的事件处理
			// }else{
			// //取消按钮的事件处理
			// }

			switch (v.getId()) {
			case BUTTON_LOGIN:// 登录
				// 输入有效性验证
				if (isEmpty(etName)) {
					etName.setError("用户名不能为空");
					return;
				}
				if (isEmpty(etPassword)) {
					etPassword.setError("密码不能为空");
					return;
				}
				// 获取用户输入
				String name = etName.getText().toString();
				String password = etPassword.getText().toString();
				// 登录验证
				if ("admin".equals(name) && "123456".equals(password)) {
					// 登录成功
					Toast.makeText(Zgj_day01_01_t1Activity.this, "登录成功", 3000).show();
				} else {
					// 登录失败
					Toast.makeText(Zgj_day01_01_t1Activity.this, "用户名或密码错误", 3000).show();
					etPassword.setText("");
				}
				break;
			case BUTTON_CANCEL:// 取消
				Zgj_day01_01_t1Activity.this.finish();
				break;
			}
		}
	}    
}