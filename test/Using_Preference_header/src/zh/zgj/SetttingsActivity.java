package zh.zgj;

import java.util.List;

import android.preference.PreferenceActivity;

public class SetttingsActivity extends PreferenceActivity {

	 @Override
	 public void onBuildHeaders(List<Header> target) {
	  // TODO Auto-generated method stub
	  super.onBuildHeaders(target);
	  loadHeadersFromResource(R.xml.preferenceheader,target);
	 }
}
