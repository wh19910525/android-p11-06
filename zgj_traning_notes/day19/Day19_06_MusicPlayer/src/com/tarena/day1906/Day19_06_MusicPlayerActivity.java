package com.tarena.day1906;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.tarena.day1906.MusicService.MyBinder;
import com.tarena.entity.Music;
import com.tarena.utils.GlobalUtils;

public class Day19_06_MusicPlayerActivity extends Activity {
	
	private ListView lvMusics;
	private TextView tvMusicName, tvProgress, tvDuration;
	private SeekBar sbProgress;
	private MusicAdapter adapter;
	private MusicApplication app;
	private Intent intent;
	private MusicService service;
	private Button btnPlayOrPause;
	private boolean isLoop;
	private Thread workThread;

	private void setupView() {
		lvMusics = (ListView) findViewById(R.id.lvMusics);
		adapter = new MusicAdapter(this, app.getPlayList());
		lvMusics.setAdapter(adapter);

		btnPlayOrPause = (Button) findViewById(R.id.btnPlayOrPause);

		tvDuration = (TextView) findViewById(R.id.tvDuration_Player);
		tvMusicName = (TextView) findViewById(R.id.tvMusicName_Player);
		tvProgress = (TextView) findViewById(R.id.tvProgress_Player);

		sbProgress = (SeekBar) findViewById(R.id.sbProgress_Player);
	}
	
	private void addListener() {
		sbProgress.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {//
				// TODO Auto-generated method stub
				if (fromUser) {
					Music m = app.getMusic(app.getCurrentIndex());
					if (m != null) {
						progress = progress * (int) m.getDuration() / 100;//��μ���
						service.seekTo(progress);//���� ���ֲ��Ž���
						flush();
						synchronized (workThread) {
							workThread.notify();
						}
					}
				}
			}
		});
	}

	private ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder binder) {
			// TODO Auto-generated method stub
			service = ((MyBinder) binder).getService();//
		}
	};

	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btnPrevious:
			service.previous();
			btnPlayOrPause.setText("��ͣ");
			flush();
			break;

		case R.id.btnPlayOrPause:
			if ("����".equals(btnPlayOrPause.getText().toString())) {
				service.play();
				flush();
				btnPlayOrPause.setText("��ͣ");
			} else {
				service.pause();
				btnPlayOrPause.setText("����");
			}
			break;
		case R.id.btnNext:
			service.next();
			btnPlayOrPause.setText("��ͣ");
			flush();
			break;
		}

		synchronized (workThread) {
			workThread.notify();
		}
	}
	
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				int position = msg.arg1;
				tvProgress.setText(GlobalUtils.format(position));
				Music m = app.getMusic(app.getCurrentIndex());
				position = position * 100 / (int) m.getDuration();//��μ���
				sbProgress.setProgress((int) position);//���� �������� ����
				break;
			}
		};
	};
	
	/**
	 �ѹ����߳�д��onStart()�����е�Ŀ��,��Ϊ����Activityÿ�����½��뽻��״̬ǰ�����������̡߳�
	 ����Activity������ͣ������״̬ʱ,�����߳̾�û�б�Ҫ����ִ��,�Ӷ������ڴ������ʡ�
	 ֻ����Service�ں�̨���м��ɡ���Activity���ڽ���״̬ʱ�����������߳�,�Ӷ��������ݵ�*/
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		workThread = new Thread() {
			public void run() {
				while (isLoop) {
					if (service != null) {
						while (isLoop && service.isPlaying()) {//���ѭ�� ���� ���� ������
							Message msg = Message.obtain();
							msg.what = 0;
							msg.arg1 = (int) service.getCurrentPosition();
							handler.sendMessage(msg);
							try {
								sleep(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}

					if (!isLoop)
						break;

					synchronized (this) {
						try {
							this.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			};
		};
		workThread.start();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		isLoop = false;
		synchronized (workThread) {
			workThread.notify();
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		unbindService(conn);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.opts, menu);
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.sub_menu_loop:
			service.setPlayMode(GlobalUtils.PLAY_MODE_LOOP);
			break;
		case R.id.sub_menu_random:
			service.setPlayMode(GlobalUtils.PLAY_MODE_RANDOM);
			break;
		case R.id.menu_opts_exit:
			stopService(intent);
			finish();
			break;
		}
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}

	private void flush() {//ˢ�� ��ʾ �� ���� ������ �� ���ֳ���
		Music m = app.getMusic(app.getCurrentIndex());
		if (m != null) {
			tvMusicName.setText(m.getName());
			tvDuration.setText(GlobalUtils.format(m.getDuration()));
		}
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		app = (MusicApplication) getApplication();
		setupView();
		addListener();
		isLoop = true;

		intent = new Intent(this, MusicService.class);
		startService(intent);//Ϊʲô ���� ������ ���� service �� ���� �� service;
		bindService(intent, conn, BIND_AUTO_CREATE);
	}
	
}