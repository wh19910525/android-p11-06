package com.tarena.biz;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;

import com.tarena.dal.ImageDao;
import com.tarena.entity.ImageInfo;

public class ImageBiz {
	
	private ImageDao dao;

	public ImageBiz(Context context) {
		dao = new ImageDao(context);
	}

	public ArrayList<ImageInfo> getImageInfos() {
		return dao.getImageInfos();
	}

	public Bitmap getBitmap(int id) {
		return dao.getBitmap(id);
	}

	public Bitmap getThumbnail(int id) {
		return dao.getThumbnail(id);
	}
}
