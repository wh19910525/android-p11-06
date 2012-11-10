package wh.zgh;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Protice_04_06Activity extends Activity {
	
	TextView m_TextView;
	RadioGroup m_RadioGroup;
	RadioButton m_RadioButton1,  m_RadioButton2, m_RadioButton3, m_RadioButton4;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    
    m_TextView = (TextView)findViewById(R.id.tv);
    m_RadioGroup = (RadioGroup)findViewById(R.id.rg);
    m_RadioButton1 = (RadioButton)findViewById(R.id.rb1);
    m_RadioButton2 = (RadioButton)findViewById(R.id.rb2);
    m_RadioButton3 = (RadioButton)findViewById(R.id.rb3);
    m_RadioButton4 = (RadioButton)findViewById(R.id.rb4);
    
    m_RadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()  {
		
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			if(checkedId == m_RadioButton1.getId())
			{
				displayToast("选中了：" + m_RadioButton1.getId() + "恭喜你，这是正确的答案！");
			}
			else
			{
				displayToast("错误！");
			}
		}
	});
    
    }
    
    public void displayToast(String str)
    {
    	Toast toast = Toast.makeText(this, str, Toast.LENGTH_LONG);
    	toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 220);
    	toast.show();
    	
    }
    
    
    
}