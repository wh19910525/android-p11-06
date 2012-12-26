package com.tarena.biz;

import java.util.ArrayList;

import android.content.Context;

import com.tarena.dal.MusicDao;
import com.tarena.entity.Music;

public class MusicBiz {
	private MusicDao dao;

	public MusicBiz(Context context) {
		dao = new MusicDao(context);
	}

	public ArrayList<Music> getMusics() {
		return dao.getMusics();
	}

	public int delete(int id) {
		return dao.delete(id);
	}
}
