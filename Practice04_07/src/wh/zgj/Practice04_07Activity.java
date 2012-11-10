package wh.zgj;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class Practice04_07Activity extends Activity {
	
	private TextView m_TextView;
	private CheckBox m_CheckBox1, m_CheckBox2, m_CheckBox3, m_CheckBox4;
	private Button m_Button;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        m_TextView = (TextView)findViewById(R.id.tv);
        
        m_Button = (Button)findViewById(R.id.bt);
        
        m_CheckBox1 = (CheckBox)findViewById(R.id.cb1);
        m_CheckBox2 = (CheckBox)findViewById(R.id.cb2);
        m_CheckBox3 = (CheckBox)findViewById(R.id.cb3);
        m_CheckBox4 = (CheckBox)findViewById(R.id.cb4);
        
        m_CheckBox1.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
        	
        	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
        		if(m_CheckBox1.isChecked()){
        			
        			displayToast("选择了" + m_CheckBox1.getText());
        		}
        	}
        });
        
        m_CheckBox2.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if(m_CheckBox2.isChecked())
				{
					displayToast("选择了" + m_CheckBox2.getText());					
				}			
			}
        });
        
        m_CheckBox3.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if(m_CheckBox3.isChecked())
				{
					displayToast("选择了" + m_CheckBox3.getText());
					
				}
			}
        	
        });
        
        m_CheckBox4.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if(m_CheckBox4.isChecked())
				{
					displayToast("选择了" + m_CheckBox4.getText());
				}
			}
        	
        });
     
        m_Button.setOnClickListener(new Button.OnClickListener(){

        	
			@Override
			public void onClick(View v) {
				
				int count = 0;
				
				// TODO Auto-generated method stub
				if (m_CheckBox1.isChecked())
				{
					count ++;
				}
				
				if (m_CheckBox2.isChecked())
				{
					count ++;
				}
				
				if (m_CheckBox3.isChecked())
				{
					count ++;
				}
				
				if (m_CheckBox4.isChecked())
				{
					count ++;
				}
				
				displayToast("一共 选择了"+ count + "项！");
				
			}
        	
        });
        
    }
    
    public void displayToast(String str){
    	Toast toast = Toast.makeText(this, str, Toast.LENGTH_LONG);
    	toast.setGravity(Gravity.TOP, 220, 0);
    	toast.show();
    }
    
}