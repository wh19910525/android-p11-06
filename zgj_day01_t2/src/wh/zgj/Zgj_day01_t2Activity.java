package wh.zgj;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class Zgj_day01_t2Activity extends Activity implements OnClickListener{
	private static final int BUTTON_LOGIN = 1;
	private static final int BUTTON_CANCEL = 2;

	private TextView tvName, tvPassword;
	private EditText etName, etPassword;
	private Button btnLogin, btnCancel;
	private LinearLayout root, row1, row2, row3;
	
	public void onClick(View v)
	{
		switch (v.getId()){
		case BUTTON_LOGIN:
			Toast.makeText(this, "您点击了 登录 按钮", 3000).show();
			break;
			
		case BUTTON_CANCEL:
			Toast.makeText(this, "您 点击了 取消 按钮", 3000).show();
			break;
		}
		
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
     // 创建布局参数对象
     		LayoutParams params = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
        
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
    		tvPassword.setText("密码");

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
//    		btnCancel.setLayoutParams(params);//在 这里 为什么 不能用
    		btnLogin.setText("登录");
    		btnLogin.setId(BUTTON_LOGIN);
    		btnLogin.setOnClickListener(this);
        
    		btnCancel = new Button(this);
 //   		btnCancel.setLayoutParams(params);
    		btnCancel.setText("取消");
    		btnCancel.setId(BUTTON_CANCEL);
    		btnCancel.setOnClickListener(this);
    		
    		
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
    
    
}