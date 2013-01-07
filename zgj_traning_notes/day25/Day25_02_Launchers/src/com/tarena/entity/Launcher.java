package com.tarena.entity;

import android.content.Intent;
import android.graphics.drawable.Drawable;

public class Launcher {
	
	private String title;
	private Drawable icon;
	private Intent intent;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Drawable getIcon() {
		return icon;
	}
	
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
	
	public Intent getIntent() {
		return intent;
	}
	
	public void setIntent(Intent intent) {
		this.intent = intent;
	}
}
