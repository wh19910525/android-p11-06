package com.tarena.day1504;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class Day15_04_IOActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// File file = getFilesDir();
		// File file = getCacheDir();
		// File file = getDir("abc", MODE_PRIVATE);
		// File file = getDatabasePath("user.db");
		// File file = getExternalCacheDir();
		// File file = getExternalFilesDir(Environment.DIRECTORY_DCIM);
		
		// File file = Environment.getDataDirectory();
		// File file = Environment.getDownloadCacheDirectory();
		// File file = Environment.getRootDirectory();
		// File file = Environment.getExternalStorageDirectory();

		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			Log.i("info", "file=" + file);
		}

		File ttt = new File("/sdcard/image/", "2.jpg/");
		if (ttt.isFile())
			Log.i("wh", "这是一个文件！");
		else
			Toast.makeText(this, "这不是一个文件！", 3000).show();
		if (ttt.isDirectory())
			Log.i("wh", "这是一个目录！");
		else
			Toast.makeText(this, "这不是一个目录！", 3000).show();
		
		String wanghai = ttt.getParent();
		Log.i("wh", wanghai);
		String zgj = ttt.getParentFile().toString();
		Log.i("zgj", zgj);
		// File root = new File("/");
		// String[] fileNames = root.list();
		// for (String fileName : fileNames) {
		// Log.i("info", fileName);
		// }

		// try {
		// OutputStream out = openFileOutput("myfile.txt", MODE_PRIVATE);
		// out.write("this is my file!".getBytes());
		// out.close();
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// try {
		// InputStream in = openFileInput("myfile.txt");
		// ByteArrayOutputStream out = new ByteArrayOutputStream();
		// int len = -1;
		// byte[] bytes = new byte[128];
		// while ((len = in.read(bytes)) != -1) {
		// out.write(bytes, 0, len);
		// out.flush();
		// }
		// out.close();
		// in.close();
		//
		// Toast.makeText(this, new String(out.toByteArray()), 5000).show();
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
}