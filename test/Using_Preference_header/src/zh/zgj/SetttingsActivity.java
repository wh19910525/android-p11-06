package zh.zgj;

import java.util.List;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.widget.Toast;

public class SetttingsActivity extends PreferenceActivity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "zgj", 3000).show();
    }
	 @Override
	 public void onBuildHeaders(List<Header> target) {
		 
		  // TODO Auto-generated method stub
		  super.onBuildHeaders(target);
		  
		  loadHeadersFromResource(R.xml.preferenceheader,target);
		  Toast.makeText(this, "3.0 update", 2000).show();
	 }
}
