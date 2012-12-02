package com.tarena.biz;

import java.util.ArrayList;

import com.tarena.entity.Music;

public class MusicBiz {
	private ArrayList<Music> musics;

	public MusicBiz() {
		musics = new ArrayList<Music>();
		Music music = new Music();
		music.setId(1);
		music.setName("硬币");
		music.setAlbum("笑着哭");
		music.setSinger("汪峰");
		music.setComposer("汪峰");
		music.setDuration(809800);
		music.setMusicPath("/mnt/sdcard/musics/yingbi.mp3");
		musics.add(music);

		music = new Music();//注意 每一个 add的对象 必须 要 重新 开辟 实例，否则 添加的信息 相同;
		music.setId(2);
		music.setName("歌唱祖国");
		music.setAlbum("笑着哭");
		music.setSinger("汪峰");
		music.setComposer("汪峰");
		music.setDuration(809800);
		music.setMusicPath("/mnt/sdcard/musics/gechangzuguo.mp3");
		musics.add(music);

		music = new Music();
		music.setId(3);
		music.setName("甜蜜蜜");
		music.setAlbum("君生今世");
		music.setSinger("邓丽君");
		music.setComposer("小虫");
		music.setDuration(709800);
		music.setMusicPath("/mnt/sdcard/musics/tianmimi.mp3");
		musics.add(music);

		music = new Music();
		music.setId(4);
		music.setName("美羊羊和灰太狼");
		music.setAlbum("美羊羊和灰太狼的故事");
		music.setSinger("汪峰");
		music.setComposer("汪峰");
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
