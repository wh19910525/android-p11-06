package wh.zgj;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Project_04_04Activity extends Activity {
	
    private TextView m_textview;//�����ƶ��� ������ ����һ��
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
       // m_textview = new TextView(this);
        m_textview = (TextView)this.findViewById(R.id.tv);
        String string = "Toastʾ�������յ�����ʱ�����ǻ���ʾ��ӭʹ�ã�";
        m_textview.setText(string);
        m_textview.setTextSize(35);
        Button button = (Button)findViewById(R.id.bv);
        button.setOnClickListener(new Button.OnClickListener(){
        	public void onClick(View v)
        	{
        		DisplayToast("����������������ʾ");
        	}
        });
    }
    
    public void DisplayToast(String str)
    {
    	Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    	
    }
}

