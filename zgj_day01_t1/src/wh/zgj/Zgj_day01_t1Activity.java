package wh.zgj;

import android.app.Activity;

import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

import android.view.View.OnClickListener;



public class Zgj_day01_t1Activity extends Activity {
	private static final int BUTTON_LOGIN = 1;
	private static final int BUTTON_CANCEL = 2;
	
	private TextView tvName, tvPassword;
	private EditText etName, etPassword;
	private Button btLogin, btCancel;
	private LinearLayout root, row1, row2, row3;
	
	private ButtonClickListener listener;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
        
		LayoutParams params = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);//Ϊʲôû��3������
//        LayoutParams params = new LayoutParams(100, LayoutParams.WRAP_CONTENT);
        
        listener = new ButtonClickListener();
        
        //��һ��
        tvName = new TextView(this);
        tvName.setText("�û���");
        
        etName = new EditText(this);
        etName.setHint("������ �û���");
        etName.setLayoutParams(params);
        
        row1 = new LinearLayout(this);
        row1.addView(tvName);
        row1.addView(etName);
        
        
        //�ڶ���
        tvPassword = new TextView(this);
        tvPassword.setText("����");
        
        etPassword = new EditText(this);
        etPassword.setHint("������ ����");
        etPassword.setLayoutParams(params);
        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());//��������
        
        row2 = new LinearLayout(this);
        row2.addView(tvPassword);
        row2.addView(etPassword);
        
        //������
        btLogin = new Button(this);
        btLogin.setText("��½");
        btLogin.setId(BUTTON_LOGIN);        
        btLogin.setOnClickListener(listener);//Ϊʲô �д���
        
        btCancel = new Button(this);
        btCancel.setText("ȡ��");
        btCancel.setId(BUTTON_CANCEL);
        btCancel.setOnClickListener(listener);
        
        row3 = new LinearLayout(this);
        row3.addView(btLogin, params);
        row3.addView(btCancel, params);
        
        root = new LinearLayout(this);
        
        root.setOrientation(LinearLayout.VERTICAL);
        root.addView(row1);
        root.addView(row2);
        root.addView(row3);
        
        setContentView(root);
        
    }
    
    private boolean isEmpty(EditText et)
    {
    	if (et.getText() == null || "".equals(et.getText().toString().trim()))
    	{
    		return true;
    	}
    	return false;
    }
    
    class ButtonClickListener implements OnClickListener
    {
    	public void onClick(View v)
    	{
			// TODO Auto-generated method stub
			// String text = ((Button)v).getText().toString();
			// if("��¼".equals(text)){
			// //��¼��ť���¼�����
			// }else{
			// //ȡ����ť���¼�����
			// }
    		
    		switch (v.getId()) {//��ȡ ������� ���� id;
			case BUTTON_LOGIN:
				if (isEmpty(etName))
				{
					etName.setError("�û��� ���� Ϊ��");
					return ;
				}
				
				
				if (isEmpty(etPassword))
				{
					etPassword.setError("���� ���� Ϊ��");
					return ;
				}
				
				String name = etName.getText().toString();
				String password = etPassword.getText().toString();
				
				if ("wh".equals(name) && "123123".equals(password))
				{
					Toast.makeText(Zgj_day01_t1Activity.this, "��½�ɹ���", 3000).show();
				}
				else
				{
					Toast.makeText(Zgj_day01_t1Activity.this, "�û����������벻��ȷ", 3000).show();
					etPassword.setText("");
				}
						
				break;
			case BUTTON_CANCEL:
				Zgj_day01_t1Activity.this.finish();
				break;
			}
    		
    	}


    	
    }
    
    
    
}