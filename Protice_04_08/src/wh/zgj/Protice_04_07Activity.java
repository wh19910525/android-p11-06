package wh.zgj;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Protice_04_07Activity extends Activity {
	
	private static final String[] m_Conutries = {"O��", "A��", "B��", "AB��", "����"};//��Ҫѡ��� ��Ŀ
	
	private TextView m_TextView;
	private Spinner m_Spinner;
	private ArrayAdapter<String> adapter;//���� ʲô��
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        m_TextView = (TextView)findViewById(R.id.tv);
        m_Spinner = (Spinner)findViewById(R.id.Spinner1);
        
        //����ѡ������ �� ArrayAdapter ����
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m_Conutries);
        //���������б�ķ��
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //�� adapter ��ӵ� m_Spinner ��
        m_Spinner.setAdapter(adapter);
        //��� Spinner�� �����¼�
        m_Spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				m_TextView.setText("Ѫ�ͣ�" + m_Conutries[arg2]);
				
				//���� ��ʾ ��ǰ ѡ��
				arg0.setVisibility(View.VISIBLE);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
    }
    
    
}