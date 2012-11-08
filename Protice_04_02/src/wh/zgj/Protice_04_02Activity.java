package wh.zgj;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class Protice_04_02Activity extends Activity {
    /** Called when the activity is first created. */

	private TextView textview;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        textview =(TextView)this.findViewById(R.id.m_textview);//
        /*
        textview.setTextColor(Color.RED);
        textview.setTextSize(20);
        textview.setBackgroundColor(Color.BLUE);
        String str = "TextView Ê¾Àý£¬»¶Ó­Ê¹ÓÃ";
        textview.setText(str);
        */
        
        
    }
    
    

}