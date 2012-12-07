package com.tarena.biz;

import java.io.File;
import java.util.ArrayList;

import com.tarena.entity.ImageInfo;
import com.tarena.utils.BitmapUtils;

public class ImageBiz {
	public ArrayList<ImageInfo> getImages(String dir) {
		ArrayList<ImageInfo> images = null;
		if (dir != null) {
			File d = new File(dir);//根据路径找到路径下的文件或目录
			if (d.exists() && d.isDirectory()) {//如果存在该目录(d.isDirectory判断是否为目录)
				String[] fileNames = d.list();//将该路径下的所有目录列举出来,放到字符串数组中
				if (fileNames != null) {//如果该目录中存在文件
					images = new ArrayList<ImageInfo>();
					for (String fileName : fileNames) {//遍历所有目录
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
