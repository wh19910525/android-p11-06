package com.tarena.biz;

import java.io.File;
import java.util.ArrayList;

import com.tarena.entity.ImageInfo;
import com.tarena.utils.BitmapUtils;

public class ImageBiz {
	
	public ArrayList<ImageInfo> getImageInfos(String dir) {
		
		ArrayList<ImageInfo> images = null;
		if (dir != null) {
			File d = new File(dir);//����·���ҵ�·���µ��ļ���Ŀ¼
			if (d.exists() && d.isDirectory()) {//������ڸ�Ŀ¼(d.isDirectory�ж��Ƿ�ΪĿ¼)
				String[] fileNames = d.list();//����·���� �����ļ� �� ����,�� �ļ��� �ŵ��ַ���������
				if (fileNames != null) {//�����Ŀ¼�д����ļ�
					images = new ArrayList<ImageInfo>();
					for (String fileName : fileNames) {//���� ���� �ļ�
						ImageInfo img = new ImageInfo();
						img.setTitle(fileName);
						img.setThumbnail(BitmapUtils.loadBitmap(dir + fileName, 100, 100));

						images.add(img);
					}
				}
			}
		}
		return images;
	}
}
