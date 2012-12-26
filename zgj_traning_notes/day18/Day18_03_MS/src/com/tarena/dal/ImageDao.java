package com.tarena.dal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Images.Thumbnails;

import com.tarena.entity.ImageInfo;

public class ImageDao {
	
	private ContentResolver cr;

	public ImageDao(Context context) {
		
		cr = context.getContentResolver();
	}

	public ArrayList<ImageInfo> getImageInfos() {
		
		ArrayList<ImageInfo> images = null;
		//Media.EXTERNAL_CONTENT_URI指向：content://media/external/images/media
		Cursor c = cr.query(Media.EXTERNAL_CONTENT_URI, //这是在哪里 定义的;
				new String[] {Media._ID, Media.TITLE }, 
				null, null, null);
		if (c != null) {
			images = new ArrayList<ImageInfo>();
			while (c.moveToNext()) {
				ImageInfo img = new ImageInfo();
				img.setId(c.getInt(c.getColumnIndex(Media._ID)));
				img.setTitle(c.getString(c.getColumnIndex(Media.TITLE)));
				images.add(img);
			}
			c.close();
		}
		return images;
	}

	public Bitmap getBitmap(int id) {//获得真图
		try {
			return Media.getBitmap(cr, ContentUris.withAppendedId(Media.EXTERNAL_CONTENT_URI, id));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Bitmap getThumbnail(int id) {//获得缩略图
		return Thumbnails.getThumbnail(cr, id, Thumbnails.MICRO_KIND, null);
	}
}
