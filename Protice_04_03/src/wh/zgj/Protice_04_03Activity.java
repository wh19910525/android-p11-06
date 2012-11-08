package wh.zgj;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.PhoneLookup;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class Protice_04_03Activity extends Activity {
	LinearLayout m_LinearLayout;
	ListView m_ListView;
	
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        
        m_LinearLayout = new LinearLayout(this);
        m_LinearLayout.setOrientation(LinearLayout.VERTICAL);
        m_LinearLayout.setBackgroundColor(android.graphics.Color.GREEN);
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        
        
        
        m_ListView = new ListView(this);
        m_ListView.setBackgroundColor(Color.BLACK);
        m_LinearLayout.addView(m_ListView, param);
        setContentView(m_LinearLayout);
        
        //
        
        Cursor cur = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        startManagingCursor(cur);
        ListAdapter adapter = new SimpleCursorAdapter(
        		this, 
        		android.R.layout.simple_list_item_2, 
        		cur, 
        		new String[] {PhoneLookup.DISPLAY_NAME, PhoneLookup.NUMBER}, 
        		new int[] {android.R.id.text1, android.R.id.text2} );
        
        m_ListView.setAdapter(adapter);
        m_ListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        	@Override
        	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3)
        	{
        		DisplayToast("滚动到第"+Long.toString(arg0.getSelectedItemId())+"项");
        		
        	}
        	@Override
        	public void onNothingSelected(AdapterView<?> arg0)
        	{
        		DisplayToast("没有选中");
        		
        	}
        	
		}) ;
        
		m_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
			{
				//于对选中的项进行处理
				DisplayToast("选中了第"+Integer.toString(arg2+1)+"项");
			}

		});
		
	}
    
    
    public void DisplayToast(String str)
    {
    	Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
    
    
    
}





