package com.tarena.biz;

import java.util.ArrayList;

import com.tarena.entity.Music;

public class MusicBiz {
	private ArrayList<Music> musics;

	public MusicBiz() {
		musics = new ArrayList<Music>();
		Music music = new Music();
		music.setId(1);
		music.setName("Ӳ��");
		music.setAlbum("Ц�ſ�");
		music.setSinger("����");
		music.setComposer("����");
		music.setDuration(809800);
		music.setMusicPath("/mnt/sdcard/musics/yingbi.mp3");
		musics.add(music);

		music = new Music();//ע�� ÿһ�� add�Ķ��� ���� Ҫ ���� ���� ʵ�������� ��ӵ���Ϣ ��ͬ;
		music.setId(2);
		music.setName("�質���");
		music.setAlbum("Ц�ſ�");
		music.setSinger("����");
		music.setComposer("����");
		music.setDuration(809800);
		music.setMusicPath("/mnt/sdcard/musics/gechangzuguo.mp3");
		musics.add(music);

		music = new Music();
		music.setId(3);
		music.setName("������");
		music.setAlbum("��������");
		music.setSinger("������");
		music.setComposer("С��");
		music.setDuration(709800);
		music.setMusicPath("/mnt/sdcard/musics/tianmimi.mp3");
		musics.add(music);

		music = new Music();
		music.setId(4);
		music.setName("������ͻ�̫��");
		music.setAlbum("������ͻ�̫�ǵĹ���");
		music.setSinger("����");
		music.setComposer("����");
		music.setDuration(839800);
		music.setMusicPath("/mnt/sdcard/musics/yingbi.mp3");
		musics.add(music);
	}

	public ArrayList<Music> getMusics() {
		return musics;
	}

	public void addMusic(Music music) {
		musics.add(music);
	}

	public void modifyMusic(Music music) {
		for (int i = 0; i < musics.size(); i++) {
			if (musics.get(i).equals(music)) {
				musics.set(i, music);
			}
		}
	}

	public void removeMusic(int position) {
		if (position >= 0 && position < musics.size())
			musics.remove(position);
	}

	public void removeMusic(Music music) {
		musics.remove(music);
	}
}
