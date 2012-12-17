package com.tarena.day1202;

import java.lang.ref.SoftReference;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

public class Day12_02_SoftReferenceActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		
		SoftReference<Bitmap> sb = new SoftReference<Bitmap>(BitmapFactory.decodeFile("/mnt/sdcard/imags/p2.jpg"));
			if(sb.get()!=null){
			
		}else{
			
		}
	}
}