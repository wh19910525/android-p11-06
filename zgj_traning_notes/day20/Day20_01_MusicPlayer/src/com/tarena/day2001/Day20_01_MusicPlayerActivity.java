package com.tarena.day2001;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.tarena.entity.Music;
import com.tarena.utils.GlobalUtils;

public class Day20_01_MusicPlayerActivity extends Activity {
	
	private ListView lvMusics;
	private Button btnPlayOrPasue;
	private TextView tvName, tvProgress, tvDuration;
	private SeekBar sbProgress;
	private MusicAdapter adapter;
	private MusicApplication app;
	private Music currentMusic;
	private InnerReceiver receiver;

	/**
	 * 界面初始化方法
	 */
	private void setupView() {

		lvMusics = (ListView) findViewById(R.id.lvMusics);
		adapter = new MusicAdapter(this, app.getPlayList());
		lvMusics.setAdapter(adapter);
		btnPlayOrPasue = (Button) findViewById(R.id.btnPlayOrPause);
		tvDuration = (TextView) findViewById(R.id.tvDuration_Player);
		tvName = (TextView) findViewById(R.id.tvMusicName_Player);
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
			//	Music currentMusic = app.getMusic(app.getCurrentPosition());
					// 发送广播，跳转
					Intent intent = new Intent(GlobalUtils.ACTION_SEEK_TO);
					progress = progress * (int) currentMusic.getDuration() / 100;//currentMusic 都没有获取实例怎么就能用了
					intent.putExtra(GlobalUtils.EXTRA_CURRENT_PROGRESS,
							progress);
					sendBroadcast(intent);
				}
			}
		});
	}

	/**
	 * 按钮的单击事件处理方法
	 * 
	 * @param v
	 */
	public void doClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btnPrevious:// 上一首
			intent.setAction(GlobalUtils.ACTION_PREVIOUS);
			btnPlayOrPasue.setText("暂停");
			break;
		case R.id.btnPlayOrPause:// 播放或暂停
			if ("播放".equals(btnPlayOrPasue.getText().toString())) {
				intent.setAction(GlobalUtils.ACTION_PLAY);
				btnPlayOrPasue.setText("暂停");
			} else {
				intent.setAction(GlobalUtils.ACTION_PAUSE);
				btnPlayOrPasue.setText("播放");
			}
			break;
		case R.id.btnNext:// 下一首
			intent.setAction(GlobalUtils.ACTION_NEXT);
			btnPlayOrPasue.setText("暂停");
			break;
		}
		sendBroadcast(intent);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		// 发送广播请求播放状态,使Service发回相应相应,显示上次Activity在交互状态时的数据显示
		Intent intent = new Intent(GlobalUtils.ACTION_REQUEST);
		sendBroadcast(intent);

		// 发送广播，改变更新状态,在Activity重新进入交互状态时开启Service的线程进行进度条和时长的更新
		intent = new Intent(GlobalUtils.ACTION_UPDATE_STATE_CHANGED);
		intent.putExtra(GlobalUtils.EXTRA_NEED_UPDATE, true);
		sendBroadcast(intent);

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		//此处再次发送改变状态的广播,当Activity停止时,就没有必要让Service的线程开启，此条通知
		//使得Service的needUpDate为false，这样Service的线程就不用开启了
		
		Intent intent = new Intent(GlobalUtils.ACTION_UPDATE_STATE_CHANGED);
		intent.putExtra(GlobalUtils.EXTRA_NEED_UPDATE, false);
		sendBroadcast(intent);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		// 取消动态注册的广播接收器
		unregisterReceiver(receiver);
	}

	/**
	 * 创建系统菜单的方法
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.opts, menu);
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * 系统菜单项的单击事件处理方法
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent(GlobalUtils.ACTION_PLAY_MODE_CHANGED);
		switch (item.getItemId()) {
		case R.id.sub_menu_loop://循环播放
			intent.putExtra(GlobalUtils.EXTRA_PLAY_MODE,
					GlobalUtils.PLAY_MODE_LOOP);
			break;
		case R.id.sub_menu_random://随机播放
			intent.putExtra(GlobalUtils.EXTRA_PLAY_MODE,
					GlobalUtils.PLAY_MODE_RANDOM);
			break;
		case R.id.menu_opts_exit://退出
			finish();
			intent.setAction(GlobalUtils.ACTION_SERVICE_STOP);//发送广播时Activity结束时Service也over
			break;
		}
		sendBroadcast(intent);
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}


	private class InnerReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			if (GlobalUtils.ACTION_CURRENT_MUSIC_CHANGED.equals(action)) {
				currentMusic = (Music) intent//获得正在播放音乐的相关数据
						.getSerializableExtra(GlobalUtils.EXTRA_CURRENT_MUSIC);

				// 更新界面，显示当前音乐信息
				tvName.setText(currentMusic.getName());
				long duration = currentMusic.getDuration();
				tvDuration.setText(GlobalUtils.format(duration));
				
			} else if (GlobalUtils.ACTION_UPDATE_PROGRESS.equals(action)) {
				
				// 获取最新的播放进度，只要Service的线程开启着,下面2项数据一直都可以得到更新
				int progress = intent.getIntExtra(
						GlobalUtils.EXTRA_CURRENT_PROGRESS, 0);
				// 更新界面
				tvProgress.setText(GlobalUtils.format(progress));
				progress = progress * 100 / (int) currentMusic.getDuration();
				sbProgress.setProgress(progress);
				
				//当Activity进入交互状态前,给Service发请求，Service通过发广播给Activity响应，并得到
				//Activity之前的状态,这样可以使得新开启的Activity显示之前的数据
				
			} else if (GlobalUtils.ACTION_RESPONSE.equals(action)) {
				int playState = intent.getIntExtra(
						GlobalUtils.EXTRA_PLAY_STATE, GlobalUtils.OTHERS);
				switch (playState) {
				case GlobalUtils.ISPAUSE:
					currentMusic = (Music)intent.getSerializableExtra(GlobalUtils.EXTRA_CURRENT_MUSIC);
					tvName.setText(currentMusic.getName());
					tvDuration.setText(GlobalUtils.format(currentMusic.getDuration()));
					
					int progress = intent.getIntExtra(GlobalUtils.EXTRA_CURRENT_PROGRESS, 0);
					tvProgress.setText(GlobalUtils.format(progress));
					progress = progress * 100/(int)currentMusic.getDuration();
					sbProgress.setProgress(progress);
					break;
				case GlobalUtils.ISPLAYING:
					currentMusic = (Music)intent.getSerializableExtra(GlobalUtils.EXTRA_CURRENT_MUSIC);
					tvName.setText(currentMusic.getName());
					tvDuration.setText(GlobalUtils.format(currentMusic.getDuration()));
					btnPlayOrPasue.setText("暂停");
					break;

				default:
					break;
				}
			}
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
		// 启动音乐播放服务
		Intent intent = new Intent(this, MusicService.class);
		startService(intent);

		// 注册广播接收器
		receiver = new InnerReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(GlobalUtils.ACTION_CURRENT_MUSIC_CHANGED);
		filter.addAction(GlobalUtils.ACTION_UPDATE_PROGRESS);
		filter.addAction(GlobalUtils.ACTION_RESPONSE);

		registerReceiver(receiver, filter);
	}
}