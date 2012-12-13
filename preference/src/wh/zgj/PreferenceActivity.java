package wh.zgj;

import android.app.Activity;
import android.os.Bundle;

public class PreferenceActivity extends android.preference.PreferenceActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        addPreferencesFromResource(R.xml.testpreference);
    }
}