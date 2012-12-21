package com.tarena.day1505;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.os.Bundle;

public class Day15_05_IOActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //获取指定名的数据文件的存储路径
        File file = getDatabasePath("media.db");
        //如果该文件不存在
        if(!file.exists()){
        	//如果数据库目录不存在，则创建数据库目录
        	if(!file.getParentFile().exists()){
        		file.getParentFile().mkdirs();
        	}
        	try {
				//从原生资源目录向数据库目录复制数据库文件
				InputStream in = getAssets().open("media.db");
				BufferedInputStream is = new BufferedInputStream(in);
				FileOutputStream out = new FileOutputStream(file);
				BufferedOutputStream os = new BufferedOutputStream(out);
				int len = -1;
				byte[] buffer = new byte[1024];
				while((len=is.read(buffer))!=-1){
					os.write(buffer,0,len);
					os.flush();
				}
				os.close();
				out.close();
				is.close();
				in.close();
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