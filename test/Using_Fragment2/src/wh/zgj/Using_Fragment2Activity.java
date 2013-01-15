package wh.zgj;

import android.app.Activity;
import android.os.Bundle;


import android.os.Bundle;  
import android.view.Menu;  
import android.view.MenuItem;  
import android.app.Activity;  
import android.content.Intent;  
  
public class Using_Fragment2Activity extends Activity {  
      
    private static final int menu_setting = 1;  
  
    @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.main);  
    }  
      
    @Override  
    public boolean onCreateOptionsMenu(Menu menu) {  
        // TODO Auto-generated method stub  
        menu.add(0,menu_setting,1,"…Ë÷√").setIcon(android.R.drawable.ic_menu_preferences);  
        return super.onCreateOptionsMenu(menu);  
    }  
      
    @Override  
    public boolean onOptionsItemSelected(MenuItem item) {  
        // TODO Auto-generated method stub  
        super.onOptionsItemSelected(item);  
        Intent intent = new Intent(this, FragmentPreferences.class);  
        startActivity(intent);  
        return false;  
    }  
  
}  