package wh.zgj;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;

public class Pratice_04_09Activity extends Activity {
	
	//设置 提示列表
	private static final String[] autostring = {"a2", "abf", "abe", "abcde", "abc2", "abcd3", "abcde2", "abc2", "abcd2", "abcde2"};
	
	private ArrayAdapter<String> adapter;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //将 提示内容 与 ArrayAdapter 连接
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, autostring);
        
        AutoCompleteTextView m_AutoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.ac);
        
        //将 adapter 添加到 m_AutoCompleteTextView 中
        m_AutoCompleteTextView.setAdapter(adapter);
        
        MultiAutoCompleteTextView m_MultiAutoCompleteTextView = (MultiAutoCompleteTextView)findViewById(R.id.ma);
        
        //将 adapter 添加到  m_MultiAutoCompleteTextView 中
        m_MultiAutoCompleteTextView.setAdapter(adapter);
        
        m_MultiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        
    }


}