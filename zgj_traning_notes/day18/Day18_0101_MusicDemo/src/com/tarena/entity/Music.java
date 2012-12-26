package com.tarena.entity;

import java.io.Serializable;

import com.tarena.utils.GlobalUtils;

public class Music implements Serializable {
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getAlbumPath() {
		return albumPath;
	}

	public void setAlbumPath(String albumPath) {
		this.albumPath = albumPath;
	}

	public String getMusicPath() {
		return musicPath;
	}

	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("������Ϣ");
		sb.append("��ţ�").append(id).append('\n');
		sb.append("������").append(name).append('\n');
		sb.append("ר����").append(album).append('\n');
		sb.append("���֣�").append(singer).append('\n');
		sb.append("���ʣ�").append(author).append('\n');
		sb.append("������").append(composer).append('\n');
		sb.append("ʱ����").append(GlobalUtils.format(duration)).append('\n');
		sb.append("ר��·����").append(albumPath).append('\n');
		sb.append("����·����").append(musicPath);
		// TODO Auto-generated method stub
		return sb.toString();
	}

	private String name;
	private String album;
	private String singer;
	private String composer;
	private String author;
	private int duration;
	private String albumPath;
	private String musicPath;

}
