package com.tarena.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

public class BitmapUtils {
	/**
	 * ��ָ��������������ָ��·����ͼƬ
	 * 
	 * @param path
	 * @param scale
	 * @return
	 */
	public static Bitmap loadBitmap(String path, int scale) {
		if (path != null && scale > 0) {
			Options opts = new Options();
			opts.inSampleSize = scale;
			return BitmapFactory.decodeFile(path, opts);
		}
		return null;
	}

	/**
	 * ����ָ���ߴ籣���ݺ�� ����ָ��λ�õ�ͼƬ
	 * @param path
	 * @param widht
	 * @param height
	 * @return
	 */
	public static Bitmap loadBitmap(String path, int widht, int height) {
		if (path != null && widht > 0 && height > 0) {
			Options opts = new Options();
			opts.inJustDecodeBounds = true;//���ر߽���Ϣ
			BitmapFactory.decodeFile(path, opts);
			
			int x = opts.outWidth / widht;//opts.outWidth ��������صı߽���Ϣ�Ŀ�
			int y = opts.outHeight / height;//opts.outHeight ��������صı߽���Ϣ�ĸ�
			int scale = x > y ? x : y;
			opts.inJustDecodeBounds = false;
			opts.inSampleSize = scale;//������������
			return BitmapFactory.decodeFile(path, opts);
		}
		return null;
	}
}
