package wh.zgj;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class App_use_fragmentActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this, R.xml.preference, true);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();
    }
}