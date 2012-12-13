package com.tarena.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

public class BitmapUtils {
	public static Bitmap loadBitmap(byte[] data, int width, int height) {
		Bitmap bm = null;
		Options opts = new Options();
		opts.inJustDecodeBounds = true;
		BitmapFactory.decodeByteArray(data, 0, data.length, opts);
		int x = opts.outWidth / width;
		int y = opts.outHeight / height;
		opts.inSampleSize = x > y ? x : y;
		opts.inJustDecodeBounds = false;
		bm = BitmapFactory.decodeByteArray(data, 0, data.length, opts);
		return bm;
	}
}
