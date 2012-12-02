package com.tarena.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeFormatter {
	private static SimpleDateFormat formatter = new SimpleDateFormat("mmm:sss");
	public static String format(long duration) {
		return formatter.format(new Date(duration));
	}
}
