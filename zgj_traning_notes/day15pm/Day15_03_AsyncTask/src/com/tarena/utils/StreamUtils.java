package com.tarena.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtils {
	public static void save(InputStream is,String path){
		if(is!=null && path!=null){
			try {
				File file = new File(path);
				if(!file.getParentFile().exists()){
					file.getParentFile().mkdirs();
				}
				
				FileOutputStream os = new FileOutputStream(file);
				BufferedOutputStream out = new BufferedOutputStream(os);
				BufferedInputStream in = new BufferedInputStream(is);
				int len = 0;
				byte[] buffer = new byte[1024*4];
				while((len = in.read(buffer))!=-1){
					out.write(buffer, 0, len);
					out.flush();
				}
				out.close();
				os.close();
				in.close();
				is.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
