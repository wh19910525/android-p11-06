package com.tarena.day1906;

import java.util.ArrayList;

import com.tarena.biz.MusicBiz;
import com.tarena.entity.Music;

import android.app.Application;

public class MusicApplication extends Application {
	private ArrayList<Music> playList;
	private int currentIndex;//���嵱ǰ����

	public int getCurrentIndex() {//���ص�ǰ����
		return currentIndex;
	}

	public void setCurrentIndex(int currentIndex) {//���ò�������
		if (currentIndex >= 0 && currentIndex < playList.size())
			this.currentIndex = currentIndex;
		else
			this.currentIndex = -1;
	}

	public ArrayList<Music> getPlayList() {//���ظ����б�
		return playList;
	}

	public Music getMusic(int position) {//����������ѯ����
		if (position >= 0 && position < playList.size())
			return playList.get(position);
		return null;
	}

	public void addMusic(Music music) {//��Ӹ���
		playList.add(music);
	}

	public void removeMusic(int position) {//���б���ɾ��
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
		this.setCurrentIndex(0);//Ĭ�ϳ�ʼ�±�Ϊ0,���ӵ�һ�׿�ʼ����
	}
}
