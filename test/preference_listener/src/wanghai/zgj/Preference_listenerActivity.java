package wanghai.zgj;

import android.content.Intent;  
import android.content.SharedPreferences;  
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;  
import android.preference.CheckBoxPreference;  
import android.preference.EditTextPreference;  
import android.preference.ListPreference;  
import android.preference.Preference;  
import android.preference.PreferenceActivity;  
import android.preference.PreferenceManager;  
import android.preference.PreferenceScreen;  
import android.preference.Preference.OnPreferenceClickListener;  
import android.util.Log;  
  
public class Preference_listenerActivity extends PreferenceActivity implements  
        Preference.OnPreferenceClickListener, Preference.OnPreferenceChangeListener,
        OnSharedPreferenceChangeListener {  
	
    private static String TAG = "Preference_listenerActivity";            
    private CheckBoxPreference mapply_wifiPreference;       //��wifi  
    private CheckBoxPreference mapply_internetPreference;   //Internet����  
    private ListPreference depart_valuePreference;          //��������  
    private EditTextPreference number_editPreference;       //����绰����  
    private Preference mwifi_settingPreference;             //wifi����  
    private String oldDeptId; // �ɲ��ŵ�����  
  
    private static int count_wifi = 0;
    
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        addPreferencesFromResource(R.xml.mypeference);  
        //����keyֵ�ҵ��ؼ�  
        mapply_wifiPreference = (CheckBoxPreference) findPreference("apply_wifi");  
        mapply_internetPreference = (CheckBoxPreference) findPreference("apply_internet");  
        depart_valuePreference = (ListPreference) findPreference("depart_value");  
        number_editPreference = (EditTextPreference) findPreference("number_edit");  
        mwifi_settingPreference = (Preference) findPreference("wifi_setting");  
  
        // ���ü�����  
        mapply_internetPreference.setOnPreferenceClickListener(this);  
        mapply_internetPreference.setOnPreferenceChangeListener(this);  
        
        depart_valuePreference.setOnPreferenceClickListener(this);  
        depart_valuePreference.setOnPreferenceChangeListener(this);  
        
        number_editPreference.setOnPreferenceClickListener(this);  
        number_editPreference.setOnPreferenceChangeListener(this); 
        
        mapply_wifiPreference.setOnPreferenceClickListener(this);  
        mapply_wifiPreference.setOnPreferenceChangeListener(this); 
        
        mwifi_settingPreference.setOnPreferenceClickListener(this);  
  
        // �õ����ǵĴ洢Preferencesֵ�Ķ���Ȼ����������Ӧ����  
   //     SharedPreferences shp = PreferenceManager.getDefaultSharedPreferences(this);  
     //   boolean apply_wifiChecked = shp.getBoolean("apply_wifi", false);  
    }  
  
    // �Կؼ����е�һЩ����  
    private void operatePreference(Preference preference) {  
        if (preference == mapply_wifiPreference){                  //�����    "��wifi"  
            Log.i(TAG, " Wifi CB, and isCheckd ="+ mapply_wifiPreference.isChecked());  
        }else if (preference.getKey().equals("apply_internet")){   //�����"Internet����"  
            Log.i(TAG, " internet CB, and isCheckd = "+mapply_internetPreference.isChecked());  
        }else if (preference == depart_valuePreference){           //����� "��������"  
            Log.i(TAG, " department CB,and selectValue = "+ depart_valuePreference.getValue() + ", Text="+ depart_valuePreference.getEntry());  
        }else if (preference.getKey().equals("wifi_setting")) {    //�����"wifi����"  
            mwifi_settingPreference.setTitle("hit me " + (count_wifi++) + " ��");  
        }else if (preference == number_editPreference)             //�����"����绰����"  
            Log.i(TAG, "Old Value="+ number_editPreference.getText() + ", New Value="+ number_editPreference.getEditText().toString());  
    }  
    
    // ����¼�����  
    @Override  
    public boolean onPreferenceClick(Preference preference) {  
        // TODO Auto-generated method stub  
        Log.i(TAG, "onPreferenceClick----->"+String.valueOf(preference.getKey()));  
        // �Կؼ����в���  
        operatePreference(preference);  
        
        //����÷�������true�����ٵ���onPreferenceTreeClick���� 
        return false;
        //return true;
    }  
    
    //����¼�����  
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {  
        Log.i(TAG, "onPreferenceTreeClick----->"+preference.getKey());  
        // �Կؼ����в���  
        operatePreference(preference); 
        
        if (preference.getKey().equals("wifi_setting")) {  
            // ����һ���µ�Intent��  
            // �����������true�� ����ת�����Զ�����µ�Intent ;  
            // �����������false������ת��xml�ļ������õ�Intent ;  
            Intent i = new Intent(Preference_listenerActivity.this, Otheractivity.class);  //OtherActivityֻ��һ���򵥵�Activity  
            i.putExtra("type", "wifi");  
            startActivity(i);  
            return true;  
        } 
        
        return false;  
        
        //����ֵ��
        //true  �������¼��ѳɹ���׽������ִ��Ĭ�϶������߷����ϲ�������� ���磬����ת��Ĭ��Intent��
        //false ����ִ��Ĭ�϶������ҷ����ϲ�����������磬��ת��Ĭ��Intent
    }  
  
    // ��Preference��ֵ�����ı�ʱ�������¼���true������ֵ���¿ؼ���״̬��false��do noting  
    public boolean onPreferenceChange(Preference preference, Object objValue) {  
        Log.i(TAG, "onPreferenceChange----->"+String.valueOf(preference.getKey()));  
        if (preference == mapply_wifiPreference){  
            Log.i(TAG, "Wifi CB, and isCheckd = " + String.valueOf(objValue));  
        }else if (preference.getKey().equals("apply_internet")) {  
            Log.i(TAG, "internet CB, and isCheckd = "+ String.valueOf(objValue));  
            return false;  //���������ֵ  
        }else if (preference == depart_valuePreference){  
            Log.i(TAG, "  Old Value"+ depart_valuePreference.getValue()+" NewDeptName"+objValue);  
        }else if (preference.getKey().equals("wifi_setting")) {  
            Log.i(TAG, "change" + String.valueOf(objValue));  
            mwifi_settingPreference.setTitle("its turn me.");  //��������title  
        } else if (preference == number_editPreference) {  
            Log.i(TAG, "Old Value = " + String.valueOf(objValue));  
            return false; // ���������ֵ  
        }  
        return true;  //������º��ֵ  
    }  
    
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    	    Log.i(TAG, "onSharedPreferenceChanged----->"+ key);  
            Preference connectionPref = findPreference(key);
            // Ϊѡ�е�ֵ�����û�����ժҪ��
            Log.i(TAG, "key = " + "has changed!!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    
}  


