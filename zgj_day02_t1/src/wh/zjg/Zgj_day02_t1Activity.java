package wh.zjg;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class Zgj_day02_t1Activity extends Activity implements OnClickListener{
	
	private static final int BUTTON_CAL = 1;
	private static final int BUTTON_RESET = 2;
	
	private EditText etWeight, etHeight;
	private TextView tvWeitht, tvHeight;
	private Button btCal, btReset;
	private LinearLayout root, row1, row2, row3;
	
	private void setupView()
	{
		LayoutParams params = new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1.0f);
		
		//��һ��
		tvHeight = new TextView(this);
		tvHeight.setText("���");
		
		etHeight = new EditText(this);
		etHeight.setHint("������ ���");
		etHeight.setInputType(InputType.TYPE_CLASS_NUMBER);
		
		row1 = new LinearLayout(this);
		row1.addView(tvHeight);
		row1.addView(etHeight, params);
	
		//�ڶ���
		tvWeitht = new TextView(this);
		tvWeitht.setText("����");
		
		etWeight = new EditText(this);
		etWeight.setHint("������ ����");
		etWeight.setInputType(InputType.TYPE_CLASS_NUMBER);

		row2 = new LinearLayout(this);
		row2.addView(tvWeitht);
		row2.addView(etWeight, params);
		
		//������
		btCal = new Button(this);
		btCal.setText("����");
		btCal.setId(BUTTON_CAL);
		
		btReset = new Button(this);
		btReset.setText("����");
		btReset.setId(BUTTON_RESET);
	
		row3 = new LinearLayout(this);
		row3.addView(btCal, params);
		row3.addView(btReset, params);
	
		root = new LinearLayout(this);
		root.setOrientation(LinearLayout.VERTICAL);
		root.addView(row1);
		root.addView(row2);
		root.addView(row3);
		
		setContentView(root);
		
	}
	
	private void addListeners()
	{
		btCal.setOnClickListener(this);
		btReset.setOnClickListener(this);
	}
	
	private boolean isEmpty(EditText et) {
		if (et.getText() == null || "".equals(et.getText().toString().trim())) {
			return true;
		}
		return false;
	}
	
	public void onClick(View v)
	{
		switch (v.getId()) {
		case BUTTON_CAL:
			if(isEmpty(etHeight))
			{
				etHeight.setError("��� ���� Ϊ��");
				return;
			}
			
			if (isEmpty(etWeight)) {
				etWeight.setError("���ز���Ϊ��");
				return;
			}
			
			double h = Double.parseDouble(etHeight.getText().toString());
			double w = Double.parseDouble(etWeight.getText().toString());
			
			// ����
			double bmi = w * 10000 / h / h;
			
			if (bmi < 18) {
				Toast.makeText(this, "�������ƫ�ݣ����ǿӪ��", 3000).show();
			} else if (bmi < 25) {
				Toast.makeText(this, "������ı�׼�����������", 3000).show();
			} else if (bmi < 30) {
				Toast.makeText(this, "�������ƫ�֣����ǿ����", 3000).show();
			} else if (bmi < 35) {
				Toast.makeText(this, "���������ȷ��֣����ǿ����", 3000).show();
			} else if (bmi < 40) {
				Toast.makeText(this, "��������жȷ��֣����ǿ����", 3000).show();
			} else if (bmi < 45) {
				Toast.makeText(this, "����������ط��֣����ǿ����", 3000).show();
			} else {
				Toast.makeText(this, "������Ĺ����ˣ����ǿ����", 3000).show();
			}
			
			
		case BUTTON_RESET:
			etWeight.setText("");
			etHeight.setText("");
			
			break;
		}
		
	
	
	
	}
	
	
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        setupView();
        addListeners();
    }
}