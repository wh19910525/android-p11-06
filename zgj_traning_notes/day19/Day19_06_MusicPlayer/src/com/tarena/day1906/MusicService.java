package com.tarena.day1906;

import java.io.IOException;
import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.tarena.entity.Music;
import com.tarena.utils.GlobalUtils;

public class MusicService extends Service {
	
	private MusicApplication app;
	private MediaPlayer player;
	private boolean isPause;//用于判断音乐是否暂停
	private int playMode;//播放模式
	private Random rand;

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		isPause = false;//个人觉得应该是true
		playMode = GlobalUtils.PLAY_MODE_LOOP;
		rand = new Random(System.currentTimeMillis());
		app = (MusicApplication) getApplication();

		player = new MediaPlayer();//
		player.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				next();
			}
		});

	}
	
	public boolean isPause() {
		return isPause;
	}

	public void setPlayMode(int playMode) {
		this.playMode = playMode;
	}

	public void play() {
		if (isPause) {//判断是否为暂停,如果暂停则直接播放即可
			player.start();
		} else {
			int currentIndex = app.getCurrentIndex();
			Music m = app.getMusic(currentIndex);
			Log.i("info", "music:" + m.getName());
			if (m != null) {
				try {
					player.reset();
					player.setDataSource(m.getPath());
					player.prepare();
					player.start();
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
		}

		isPause = false;
	}

	public void pause() {
		if (player.isPlaying()) {
			player.pause();
			isPause = true;//
		}
	}

	public void next() {
		int currentIndex = app.getCurrentIndex();
		switch (playMode) {
		case GlobalUtils.PLAY_MODE_LOOP:
			if (++currentIndex == app.getPlayList().size())
				currentIndex = 0;
			break;
		case GlobalUtils.PLAY_MODE_RANDOM:
			do {//使用do while方法是为了避免随机产生的歌曲有连续重复的
				currentIndex = rand.nextInt(app.getPlayList().size());
			} while (currentIndex == app.getCurrentIndex());
			break;
		}
		
		app.setCurrentIndex(currentIndex);
		isPause = false;
		play();
	}

	public void previous() {
		int currentIndex = app.getCurrentIndex();
		switch (playMode) {
		case GlobalUtils.PLAY_MODE_LOOP:
			if (--currentIndex < 0)
				currentIndex = app.getPlayList().size() - 1;
			break;
		case GlobalUtils.PLAY_MODE_RANDOM:
			do {
				currentIndex = rand.nextInt(app.getPlayList().size());
			} while (currentIndex == app.getCurrentIndex());
			break;
		}
		app.setCurrentIndex(currentIndex);
		isPause = false;
		play();
	}

	public void seekTo(int position) {
		player.seekTo(position);//时长的偏移量,快进或后退
		player.start();
		isPause = false;
	}

	public long getCurrentPosition() {
		return player.getCurrentPosition();
	}

	public boolean isPlaying() {
		return player.isPlaying();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (player.isPlaying())
			player.stop();

		player.release();
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return new MyBinder();
	}

	public class MyBinder extends Binder {
		
		public MusicService getService() {
			return MusicService.this;
		}
		
	}
}
