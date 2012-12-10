package com.tarena.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

public class BitmapUtils {
	/**
	 * 按指定比例收缩加载指定路径的图片
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
	 * 根据指定尺寸保持纵横比 加载指定位置的图片
	 * @param path
	 * @param widht
	 * @param height
	 * @return
	 */
	public static Bitmap loadBitmap(String path, int widht, int height) {
		if (path != null && widht > 0 && height > 0) {
			Options opts = new Options();
			opts.inJustDecodeBounds = true;//加载边界信息
			BitmapFactory.decodeFile(path, opts);
			
			int x = opts.outWidth / widht;//opts.outWidth 获得所加载的边界信息的宽
			int y = opts.outHeight / height;//opts.outHeight 获得所加载的边界信息的高
			int scale = x > y ? x : y;
			opts.inJustDecodeBounds = false;
			opts.inSampleSize = scale;//设置收缩比例
			return BitmapFactory.decodeFile(path, opts);
		}
		return null;
	}
}
