package com.tarena.day1902;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore.Audio.Albums;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;

public class Day19_02_MSActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// MediaStore.Audio.Media
		// Media.EXTERNAL_CONTENT_URI
		// Media.INTERNAL_CONTENT_URI
		// Media._ID
		// Media.DATA
		// Media.TITLE
		// Media.DURATION
		// Media.COMPOSER
		// Media.ARTIST
		// Media.ALBUM
		// Media.ALBUM_KEY

		// MediaStore.Audio.Albums
		// Albums.EXTERNAL_CONTENT_URI
		// Albums.INTERNAL_CONTENT_URI
		// Albums._ID
		// Albums.ALBUM
		// Albums.ALBUM_KEY
		// Albums.ALBUM_ART

		String[] projection = { "_id", "title", "composer", "artist", "album",
				"duration", "album_key", "_data" };
		ContentResolver cr = getContentResolver();
		//Ϊʲô ���� ͬ�� Media.EXTERNAL_CONTENT_URI ���ܹ����ʵ� ���֣��� 18_03 �ܹ����ʵ� ͼƬ
		Cursor c = cr.query(Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
		Log.i("info", "wanghai" + Media.EXTERNAL_CONTENT_URI);
 		if (c != null) {
			while (c.moveToNext()) {
				Log.i("info", "��ţ�" + c.getInt(c.getColumnIndex("_id")));
				Log.i("info", "������" + c.getString(c.getColumnIndex("title")));
				Log.i("info", "������" + c.getString(c.getColumnIndex("composer")));
				Log.i("info", "���֣�" + c.getString(c.getColumnIndex("artist")));
				Log.i("info", "ר����" + c.getString(c.getColumnIndex("album")));
				Log.i("info", "ʱ����" + format(c.getLong(c.getColumnIndex("duration"))));
				Log.i("info", "·����" + c.getString(c.getColumnIndex("_data")));

				String album_key = c.getString(c.getColumnIndex("album_key"));
				Cursor c1 = cr.query(Albums.EXTERNAL_CONTENT_URI,
						new String[] { "album_art" }, "album_key=?",
						new String[] { album_key }, null);
				if (c1 != null && c1.moveToFirst()) {
					String album_art = c1.getString(0);
					Log.i("info", "album_art:" + album_art);
					Bitmap bm = BitmapFactory.decodeFile(album_art);
					Log.i("info", "sר��ͼƬ��" + bm);
					c1.close();
				}
			}
			c.close();
		}
	}

	private static String format(long duration) {
		SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
		return formatter.format(new Date(duration));
	}
}