package com.tarena.day1802;


import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Images.Thumbnails;
import android.util.Log;

public class Day18_02_MSActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		ContentResolver cr = getContentResolver();
//		 Cursor c = cr.query(Media.EXTERNAL_CONTENT_URI, null, null, null, null);//Media.EXTERNAL_CONTENT_URI
//		 if (c != null) {
//			 String[] cols = c.getColumnNames();
//			 while (c.moveToNext()) {
//				 for (String col : cols) {
//					 Log.i("info", col + ":" + c.getString(c.getColumnIndex(col)));
//				 }
//				 Log.i("info", "-----------------------------------------------------");
//				 String path = c.getString(c.getColumnIndex(Media.DATA));//为什么这里的Media.DATA 和下面的Media._ID 打印出来的不是同一张 图片
//				 Log.i("info", "patch-wh:" +path);
//				 Bitmap bm = BitmapFactory.decodeFile(path);
//				 Log.i("info", "bm=" + bm.toString());
//				  int id = c.getInt(c.getColumnIndex(Media._ID));
//				  try {
//					  bm = Media.getBitmap(cr, ContentUris.withAppendedId(Media.EXTERNAL_CONTENT_URI, id));//获得真图
//					  Log.i("info", "id=" + id);
//					  Log.i("info", "bitmap=" + bm.toString());
//				  } catch (FileNotFoundException e) {
//				  // TODO Auto-generated catch block
//					  e.printStackTrace();
//				  } catch (IOException e) {
//				  // TODO Auto-generated catch block
//					  e.printStackTrace();
//				  }
//				 Log.i("info", "+++++++++++++++++++++++++++++++++");
//			 }
//			 c.close();
//		 }
		
		/**Thumbnails.EXTERNAL_CONTENT_URI得到content://media/external/images/thumbnails
		 * 得到sdcard下图片的 缩略图数据库的路径
		 */
		Cursor c = cr.query(Thumbnails.EXTERNAL_CONTENT_URI, null, null, null, null);
		if (c != null) {
			String[] cols = c.getColumnNames();
			while (c.moveToNext()) {
				for (String col : cols) {
					Log.i("info", col + ":" + c.getString(c.getColumnIndex(col)));
				}

				// Bitmap thumb = Thumbnails.getThumbnail(cr,
				// c.getInt(c.getColumnIndex(Media._ID)),
				// Thumbnails.MINI_KIND, null);

				String path = c.getString(c.getColumnIndex(Thumbnails.DATA));
				Bitmap bm = BitmapFactory.decodeFile(path);
				if (bm == null)
				{
					Log.i("info", "===+++++++++============");
				}else{
					Log.i("info", "thumb=" + bm.toString());
				}
				
				Log.i("info", "===================================");
			}

			c.close();
		}

		// MediaStore.Images
		// Images.Media

		// Media.EXTERNAL_CONTENT_URI
		// Media.INTERNAL_CONTENT_URI
		// Media._ID
		// Media.DATA
		// Media.TITLE
		// Media.SIZE
		// Media.DISPLAY_NAME
		// Media.DATE_ADDED
		// Media.DATE_MODIFIED
		// Media.getBitmap(cr, url)

		// Images.Thumbnails
		// Thumbnails.EXTERNAL_CONTENT_URI
		// Thumbnails.INTERNAL_CONTENT_URI
		// Thumbnails._ID
		// Thumbnails.DATA
		// Thumbnails.IMAGE_ID
		// Thumbnails.KIND
		// Thumbnails.WIDTH
		// Thumbnails.HEIGHT
		// Thumbnails.getThumbnail(cr, origId, kind, options)
		// MediaStore.Video
		// MediaStore.Audio
	}
}