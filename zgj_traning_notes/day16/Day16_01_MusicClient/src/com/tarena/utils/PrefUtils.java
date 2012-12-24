package com.tarena.utils;

import java.util.Comparator;

import com.tarena.entity.Music;
import com.tarena.entity.SortByAlbum;
import com.tarena.entity.SortByDuration;
import com.tarena.entity.SortByName;
import com.tarena.entity.SortBySinger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefUtils {
	
	private SharedPreferences pref;

	public PrefUtils(Context context) {
		pref = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public boolean showAlbum() {
		return pref.getBoolean(GlobalUtils.PREF_KEY_SHOW_ALBUM, true);
	}

	public boolean showSinger() {
		return pref.getBoolean(GlobalUtils.PREF_KEY_SHOW_SINGER, true);
	}

	public boolean showDuration() {
		return pref.getBoolean(GlobalUtils.PREF_KEY_SHOW_DURATION, true);
	}

	public Comparator<Music> getComparator() {
		
		Comparator<Music> comparator = null;
		switch (pref.getInt(GlobalUtils.PREF_KEY_SORT, GlobalUtils.SORT_BY_NAME)) {
		case GlobalUtils.SORT_BY_NAME:
			comparator = new SortByName();
			break;
		case GlobalUtils.SORT_BY_ALBUM:
			comparator = new SortByAlbum();
			break;
		case GlobalUtils.SORT_BY_SINGER:
			comparator = new SortBySinger();
			break;
		case GlobalUtils.SORT_BY_DURATION:
			comparator = new SortByDuration();
			break;
		}
		return comparator;
	}
}
