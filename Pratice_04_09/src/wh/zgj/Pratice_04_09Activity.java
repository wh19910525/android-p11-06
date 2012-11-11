package wh.zgj;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class Pratice_04_09Activity extends Activity {
	
	//���� ��ʾ�б�
	private static final String[] autostring = {"a2", "abf", "abe", "abcde", "abc2", "abcd3", "abcde2", "abc2", "abcd2", "abcde2"};
	
	private ArrayAdapter<String> adapter;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //�� ��ʾ���� �� ArrayAdapter ����
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autostring);
        
        AutoCompleteTextView m_AutoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.ac);
        
        //�� adapter ��ӵ� m_AutoCompleteTextView ��
        m_AutoCompleteTextView.setAdapter(adapter);
        
        MultiAutoCompleteTextView m_MultiAutoCompleteTextView = (MultiAutoCompleteTextView)findViewById(R.id.ma);
        
        //�� adapter ��ӵ�  m_MultiAutoCompleteTextView ��
        m_MultiAutoCompleteTextView.setAdapter(adapter);
        
        m_MultiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        
    }


}