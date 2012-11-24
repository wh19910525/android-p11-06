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
			Toast.makeText(this, "������� ��¼ ��ť", 3000).show();
			break;
			
		case BUTTON_CANCEL:
			Toast.makeText(this, "�� ����� ȡ�� ��ť", 3000).show();
			break;
		}
		
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
     // �������ֲ�������
     		LayoutParams params = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
        
    		// ��һ��
    		tvName = new TextView(this);
    		tvName.setText("�û���");

    		etName = new EditText(this);
    		etName.setLayoutParams(params);
    		etName.setHint("�������û���");

    		row1 = new LinearLayout(this);
    		row1.addView(tvName);
    		row1.addView(etName);
    		// �ڶ���
    		tvPassword = new TextView(this);
    		tvPassword.setText("����");

    		etPassword = new EditText(this);
    		etPassword.setLayoutParams(params);
    		// etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
    		etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());//��������
    		etPassword.setHint("����������");
        
    		row2 = new LinearLayout(this);
    		row2.addView(tvPassword);
    		row2.addView(etPassword);
    		
    		
    		// ������
    		btnLogin = new Button(this);
//    		btnCancel.setLayoutParams(params);//�� ���� Ϊʲô ������
    		btnLogin.setText("��¼");
    		btnLogin.setId(BUTTON_LOGIN);
    		btnLogin.setOnClickListener(this);
        
    		btnCancel = new Button(this);
 //   		btnCancel.setLayoutParams(params);
    		btnCancel.setText("ȡ��");
    		btnCancel.setId(BUTTON_CANCEL);
    		btnCancel.setOnClickListener(this);
    		
    		
    		row3 = new LinearLayout(this);
    		row3.addView(btnLogin, params);
    		row3.addView(btnCancel, params);
    		
    		// ������
    		root = new LinearLayout(this);
    		root.setOrientation(LinearLayout.VERTICAL);
    		root.addView(row1);
    		root.addView(row2);
    		root.addView(row3);
    		
    		// ����ͼ����ӵ�Activity��������ʾ��
    		setContentView(root);
        
    }
    
    
}