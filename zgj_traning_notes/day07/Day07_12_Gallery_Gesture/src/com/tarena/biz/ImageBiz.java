package com.tarena.biz;

import java.io.File;
import java.util.ArrayList;

import com.tarena.entity.ImageInfo;
import com.tarena.utils.BitmapUtils;

public class ImageBiz {
	public ArrayList<ImageInfo> getImages(String dir) {
		ArrayList<ImageInfo> images = null;
		if (dir != null) {
			File d = new File(dir);
			if (d.exists() && d.isDirectory()) {//如果存在该目录
				String[] fileNames = d.list();
				if (fileNames != null) {//如果该目录中存在文件
					images = new ArrayList<ImageInfo>();
					for (String fileName : fileNames) {
						ImageInfo img = new ImageInfo();
						img.setTitle(fileName);
						img.setThumbnail(BitmapUtils.loadBitmap(dir + fileName,
								100, 100));

						images.add(img);
					}
				}
			}
		}
		return images;
	}
}
