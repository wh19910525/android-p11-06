package com.tarena.day2001;

import java.util.ArrayList;

import com.tarena.biz.MusicBiz;
import com.tarena.entity.Music;

import android.app.Application;

public class MusicApplication extends Application {
	
	private int currentPosition;// 当前播放音乐的索引
	private ArrayList<Music> playList;// 播放列表

	/**
	 * 播放音乐索引的getter访问器
	 * 
	 * @return
	 */
	public int getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * 播放音乐索引的setter访问器
	 * 
	 * @return
	 */
	public void setCurrentPosition(int currentPosition) {//设置播放索引
		if (currentPosition >= 0 && currentPosition < playList.size())
			this.currentPosition = currentPosition;
		else
			this.currentPosition = -1;
	}

	/**
	 * 播放列表的getter访问器
	 * 
	 * @return
	 */
	public ArrayList<Music> getPlayList() {
		return playList;
	}

	/**
	 * 根据位置获取播放列表中的一条音乐信息
	 * 
	 * @param position
	 * @return
	 */
	public Music getMusic(int position) {//根据索引查询歌曲
		if (position >= 0 && position < playList.size())
			return playList.get(position);
		return null;
	}

	/**
	 * 向播放列表中追加音乐信息
	 * 
	 * @param music
	 */
	public void addMusic(Music music) {
		playList.add(music);
	}

	/**
	 * 从播放列表中移除指定位置的音乐信息
	 * 
	 * @param position
	 */
	public void removeMusic(int position) {
		if (position >= 0 && position < playList.size())
			playList.remove(position);
	}

	/**
	 * 返回当前正在播放的音乐信息
	 * 
	 * @return
	 */
	public Music getCurrentMusic() {
		if (currentPosition >= 0 && currentPosition < playList.size())
			return playList.get(currentPosition);
		return null;
	}

	public int getCount() {
		return playList.size();
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();

		this.playList = new MusicBiz(this).getMusics();
		this.setCurrentPosition(0);//默认初始下标为0,即从第一首开始播放
	}
}
