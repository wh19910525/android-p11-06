package com.tarena.dal;

import java.util.ArrayList;

import com.tarena.entity.Music;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MusicDao {
	
	private DBOpenHelper helper;

	public MusicDao(Context context) {
		helper = new DBOpenHelper(context, "music.db");
	}

	public long addMusic(Music music) {
		long rowId = -1;
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", music.getName());
		values.put("album", music.getAlbum());
		values.put("singer", music.getSinger());
		values.put("author", music.getAuthor());
		values.put("composer", music.getComposer());
		values.put("duration", music.getDuration());
		values.put("album_path", music.getAlbumPath());
		values.put("music_path", music.getMusicPath());
		rowId = db.insert("musictbl", null, values);
		db.close();
		return rowId;
	}

	public int modifyMusic(Music music) {
		int count = 0;
		SQLiteDatabase db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", music.getName());
		values.put("album", music.getAlbum());
		values.put("singer", music.getSinger());
		values.put("author", music.getAuthor());
		values.put("composer", music.getComposer());
		values.put("duration", music.getDuration());
		values.put("album_path", music.getAlbumPath());
		values.put("music_path", music.getMusicPath());
		count = db.update("musictbl", values, "_id=?", new String[] { ""
				+ music.getId() });
		db.close();
		return count;
	}

	public int removeMusic(int id) {
		int count = 0;
		SQLiteDatabase db = helper.getWritableDatabase();
		count = db.delete("musictbl", "_id=" + id, null);
		db.close();
		return count;
	}

	public ArrayList<Music> getMusics() {
		ArrayList<Music> musics = null;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db.rawQuery("select * from musictbl", null);
		if (c != null) {
			musics = new ArrayList<Music>();
			while (c.moveToNext()) {
				Music m = new Music();
				m.setId(c.getInt(c.getColumnIndex("_id")));
				m.setDuration(c.getInt(c.getColumnIndex("duration")));
				m.setName(c.getString(c.getColumnIndex("name")));
				m.setAlbum(c.getString(c.getColumnIndex("album")));
				m.setSinger(c.getString(c.getColumnIndex("singer")));
				m.setAuthor(c.getString(c.getColumnIndex("author")));
				m.setComposer(c.getString(c.getColumnIndex("composer")));
				m.setAlbumPath(c.getString(c.getColumnIndex("album_path")));
				m.setMusicPath(c.getString(c.getColumnIndex("music_path")));
				musics.add(m);
			}
			c.close();
		}
		db.close();
		return musics;
	}
}
