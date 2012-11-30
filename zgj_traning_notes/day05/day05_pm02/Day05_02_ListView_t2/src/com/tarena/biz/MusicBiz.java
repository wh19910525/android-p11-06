package com.tarena.biz;

import java.util.ArrayList;

import com.tarena.entity.Music;

public class MusicBiz {
	private ArrayList<Music> musics;

	public MusicBiz() {
		musics = new ArrayList<Music>();
		Music music = new Music();
		music.setId(1);
		music.setName("Ó²±Ò");
		music.setAlbum("Ğ¦×Å¿Ş");
		music.setSinger("Íô·å");
		music.setComposer("Íô·å");
		music.setDuration(809800);
		music.setMusicPath("/mnt/sdcard/musics/yingbi.mp3");
		musics.add(music);

		music = new Music();
		music.setId(2);
		music.setName("¸è³ª×æ¹ú");
		music.setAlbum("Ğ¦×Å¿Ş");
		music.setSinger("Íô·å");
		music.setComposer("Íô·å");
		music.setDuration(809800);
		music.setMusicPath("/mnt/sdcard/musics/gechangzuguo.mp3");
		musics.add(music);

		music = new Music();
		music.setId(3);
		music.setName("ÌğÃÛÃÛ");
		music.setAlbum("¾ıÉú½ñÊÀ");
		music.setSinger("µËÀö¾ı");
		music.setComposer("Ğ¡³æ");
		music.setDuration(709800);
		music.setMusicPath("/mnt/sdcard/musics/tianmimi.mp3");
		musics.add(music);

		music = new Music();
		music.setId(4);
		music.setName("ÃÀÑòÑòºÍ»ÒÌ«ÀÇ");
		music.setAlbum("ÃÀÑòÑòºÍ»ÒÌ«ÀÇµÄ¹ÊÊÂ");
		music.setSinger("Íô·å");
		music.setComposer("Íô·å");
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
