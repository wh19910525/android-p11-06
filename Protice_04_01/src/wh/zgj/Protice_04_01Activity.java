package wh.zgj;

import android.app.Activity;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Protice_04_01Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
       Button button_ok = (Button)findViewById(R.id.ok);
       button_ok.setOnClickListener(new Button.OnClickListener(){
    	   public void onClick(View v){
    		   DisplayToast("点击了Ok按钮");
    	   }
       });
    }
    
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
    	switch (keyCode)
    	{
    		case KeyEvent.KEYCODE_DPAD_CENTER:
    			DisplayToast("按下 中建");
    			break;
    		case KeyEvent.KEYCODE_DPAD_UP:
    			DisplayToast("按下 上方向键");
    			break;
    		case KeyEvent.KEYCODE_DPAD_DOWN:
    			DisplayToast("按下 下方向键");
    			break;
    		case KeyEvent.KEYCODE_DPAD_LEFT:
    			DisplayToast("按下 左方向键");
    			break;
    		case KeyEvent.KEYCODE_DPAD_RIGHT:
    			DisplayToast(" 按下 右方向键");
    			break;
    	}
    	return super.onKeyDown(keyCode, event);
    	
    }
    
    public boolean onKeyUp (int keyCode, KeyEvent event)
    {
    	switch (keyCode)
    	{
    		case KeyEvent.KEYCODE_DPAD_CENTER:
    			DisplayToast("弹起：中建");
    			break;
    		case KeyEvent.KEYCODE_DPAD_UP:
    			DisplayToast("弹起：上方向键");
    			break;
    		case KeyEvent.KEYCODE_DPAD_DOWN:
    			DisplayToast("弹起：下方向键");
    			break;
    		case KeyEvent.KEYCODE_DPAD_LEFT:
    			DisplayToast("弹起：左方向键");
    			break;
    		case KeyEvent.KEYCODE_DPAD_RIGHT:
    			DisplayToast("弹起：右方向键");
    			break;
    	
    	}
    	return super.onKeyUp(keyCode, event);
    	
    }
    
    
    public boolean onTouchEvent(MotionEvent event)
    {
    	int x = (int)event.getX();
    	int y = (int)event.getY();
    	
    	DisplayToast("触笔点击坐标：（"+Integer.toString(x)+","+Integer.toString(y)+"）");
    	return super.onTouchEvent(event);
    	
    }
    
    public void DisplayToast(String str){
    	Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    	
    }
    
}


