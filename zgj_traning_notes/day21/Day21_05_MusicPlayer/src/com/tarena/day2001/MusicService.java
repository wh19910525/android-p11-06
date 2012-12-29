package com.tarena.day2001;

import java.io.IOException;
import java.util.Random;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;
import android.util.Log;

import com.tarena.entity.Music;
import com.tarena.utils.GlobalUtils;

public class MusicService extends Service {
	private MediaPlayer player;
	private int playMode;
	private boolean isPause;
	private Random random;
	private MusicApplication app;
	private Thread workThread;
	private boolean needUpdate;

	/**
	 * ��������
	 */
	public void play() {
		if (isPause) {
			player.start();
		} else {
			Music m = app.getCurrentMusic();
			if (m != null) {
				try {
					Log.i("info", "music:" + m.getName());
					player.reset();
					player.setDataSource(m.getPath());
					player.prepare();
					player.start();

					// ���͹㲥����ǰ���ֱ��
					Log.i("info", "duration:" + m.getDuration());
					Intent intent = new Intent(
							GlobalUtils.ACTION_CURRENT_MUSIC_CHANGED);
					intent.putExtra(GlobalUtils.EXTRA_CURRENT_MUSIC, m);
					sendBroadcast(intent);
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
		synchronized (workThread) {
			workThread.notify();
		}
	}

	/**
	 * ��ͣ����
	 */
	public void pause() {
		if (player.isPlaying()) {
			player.pause();
			isPause = true;
		}
	}

	/**
	 * ������һ������
	 */
	public void previous() {
		// ��ȡ��ǰ���ŵ����ֵ�����
		int currentIndex = app.getCurrentPosition();
		// ������һ�����ֵ�����
		switch (playMode) {
		case GlobalUtils.PLAY_MODE_LOOP:// ѭ��ģʽ
			if (--currentIndex < 0) {
				currentIndex = app.getCount() - 1;
			}
			break;

		case GlobalUtils.PLAY_MODE_RANDOM:// ���ģʽ
			do {
				currentIndex = random.nextInt(app.getCount());
			} while (currentIndex == app.getCurrentPosition());
			break;
		}
		app.setCurrentPosition(currentIndex);
		isPause = false;
		play();
	}

	/**
	 * ������һ������
	 */
	public void next() {
		// ��ȡ��ǰ���ŵ����ֵ�����
		int currentIndex = app.getCurrentPosition();
		// ������һ�����ֵ�����
		switch (playMode) {
		case GlobalUtils.PLAY_MODE_LOOP:// ѭ��ģʽ
			if (++currentIndex == app.getCount()) {
				currentIndex = 0;
			}
			break;

		case GlobalUtils.PLAY_MODE_RANDOM:// ���ģʽ
			do {
				currentIndex = random.nextInt(app.getCount());
			} while (currentIndex == app.getCurrentPosition());
			break;
		}
		// �����µĲ�������
		app.setCurrentPosition(currentIndex);
		isPause = false;
		// ������һ������
		play();
	}

	/**
	 * ��ת��ָ����λ�ü������ŵ�ǰ����
	 * 
	 * @param position
	 */
	public void seekTo(int position) {
		player.seekTo(position);
		player.start();
		isPause = false;
		synchronized (workThread) {
			workThread.notify();
		}
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		random = new Random(System.currentTimeMillis());
		playMode = GlobalUtils.PLAY_MODE_LOOP;
		isPause = false;
		app = (MusicApplication) getApplication();
		player = new MediaPlayer();
		player.setOnCompletionListener(new OnCompletionListener() {

			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				next();// ��ǰ���ֲ�����ɣ�������һ��
			}
		});

		needUpdate = true;
		// ���������������̣߳����߳��ڱ�Ҫʱÿ��һ�뷢�����²��Ž��ȹ㲥
		workThread = new Thread() {
			public void run() {
				while (needUpdate) {
					// ��������Ҫ���ȸ��������ִ��ڲ���״̬ʱ��ÿ��һ�뷢���µĲ��Ž���
					while (needUpdate && player.isPlaying()) {
						Intent intent = new Intent(
								GlobalUtils.ACTION_UPDATE_PROGRESS);
						intent.putExtra(GlobalUtils.EXTRA_CURRENT_PROGRESS,
								player.getCurrentPosition());
						sendBroadcast(intent);

						try {
							sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					Log.i("info", "�̵߳ȴ�");
					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					Log.i("info", "�����߳�");
				}
				Log.i("info", "�߳̽���");
			};
		};
		workThread.start();

		receiver = new InnerReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(GlobalUtils.ACTION_PLAY);
		filter.addAction(GlobalUtils.ACTION_PREVIOUS);
		filter.addAction(GlobalUtils.ACTION_PAUSE);
		filter.addAction(GlobalUtils.ACTION_SERVICE_STOP);
		filter.addAction(GlobalUtils.ACTION_PLAY_MODE_CHANGED);
		filter.addAction(GlobalUtils.ACTION_NEXT);
		filter.addAction(GlobalUtils.ACTION_UPDATE_STATE_CHANGED);
		filter.addAction(GlobalUtils.ACTION_SEEK_TO);
		filter.addAction(GlobalUtils.ACTION_REQUEST);

		registerReceiver(receiver, filter);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		// �ͷŲ�����
		if (player.isPlaying())
			player.stop();
		player.release();

		needUpdate = false;
		synchronized (workThread) {
			workThread.notify();
		}

		// ȡ��ע��㲥������
		unregisterReceiver(receiver);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	private InnerReceiver receiver;

	private class InnerReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			Log.i("info", "action:" + action);
			if (GlobalUtils.ACTION_PLAY.equals(action)) {
				play();
			} else if (GlobalUtils.ACTION_PAUSE.equals(action)) {
				pause();
			} else if (GlobalUtils.ACTION_PREVIOUS.equals(action)) {
				previous();
			} else if (GlobalUtils.ACTION_NEXT.equals(action)) {
				next();
			} else if (GlobalUtils.ACTION_PLAY_MODE_CHANGED.equals(action)) {
				playMode = intent.getIntExtra(GlobalUtils.EXTRA_PLAY_MODE,
						GlobalUtils.PLAY_MODE_LOOP);
				Log.i("info", "����ģʽ:" + playMode);
			} else if (GlobalUtils.ACTION_SERVICE_STOP.equals(action)) {
				stopSelf();
			} else if (GlobalUtils.ACTION_UPDATE_STATE_CHANGED.equals(action)) {
				needUpdate = intent.getBooleanExtra(
						GlobalUtils.EXTRA_NEED_UPDATE, true);

				Log.i("info", "needUpdate:" + needUpdate);
				synchronized (workThread) {
					workThread.notify();
				}
			} else if (GlobalUtils.ACTION_SEEK_TO.equals(action)) {
				// ��ȡ��ת����λ��
				int progress = intent.getIntExtra(
						GlobalUtils.EXTRA_CURRENT_PROGRESS, 0);
				// ��ת����
				seekTo(progress);
			} else if (GlobalUtils.ACTION_REQUEST.equals(action)) {
				Intent broadcast = new Intent(GlobalUtils.ACTION_RESPONSE);
				if (isPause) {
					broadcast.putExtra(GlobalUtils.EXTRA_PLAY_STATE,
							GlobalUtils.ISPAUSE);
					broadcast.putExtra(GlobalUtils.EXTRA_CURRENT_MUSIC,
							app.getCurrentMusic());
					broadcast.putExtra(GlobalUtils.EXTRA_CURRENT_PROGRESS,
							player.getCurrentPosition());
				} else if (player.isPlaying()) {
					broadcast.putExtra(GlobalUtils.EXTRA_PLAY_STATE,
							GlobalUtils.ISPLAYING);
					broadcast.putExtra(GlobalUtils.EXTRA_CURRENT_MUSIC,
							app.getCurrentMusic());
				} else {
					broadcast.putExtra(GlobalUtils.EXTRA_PLAY_STATE,
							GlobalUtils.OTHERS);
				}
				sendBroadcast(broadcast);
			}
		}
	}

}
