package wh.zgj;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class Zgj_day02_t2Activity extends Activity implements OnClickListener{
	
	private LayoutInflater inflater;
	
	private EditText etWeight, etHeight;
	private Button btCal, btReset;
	private View root;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        inflater = LayoutInflater.from(this);
        setupView();
        addListeners();
    }
    
	private void setupView()
	{
		root = inflater.inflate(R.layout.main, null);
		
		//��һ��		
		etHeight = (EditText)root.findViewById(R.id.etHeight);
			
		//�ڶ���		
		etWeight = (EditText)root.findViewById(R.id.etWeight);
		etWeight.setInputType(InputType.TYPE_CLASS_NUMBER);
		
		//������
		btCal = (Button)root.findViewById(R.id.btCal);	
		btReset = (Button)root.findViewById(R.id.btReset);
		
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
		case R.id.btCal:
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
						
		case R.id.btReset:
			etWeight.setText("");
			etHeight.setText("");
			break;
		}
	}
    
}