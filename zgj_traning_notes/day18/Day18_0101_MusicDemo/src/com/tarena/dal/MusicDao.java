package com.tarena.dal;

import java.util.ArrayList;

import com.tarena.dal.MusicProviderInfo.MusicInfo;
import com.tarena.entity.Music;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

public class MusicDao {
	private ContentResolver cr;
	
	/**
	 * 当内容提供者提供数据库后,调用Context的getContentResolver()获得ContentResolver的对象，
	 * 只有执行ContentResolver的增删改查方法即可操作ContexProvider提供的数据库，
	 * 即想要操作ContexProvider提供的数据库，必须通过ContentResolver的对应方法操作。*/
	
	public MusicDao(Context context) {
		cr = context.getContentResolver();
	}

	public long addMusic(Music music) {
		
		long rowId = -1;
		
		//创建ContentValues对象,根据相应的数据库字段,添加值
		ContentValues values = new ContentValues();
		
		values.put("name", music.getName());
		values.put("album", music.getAlbum());
		values.put("singer", music.getSinger());
		values.put("author", music.getAuthor());
		values.put("composer", music.getComposer());
		values.put("duration", music.getDuration());
		values.put("album_path", music.getAlbumPath());
		values.put("music_path", music.getMusicPath());
		Uri uri = cr.insert(MusicInfo.CONTENT_URI, values);
		if (uri != null) {
			//根据ContentUris的parseId()方法自动获取uri后面的Id值
			rowId = ContentUris.parseId(uri);
			//uri.parse("");//此方法是将指定的字符串转换为Uri对象
		}
		return rowId;
	}

	public int modifyMusic(Music music) {
		int count = 0;
		ContentValues values = new ContentValues();
		values.put("name", music.getName());
		values.put("album", music.getAlbum());
		values.put("singer", music.getSinger());
		values.put("author", music.getAuthor());
		values.put("composer", music.getComposer());
		values.put("duration", music.getDuration());
		values.put("album_path", music.getAlbumPath());
		values.put("music_path", music.getMusicPath());
		count = cr.update(ContentUris.withAppendedId(MusicInfo.CONTENT_URI,
				music.getId()), values, null, null);
		return count;
	}

	public int removeMusic(int id) {
		int count = 0;
		count = cr.delete(
				ContentUris.withAppendedId(MusicInfo.CONTENT_URI, id), null,
				null);
		return count;
	}

	public ArrayList<Music> getMusics() {
		ArrayList<Music> musics = null;
		//Cursor 可对查询后返回数据进行处理
		Cursor c = cr.query(MusicInfo.CONTENT_URI, null, null, null, null);
		if (c != null) {
			musics = new ArrayList<Music>();
			while (c.moveToNext()) {
				Music m = new Music();
				//根据指定字段得到其下标,再根据下标得到指定的值
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
		return musics;
	}
}
