package com.tarena.entity;

public class AppInfo {


	private int iconRes;
	private String title;
	
	public AppInfo() {
	}

	public AppInfo(int iconRes, String title) {
		super();
		this.iconRes = iconRes;
		this.title = title;
	}

	public int getIconRes() {
		return iconRes;
	}

	public void setIconRes(int iconRes) {
		this.iconRes = iconRes;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
