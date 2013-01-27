package zh.zgj;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment{

	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		  // TODO Auto-generated method stub
		  super.onCreate(savedInstanceState);
		  String flag = getArguments().getString("someKey");
		  if("wh".equals(flag)){
			  addPreferencesFromResource(R.xml.test_preferences);
		  }else if("zgj".equals(flag)){
			  addPreferencesFromResource(R.xml.preferences);
		  }
		  
	 }

}