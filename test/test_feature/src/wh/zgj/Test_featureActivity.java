package wh.zgj;

import android.app.Activity;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Test_featureActivity extends Activity {
	
	private TextView mTextView;
	private StringBuilder mStringBuilder;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mTextView = (TextView)findViewById(R.id.tv);
        mStringBuilder = new StringBuilder("以下是 这个 系统的feature：\n");
        PackageManager pm = getPackageManager();
        int count = 0;
        FeatureInfo[] features = pm.getSystemAvailableFeatures(); //得到所有支援的硬體種類
        try {
            for (FeatureInfo feature : features) 
            {
            	count ++;
            	Log.i("info:", feature.name);
            	
            	mStringBuilder.append(feature.name).append("\n");
            }
		} catch (Exception e) {
			mStringBuilder.append("这个 系统一共有").append(count).append(" 个feature \n");
			mTextView.setText(mStringBuilder.toString());
			// TODO: handle exception
		}finally{
//			  try {
//		            for (FeatureInfo feature : features) 
//		            {
//		            	count ++;
//		            	Log.i("info:", feature.name);
//		            	
//		            	mStringBuilder.append(feature.name).append("\n");
//		            }
//				} catch (Exception e) {
//					mStringBuilder.append("这个 系统一共有").append(count).append(" 个feature \n");
//					mTextView.setText(mStringBuilder.toString());
//					// TODO: handle exception
//				}
		}

        Log.i("info:", "========================");
      //  Log.i("info:", mStringBuilder.toString());
        Log.i("info:", "========================");
        
        
    }
}