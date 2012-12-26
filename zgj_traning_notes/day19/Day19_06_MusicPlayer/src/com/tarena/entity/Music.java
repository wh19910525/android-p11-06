package com.tarena.entity;

public class Music {
	private int id;
	private String name;
	private String album;
	private String composer;
	private String artist;
	private String path;
	private String albumPath;
	private long duration;

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

	public String getComposer() {
		return composer;
	}

	public void setComposer(String composer) {
		this.composer = composer;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getAlbumPath() {
		return albumPath;
	}

	public void setAlbumPath(String albumPath) {
		this.albumPath = albumPath;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("������Ϣ��\n");
		sb.append("���:").append(id).append('\n');
		sb.append("����:").append(name).append('\n');
		sb.append("ר��:").append(album).append('\n');
		sb.append("����:").append(artist).append('\n');
		sb.append("����:").append(composer).append('\n');
		sb.append("ʱ��:").append(duration).append('\n');
		sb.append("·��:").append(path).append('\n');
		sb.append("ר��ͼƬ·��:").append(albumPath);
		// TODO Auto-generated method stub
		return sb.toString();
	}
}
