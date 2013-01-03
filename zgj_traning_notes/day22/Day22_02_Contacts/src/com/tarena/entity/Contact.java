package com.tarena.entity;

import java.io.Serializable;

public class Contact implements Serializable {//这个类将 用 putExtra()发送，因此 需要实现 这个接口
	
	private int id;
	private String name;

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

}
