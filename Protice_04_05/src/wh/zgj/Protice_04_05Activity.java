package wh.zgj;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Protice_04_05Activity extends Activity {
	
	private TextView m_TextView;
	private EditText m_EditText;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    m_TextView = (TextView)findViewById(R.id.tv);
    m_EditText = (EditText)findViewById(R.id.et);
    m_TextView.setTextSize(20);
    
//  m_EditText.setHint("请输入 账号");
    m_EditText.setOnKeyListener(new EditText.OnKeyListener() {
    	@Override
    	public boolean onKey(View arg0, int arg1, KeyEvent arg2)
    	{
    		m_TextView.setText("输入的内容是："+ m_EditText.getText().toString());
    		return false;
    	}
    });  
    }
}