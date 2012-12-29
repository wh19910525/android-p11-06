package com.tarena.day2001;

import java.util.ArrayList;

import com.tarena.biz.MusicBiz;
import com.tarena.entity.Music;

import android.app.Application;

public class MusicApplication extends Application {
	
	private int currentPosition;// ��ǰ�������ֵ�����
	private ArrayList<Music> playList;// �����б�

	/**
	 * ��������������getter������
	 * 
	 * @return
	 */
	public int getCurrentPosition() {
		return currentPosition;
	}

	/**
	 * ��������������setter������
	 * 
	 * @return
	 */
	public void setCurrentPosition(int currentPosition) {//���ò�������
		if (currentPosition >= 0 && currentPosition < playList.size())
			this.currentPosition = currentPosition;
		else
			this.currentPosition = -1;
	}

	/**
	 * �����б��getter������
	 * 
	 * @return
	 */
	public ArrayList<Music> getPlayList() {
		return playList;
	}

	/**
	 * ����λ�û�ȡ�����б��е�һ��������Ϣ
	 * 
	 * @param position
	 * @return
	 */
	public Music getMusic(int position) {//����������ѯ����
		if (position >= 0 && position < playList.size())
			return playList.get(position);
		return null;
	}

	/**
	 * �򲥷��б���׷��������Ϣ
	 * 
	 * @param music
	 */
	public void addMusic(Music music) {
		playList.add(music);
	}

	/**
	 * �Ӳ����б����Ƴ�ָ��λ�õ�������Ϣ
	 * 
	 * @param position
	 */
	public void removeMusic(int position) {
		if (position >= 0 && position < playList.size())
			playList.remove(position);
	}

	/**
	 * ���ص�ǰ���ڲ��ŵ�������Ϣ
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
		this.setCurrentPosition(0);//Ĭ�ϳ�ʼ�±�Ϊ0,���ӵ�һ�׿�ʼ����
	}
}
