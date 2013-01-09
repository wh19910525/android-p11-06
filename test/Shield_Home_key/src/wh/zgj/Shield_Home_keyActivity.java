package wh.zgj;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Shield_Home_keyActivity extends Activity {
	private Button mButton_sheild;
	private Button mButton_recovery;
	private TextView mTextView;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mButton_sheild = (Button)findViewById(R.id.Bt_recovery);
        mButton_sheild = (Button)findViewById(R.id.Bt_Sheild);
        mTextView = (TextView)findViewById(R.id.mTv);
        
    }
    
    public void onAttachedToWindow() {
        this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD);
        super.onAttachedToWindow();
    }
    
    public void doClick (View v)
    {
    	if(v.getId()== R.id.Bt_Sheild)
    	{
    		mTextView.setText("�Ѿ�����home����");
    		
    		onAttachedToWindow();
    		Toast.makeText(this, "�Ѿ�����home����1", 3000).show();
    		
    	}
    	if(v.getId() == R.id.Bt_recovery)
    	{
    		mTextView.setText("�Ѿ��ָ�home����");
    		Toast.makeText(this, "�Ѿ��ָ�home����", 3000).show();
    	}
    	
    }
}