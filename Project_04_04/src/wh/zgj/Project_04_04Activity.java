package wh.zgj;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Project_04_04Activity extends Activity {
	
    private TextView m_textview;//�����ƶ��� ������ ����һ��
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
       // m_textview = new TextView(this);
        m_textview = (TextView)this.findViewById(R.id.tv);
        
        
        
    }
    

}