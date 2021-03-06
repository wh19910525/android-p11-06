package com.tarena.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Music implements Serializable{
	
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
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("音乐信息如下：\n");
		sb.append("编号：").append(id).append('\n');
		sb.append("歌名：").append(name).append('\n');
		sb.append("专辑：").append(album).append('\n');
		sb.append("歌手：").append(singer).append('\n');
		sb.append("作者：").append(composer).append('\n');
		sb.append("时长：").append(format(duration)).append('\n');
		sb.append("路径：").append(musicPath).append('\n');
		return super.toString();
	}
	
	public String format(long duration){//?
		SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
		return formatter.format(new Date(duration));
	}
	
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return this.id==((Music)o).getId();
	}
}
