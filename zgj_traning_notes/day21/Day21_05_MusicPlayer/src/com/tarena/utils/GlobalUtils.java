package com.tarena.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GlobalUtils {
	public static final String EXTRA_PLAY_MODE = "playMode";
	public static final String EXTRA_CURRENT_MUSIC = "currentMusic";
	public static final String EXTRA_CURRENT_PROGRESS = "currentProgress";
	public static final String EXTRA_NEED_UPDATE = "needUpdate";
	public static final String EXTRA_PLAY_STATE = "playState";

	public static final int PLAY_MODE_LOOP = 1;
	public static final int PLAY_MODE_RANDOM = 2;
	
	public static final int OTHERS = 0;
	public static final int ISPLAYING = 1;
	public static final int ISPAUSE = 2;
	
	

	public static final String ACTION_PLAY = "com.tarena.action.PLAY";
	public static final String ACTION_PAUSE = "com.tarena.action.PAUSE";
	public static final String ACTION_PREVIOUS = "com.tarena.action.PREVIOUS";
	public static final String ACTION_NEXT = "com.tarena.action.NEXT";
	public static final String ACTION_PLAY_MODE_CHANGED = "com.tarena.action.PLAY_MODE_CHANGED";
	public static final String ACTION_SERVICE_STOP = "com.tarena.action.SERVICE_STOP";
	public static final String ACTION_UPDATE_STATE_CHANGED = "com.tarena.action.UPDATE_STATE_CHANGED";
	public static final String ACTION_SEEK_TO = "com.tarena.action.SEEK_TO";
	public static final String ACTION_REQUEST = "com.tarena.action.REQUEST";

	public static final String ACTION_CURRENT_MUSIC_CHANGED = "com.tarena.action.CURRENT_MUSIC_CHANGED";
	public static final String ACTION_UPDATE_PROGRESS = "com.tarena.action.UPDATE_PROGRESS";
	public static final String ACTION_RESPONSE = "com.tarena.action.RESPONSE";

	public static String format(long duration) {
		SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
		return formatter.format(new Date(duration));
	}
}
