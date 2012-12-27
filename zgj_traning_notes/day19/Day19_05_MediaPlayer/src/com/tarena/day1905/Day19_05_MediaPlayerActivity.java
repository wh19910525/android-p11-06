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
			
			/**���������Ƶ�����ڱ���,���������ؼ�ֱ�Ӳ��ô˷�ʽ����*/
			player.setDataSource("/mnt/sdcard/musics/yingbi.mp3");
			player.prepare();
			player.start();
			
//			player.seekTo(msec)//���ø�����ƫ����,����������ת
//			player.getDuration();//����ָ��������ʱ��
//			player.getCurrentPosition()//�õ�ʱ���ĵ�ǰλ��,��ʱ������ת��ǰ�������
			
			
			// player.setOnCompletionListener(new OnCompletionListener() {
			//
			// @Override
			// public void onCompletion(MediaPlayer mp) {
			// // TODO Auto-generated method stub
			//
			// }
			// });
			
			
			/** �����Ƶ������Ҫ����������,������������ַ�ʽ,��Ҫ
			 * ��MediaPlayer�������������,������ȫ��׼����(�������)��������*/
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