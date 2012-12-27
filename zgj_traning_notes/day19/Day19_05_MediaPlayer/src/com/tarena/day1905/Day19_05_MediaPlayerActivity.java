package com.tarena.day1905;

import java.io.IOException;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;

public class Day19_05_MediaPlayerActivity extends Activity {
	
	private MediaPlayer player ;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		try {
			
			player = new MediaPlayer();
			player.reset();
			
			/**如果本身音频数据在本地,无需再下载即直接采用此方式即可*/
			player.setDataSource("/mnt/sdcard/musics/yingbi.mp3");
			player.prepare();
			player.start();
			
//			player.seekTo(msec)//设置歌曲的偏移量,即歌曲的跳转
//			player.getDuration();//返回指定歌曲的时长
//			player.getCurrentPosition()//得到时长的当前位置,即时长的跳转，前进或后退
			
			
			// player.setOnCompletionListener(new OnCompletionListener() {
			//
			// @Override
			// public void onCompletion(MediaPlayer mp) {
			// // TODO Auto-generated method stub
			//
			// }
			// });
			
			
			/** 如果音频数据需要在网上下载,则采用以下这种方式,需要
			 * 给MediaPlayer对象添加侦听器,在数据全部准备好(下载完成)，后启动*/
			// player.prepareAsync();
			// player.setOnPreparedListener(new OnPreparedListener() {
			//
			// @Override
			// public void onPrepared(MediaPlayer mp) {
			// // TODO Auto-generated method stub
			// mp.start();
			// }
			// });
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		if(player.isPlaying())
			player.stop();
//			player.pause();
		
		player.release();
	}
}