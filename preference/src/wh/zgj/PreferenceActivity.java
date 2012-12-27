package wh.zgj;

import android.app.Activity;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceGroup;

public class PreferenceActivity extends android.preference.PreferenceActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        addPreferencesFromResource(R.xml.testpreference);
        
        Preference test = findPreference("ttts");
   //     ((PreferenceGroup)findPreference("thirdC")).removePreference(findPreference("ttts"));
        getPreferenceScreen().removePreference(findPreference("thirdC"));

       ((PreferenceGroup)findPreference("thirdC")).removeAll();

    }
}