package com.tarena.day1201;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.tarena.utils.GlobalUtils;

public class SettingActivity extends Activity {
	
	private RadioGroup rgSortCol;
	private CheckBox chkAlbum, chkSinger, chkDuration;
	private SharedPreferences pref;

	private void setupView() {
		rgSortCol = (RadioGroup) findViewById(R.id.rgSortCol);
		chkAlbum = (CheckBox) findViewById(R.id.chkAlbum);
		chkSinger = (CheckBox) findViewById(R.id.chkSinger);
		chkDuration = (CheckBox) findViewById(R.id.chkDuration);

		int sort_col = pref.getInt(GlobalUtils.PREF_KEY_SORT, GlobalUtils.SORT_BY_NAME);
		switch (sort_col) {
		case GlobalUtils.SORT_BY_NAME:
			rgSortCol.check(R.id.rdoMusicName);
			break;
		case GlobalUtils.SORT_BY_ALBUM:
			rgSortCol.check(R.id.rdoAlbum);
			break;
		case GlobalUtils.SORT_BY_SINGER:
			rgSortCol.check(R.id.rdoSinger);
			break;
		case GlobalUtils.SORT_BY_DURATION:
			rgSortCol.check(R.id.rdoDuration);
			break;
		}

		chkAlbum.setChecked(pref.getBoolean(GlobalUtils.PREF_KEY_SHOW_ALBUM, true));
		chkSinger.setChecked(pref.getBoolean(GlobalUtils.PREF_KEY_SHOW_SINGER, true));
		chkDuration.setChecked(pref.getBoolean(GlobalUtils.PREF_KEY_SHOW_DURATION, true));
	}

	private void addListener() {
		rgSortCol.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				Editor editor = pref.edit();
				switch (checkedId) {
				case R.id.rdoMusicName:
					editor.putInt(GlobalUtils.PREF_KEY_SORT, GlobalUtils.SORT_BY_NAME).commit();
					break;
				case R.id.rdoAlbum:
					editor.putInt(GlobalUtils.PREF_KEY_SORT, GlobalUtils.SORT_BY_ALBUM).commit();
					break;
				case R.id.rdoSinger:
					editor.putInt(GlobalUtils.PREF_KEY_SORT, GlobalUtils.SORT_BY_SINGER).commit();
					break;
				case R.id.rdoDuration:
					editor.putInt(GlobalUtils.PREF_KEY_SORT, GlobalUtils.SORT_BY_DURATION).commit();
					break;
				}
			}
		});

		chkAlbum.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				Editor editor = pref.edit();
				editor.putBoolean(GlobalUtils.PREF_KEY_SHOW_ALBUM, isChecked).commit();
			}
		});
		
		chkSinger.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				Editor editor = pref.edit();
				editor.putBoolean(GlobalUtils.PREF_KEY_SHOW_SINGER, isChecked).commit();
			}
		});
		
		chkDuration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				Editor editor = pref.edit();
				editor.putBoolean(GlobalUtils.PREF_KEY_SHOW_DURATION, isChecked).commit();
			}
		});
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		pref = PreferenceManager.getDefaultSharedPreferences(this);
		setupView();
		addListener();

	}
}
