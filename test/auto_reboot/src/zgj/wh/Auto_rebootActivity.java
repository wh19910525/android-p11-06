package zgj.wh;

import zgj.wh.MyReceiver;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Auto_rebootActivity extends Activity {
	
	private Button mButton_reset;
	private Button mButton_auto;
	
	private TextView mTextView_cishu;
	private TextView mTextView_shijian;
	private TextView mTextView_display;
	
	private EditText mEditText_cishu;
	private EditText mEditText_shijian;
	
	private MyReceiver receiver;
	
	private int E_chishu = 0;
	private int E_shijian = 0;
	private int reboot_count = 0;
	private int hava_reboot_chishu = 1;
	private int remain_reboot_chishu = 0;
	private int remain_reboot_shijian = 0;
	
	private Handler mHandler;
	private MyThread myThread;
	private StringBuilder display;
	private boolean flag_auto = true;
	private SharedPreferences pref;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mButton_reset = (Button)findViewById(R.id.B_reset);
        mButton_auto = (Button)findViewById(R.id.B_auto);
        
        mTextView_cishu = (TextView)findViewById(R.id.T_Reset_cishu);
        mTextView_shijian = (TextView)findViewById(R.id.T_after_time);
        mTextView_display = (TextView)findViewById(R.id.diplay_context);
        
        mEditText_cishu = (EditText)findViewById(R.id.E_Reset_cishu);
        mEditText_shijian = (EditText)findViewById(R.id.E_after_time);
        
        mHandler = new Handler(){
        	@Override
        	public void handleMessage(Message msg) {
				 switch (msg.what) {
				 case 0:// 开始下载
					 mTextView_display.setText(msg.obj.toString());
					 Log.i("wanghai", "case 1");
					 break;
				 }        		
        	}
        };
        
        // 获取Preferences对象
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        
        // 获取Editor
        Editor editor = pref.edit();
        
        // 获取 偏好存储的数据
        hava_reboot_chishu = pref.getInt("reboot_count", 1);
        E_shijian = pref.getInt("set_shijian", 60);
        E_chishu = pref.getInt("set_chishu", 5000);
        
		// 编辑偏好设置
		editor.putInt("reboot_count", hava_reboot_chishu + 1).commit();
		
//		editor.remove("reboot_count").putInt("reboot_count", hava_reboot_chishu + 1).commit();

        remain_reboot_chishu = Integer.parseInt(mEditText_cishu.getText().toString()) - hava_reboot_chishu;
        if (remain_reboot_chishu < 0){
        	remain_reboot_chishu = hava_reboot_chishu + 1;
        }
        

        	
    	mEditText_shijian.setText(E_shijian + "");
    	mEditText_cishu.setText(E_chishu + "");
    	
        if (flag_auto == true)
        {
        	mButton_auto.setText("取消自动重启");
        	mEditText_cishu.setEnabled(false);
        	mEditText_shijian.setEnabled(false);
        	
        }
  
        myThread = new MyThread("wait_Thread");               
        myThread.start();
   
    }
    
    class MyThread extends Thread {
    	
        private boolean stop = false; 
        
        public MyThread(String threadName) { 
            super(threadName); 
        } 
    	 
        public void setStop() { 
            this.stop = true; 
        } 
        
    	  public void run(){
    		  	  
    		  for (int i = 0;i < E_shijian; i ++){
    			  
    			  if(stop){
    				  break; 
    			  }
	    	        	remain_reboot_shijian = E_shijian - i;
	    	            display = new StringBuilder(getString(R.string.text1)).append(hava_reboot_chishu).
	    	            		append(getString(R.string.text2)).append(remain_reboot_chishu).append(getString(R.string.text3)).
	    	            		append("\n").append(getString(R.string.text4 )).append(remain_reboot_shijian).
	    	            		append(getString(R.string.text5));

	    	      try {
	    	    	      Message msg = Message.obtain(mHandler);
	    	    	      msg.what = 0;
	    	    	      msg.obj = display;
						 
	    	    	      mHandler.sendMessage(msg);
	    	    	  
	    	    	      sleep(1000);                            //直接调用
	    	      } catch (InterruptedException e) {
	    	    	  Log.i("wanghai", "sheep");
	    	      }
    		  }
    		  
    		  //如果 手动取消 自动重启,就 不执行 重启 
    		  if(stop == false)
    		  {
	    		    PowerManager pm = (PowerManager)getSystemService(POWER_SERVICE);  
	    		    pm.reboot("null");  
	    		    Log.v("wanghai", "reboot");
    		  }
    	  }
    }
    
    public void doClick(View v)
    {
    	Log.i("wanghai", "doClick");
    	Log.i("wanghai", "Thread.currentThread == " +Thread.currentThread().getName());
    	
    	if(v.getId() == R.id.B_reset){
    		Log.i("wanghai", "reset_key");
    		mEditText_cishu.setText("5000");
    		mEditText_shijian.setText("60");
    		
            // 获取Editor
            Editor editor = pref.edit();
    		// 编辑偏好设置
    		editor.putInt("set_shijian", 60).commit();
    		editor.putInt("set_chishu", 5000).commit();
    		editor.putInt("reboot_count", 1).commit();
    		
    	}else if (v.getId() == R.id.B_auto){
    		Log.i("wanghai", "auto_key");
    		E_chishu = Integer.parseInt(mEditText_cishu.getText().toString());
    		E_shijian = Integer.parseInt(mEditText_shijian.getText().toString());
    		if(flag_auto == true){
    			flag_auto = false;
            	mButton_auto.setText("设置自动重启");
            	mButton_reset.setEnabled(true);
            	mEditText_cishu.setEnabled(true);
            	mEditText_shijian.setEnabled(true);

            	//中断 线程
            	myThread.setStop();
    		}else{
    			flag_auto = true;
            	mButton_auto.setText("取消自动重启");
            	mButton_reset.setEnabled(false);
            	mEditText_cishu.setEnabled(false);
            	mEditText_shijian.setEnabled(false);

            	E_shijian = Integer.parseInt(mEditText_shijian.getText().toString());
            	E_chishu = Integer.parseInt(mEditText_cishu.getText().toString());
            	
                // 获取Editor
                Editor editor = pref.edit();
        		// 编辑偏好设置
        		editor.putInt("set_shijian", E_shijian).commit();
        		editor.putInt("set_chishu", E_chishu).commit();
        		
            	//执行  新线程，被 终止的 线程 在 重新 运行是 必须 重新 创建
            	myThread = new MyThread("wait_Thread");
                myThread.start();
    		}
    	}
    	
    }
}

