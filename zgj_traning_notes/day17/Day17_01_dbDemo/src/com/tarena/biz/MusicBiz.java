package com.tarena.biz;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.tarena.dal.MusicDao;
import com.tarena.entity.Music;

public class MusicBiz {
	private MusicDao dao;

	public MusicBiz(Context context) {
		dao = new MusicDao(context);
	}

	public long addMusic(Music music) {
		return dao.addMusic(music);
	}

	public int modifyMusic(Music music) {
		return dao.modifyMusic(music);
	}

	public int removeMusic(int id) {
		return dao.removeMusic(id);
	}

	public ArrayList<Music> getMusics() {
		return dao.getMusics();
	}
}
