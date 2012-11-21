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

	private ButtonClickListener listener;//����һ�� �ڲ���

	public void onClick(View v) {//���� OnClickListener �ӿ����һ�� ������
		// TODO Auto-generated method stub
		switch (v.getId()){
		case BUTTON_LOGIN:
			Toast.makeText(this, "�������˵�¼��ť", 3000).show();
			break;

		case BUTTON_CANCEL:
			Toast.makeText(this, "��������ȡ����ť", 3000).show();
			break;
		}
	}	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		// �������ֲ�������
		LayoutParams params = new LayoutParams(0, LayoutParams.WRAP_CONTENT,
				1.0f);

		// ������ť�ĵ����¼�����������
		listener = new ButtonClickListener();

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
		tvPassword.setText("��    ��");

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
		btnLogin.setText("��¼");
		btnLogin.setId(BUTTON_LOGIN);
		btnLogin.setOnClickListener(listener);//����һ������ʹ�� �ڲ��� ButtonClickListener ʵ�ּ���;
//		btnLogin.setOnClickListener(this);//������������ʹ�� OnClickListener �ӿ�  ʵ�ּ�����ʹ��һ���ӿ� ���� ֱ���� ������ ʵ������ӿڵ����з��������� ����� ����ʹ�ã�

		btnCancel = new Button(this);
		//btnCancel.setLayoutParams(params);
		btnCancel.setText("ȡ��");
		btnCancel.setId(BUTTON_CANCEL);
		// btnCancel.setOnClickListener(this);
		//btnCancel.setOnClickListener(new View.OnClickListener() {//������������ʹ�� ������ ʵ�ּ���
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
		
		// ������
		root = new LinearLayout(this);
		root.setOrientation(LinearLayout.VERTICAL);
		root.addView(row1);
		root.addView(row2);
		root.addView(row3);
		
		// ����ͼ����ӵ�Activity��������ʾ��
		setContentView(root);
    }
 
	private boolean isEmpty(EditText et) {
		if (et.getText() == null || "".equals(et.getText().toString().trim())) {
			return true;
		}
		return false;
	}

	class ButtonClickListener implements OnClickListener //��β��ܵ������������
	{
		public void onClick(View v) {
			// TODO Auto-generated method stub
			// String text = ((Button)v).getText().toString();
			// if("��¼".equals(text)){
			// //��¼��ť���¼�����
			// }else{
			// //ȡ����ť���¼�����
			// }

			switch (v.getId()) {
			case BUTTON_LOGIN:// ��¼
				// ������Ч����֤
				if (isEmpty(etName)) {
					etName.setError("�û�������Ϊ��");
					return;
				}
				if (isEmpty(etPassword)) {
					etPassword.setError("���벻��Ϊ��");
					return;
				}
				// ��ȡ�û�����
				String name = etName.getText().toString();
				String password = etPassword.getText().toString();
				// ��¼��֤
				if ("admin".equals(name) && "123456".equals(password)) {
					// ��¼�ɹ�
					Toast.makeText(Zgj_day01_01_t1Activity.this, "��¼�ɹ�", 3000).show();
				} else {
					// ��¼ʧ��
					Toast.makeText(Zgj_day01_01_t1Activity.this, "�û������������", 3000).show();
					etPassword.setText("");
				}
				break;
			case BUTTON_CANCEL:// ȡ��
				Zgj_day01_01_t1Activity.this.finish();
				break;
			}
		}
	}    
}