package wh.zgj;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Protice_04_07Activity extends Activity {
	
	private static final String[] m_Conutries = {"O型", "A型", "B型", "AB型", "其他"};//将要选择的 项目
	
	private TextView m_TextView;
	private Spinner m_Spinner;
	private ArrayAdapter<String> adapter;//这是 什么？
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        m_TextView = (TextView)findViewById(R.id.tv);
        m_Spinner = (Spinner)findViewById(R.id.Spinner1);
        
        //将可选的内容 与 ArrayAdapter 连接
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, m_Conutries);
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //将 adapter 添加到 m_Spinner 中
        m_Spinner.setAdapter(adapter);
        //添加 Spinner的 监听事件
        m_Spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				m_TextView.setText("血型：" + m_Conutries[arg2]);
				
				//设置 显示 当前 选项
				arg0.setVisibility(View.VISIBLE);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
    }
    
    
}