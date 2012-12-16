package com.tarena.entity;

import android.graphics.Bitmap;

public class Task {
	private String path;
	private Bitmap bitmap;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public Task(String path, Bitmap bitmap) {
		super();
		this.path = path;
		this.bitmap = bitmap;
	}

	public Task() {
	}

}