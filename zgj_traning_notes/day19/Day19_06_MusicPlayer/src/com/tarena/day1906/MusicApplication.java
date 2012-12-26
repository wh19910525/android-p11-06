package com.tarena.day1906;

import java.util.ArrayList;

import com.tarena.biz.MusicBiz;
import com.tarena.entity.Music;

import android.app.Application;

public class MusicApplication extends Application {
	private ArrayList<Music> playList;
	private int currentIndex;//定义当前索引

	public int getCurrentIndex() {//返回当前索引
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {//设置播放索引
		if (currentIndex >= 0 && currentIndex < playList.size())
			this.currentIndex = currentIndex;
		else
			this.currentIndex = -1;
	}

	public ArrayList<Music> getPlayList() {//返回歌曲列表
		return playList;
	}

	public Music getMusic(int position) {//根据索引查询歌曲
		if (position >= 0 && position < playList.size())
			return playList.get(position);
		return null;
	}

	public void addMusic(Music music) {//添加歌曲
		playList.add(music);
	}

	public void removeMusic(int position) {//从列表中删除
		if (position >= 0 && position < playList.size())
			playList.remove(position);
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		playList = new MusicBiz(this).getMusics();
		if (playList == null) {
			playList = new ArrayList<Music>();
		}
		this.setCurrentIndex(0);//默认初始下标为0,即从第一首开始播放
	}
}
