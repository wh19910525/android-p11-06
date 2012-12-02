package com.tarena.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.tarena.utils.TimeFormatter;

public class Music implements Serializable {//ʵ�� Serializable �ӿڣ���Ҫ�� ���� putExtra ������� ���� Music�����
	private int id;
	private String name;
	private String album;
	private String singer;
	private String composer;
	private long duration;
	private String musicPath;

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

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getMusicPath() {
		return musicPath;
	}

	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}

	@Override
	public String toString() {//��д
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("������Ϣ���£�\n");
		sb.append("��ţ�").append(id).append('\n');
		sb.append("������").append(name).append('\n');
		sb.append("ר����").append(album).append('\n');
		sb.append("���֣�").append(singer).append('\n');
		sb.append("���ߣ�").append(composer).append('\n');
		sb.append("ʱ����").append(TimeFormatter.format(duration)).append('\n');
		sb.append("·����").append(musicPath).append('\n');
		return sb.toString();//
	}

	@Override
	public boolean equals(Object o) {//��д
		// TODO Auto-generated method stub
		return this.id == ((Music) o).getId();
	}
}
