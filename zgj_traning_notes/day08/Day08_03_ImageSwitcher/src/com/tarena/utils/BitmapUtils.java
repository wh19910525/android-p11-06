package com.tarena.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

public class BitmapUtils {
	public static Bitmap loadBitmap(String path, int scale) {
		if (path != null && scale > 0) {
			Options opts = new Options();
			opts.inSampleSize = scale;
			return BitmapFactory.decodeFile(path, opts);
		}
		return null;
	}

	public static Bitmap loadBitmap(String path, int width, int height) {
		if (path != null && width > 0 && height > 0) {
			Options opts = new Options();
			opts.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(path, opts);
			int x = opts.outWidth / width;//opts.outWidth ��������صı߽���Ϣ�Ŀ�
			int y = opts.outHeight / height;//opts.outHeight ��������صı߽���Ϣ�ĸ�
			opts.inJustDecodeBounds = false;
			opts.inSampleSize = x > y ? x : y;//������������
			return BitmapFactory.decodeFile(path, opts);
		}
		return null;
	}
}
