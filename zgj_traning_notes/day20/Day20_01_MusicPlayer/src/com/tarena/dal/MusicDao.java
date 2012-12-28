package com.tarena.dal;

import java.util.ArrayList;

import com.tarena.entity.Music;
import com.tarena.utils.GlobalUtils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore.Audio.Albums;
import android.provider.MediaStore.Audio.Media;

public class MusicDao {
	private ContentResolver cr;

	public MusicDao(Context context) {
		cr = context.getContentResolver();
	}

	private String getAlbumPath(String album_key) {
		String albumPath = null;
		Cursor c = cr.query(Albums.EXTERNAL_CONTENT_URI,
				new String[] { Albums.ALBUM_ART }, "album_key=?",
				new String[] { album_key }, null);
		if (c != null && c.moveToFirst()) {
			albumPath = c.getString(0);
			c.close();
		}
		return albumPath;
	}

	public ArrayList<Music> getMusics() {
		ArrayList<Music> musics = null;
		String[] projection = { "_id", "title", "album", "composer", "artist",
				"duration", "album_key", "_data" };
		Cursor c = cr.query(Media.EXTERNAL_CONTENT_URI, projection, null, null,
				null);
		if (c != null) {
			musics = new ArrayList<Music>();
			while (c.moveToNext()) {
				Music m = new Music();
				m.setId(c.getInt(c.getColumnIndex("_id")));
				m.setName(c.getString(c.getColumnIndex("title")));
				m.setAlbum(c.getString(c.getColumnIndex("album")));
				m.setComposer(c.getString(c.getColumnIndex("composer")));
				m.setArtist(c.getString(c.getColumnIndex("artist")));
				m.setPath(c.getString(c.getColumnIndex("_data")));
				m.setDuration(c.getLong(c.getColumnIndex("duration")));
				String album_key = c.getString(c.getColumnIndex("album_key"));
				m.setAlbumPath(getAlbumPath(album_key));

				musics.add(m);
			}
			c.close();
		}
		return musics;
	}
}
