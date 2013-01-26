package wh.zgj;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;

public class Preference_dependency_disableDependentsStateActivity extends PreferenceActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean test1 = true;
        boolean test2 = false;
        
        Log.i("WangHai","" + (test1 && test2));

        addPreferencesFromResource(R.xml.config);
    }
}