package com.tarena.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GlobalUtils {
	public static final String EXTRA_OP_TYPE = "opType";
	public static final String EXTRA_OP_DATA = "opData";

	public static final int OP_TYPE_ADD = 1;
	public static final int OP_TYPE_UPDATE = 2;

	public static String format(long time) {
		SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
		return formatter.format(new Date(time));
	}
}
