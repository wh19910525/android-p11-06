package com.tarena.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtils {
	
	public static void save(InputStream in, String path) throws IOException {
		if (in != null && path != null) {
			File file = new File(path);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			FileOutputStream os = new FileOutputStream(file);
			BufferedOutputStream out = new BufferedOutputStream(os);
			BufferedInputStream is = new BufferedInputStream(in);
			int len = -1;
			byte[] buffer = new byte[1024];
			while ((len = is.read(buffer)) != -1) {
				out.write(buffer, 0, len);
				out.flush();
			}
			out.close();
			os.close();
			is.close();
			in.close();
		}
	}
}
