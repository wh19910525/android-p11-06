package com.tarena.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GlobalUtils {
	public static final int PLAY_MODE_LOOP = 1;//����ѭ������id
	public static final int PLAY_MODE_RANDOM = 2;//�����������id
	
	public static String format(long duration) {
		SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
		return formatter.format(new Date(duration));
	}
}
