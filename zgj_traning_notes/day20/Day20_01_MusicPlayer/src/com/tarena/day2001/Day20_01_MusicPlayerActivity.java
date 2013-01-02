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
	 * �����ʼ������
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
					// ���͹㲥����ת
					Intent intent = new Intent(GlobalUtils.ACTION_SEEK_TO);
					progress = progress * (int) currentMusic.getDuration() / 100;//currentMusic ��û�л�ȡʵ����ô��������
					intent.putExtra(GlobalUtils.EXTRA_CURRENT_PROGRESS,
							progress);
					sendBroadcast(intent);
				}
			}
		});
	}

	/**
	 * ��ť�ĵ����¼�������
	 * 
	 * @param v
	 */
	public void doClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.btnPrevious:// ��һ��
			intent.setAction(GlobalUtils.ACTION_PREVIOUS);
			btnPlayOrPasue.setText("��ͣ");
			break;
		case R.id.btnPlayOrPause:// ���Ż���ͣ
			if ("����".equals(btnPlayOrPasue.getText().toString())) {
				intent.setAction(GlobalUtils.ACTION_PLAY);
				btnPlayOrPasue.setText("��ͣ");
			} else {
				intent.setAction(GlobalUtils.ACTION_PAUSE);
				btnPlayOrPasue.setText("����");
			}
			break;
		case R.id.btnNext:// ��һ��
			intent.setAction(GlobalUtils.ACTION_NEXT);
			btnPlayOrPasue.setText("��ͣ");
			break;
		}
		sendBroadcast(intent);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

		// ���͹㲥���󲥷�״̬,ʹService������Ӧ��Ӧ,��ʾ�ϴ�Activity�ڽ���״̬ʱ��������ʾ
		Intent intent = new Intent(GlobalUtils.ACTION_REQUEST);
		sendBroadcast(intent);

		// ���͹㲥���ı����״̬,��Activity���½��뽻��״̬ʱ����Service���߳̽��н�������ʱ���ĸ���
		intent = new Intent(GlobalUtils.ACTION_UPDATE_STATE_CHANGED);
		intent.putExtra(GlobalUtils.EXTRA_NEED_UPDATE, true);
		sendBroadcast(intent);

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		//�˴��ٴη��͸ı�״̬�Ĺ㲥,��Activityֹͣʱ,��û�б�Ҫ��Service���߳̿���������֪ͨ
		//ʹ��Service��needUpDateΪfalse������Service���߳̾Ͳ��ÿ�����
		
		Intent intent = new Intent(GlobalUtils.ACTION_UPDATE_STATE_CHANGED);
		intent.putExtra(GlobalUtils.EXTRA_NEED_UPDATE, false);
		sendBroadcast(intent);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		// ȡ����̬ע��Ĺ㲥������
		unregisterReceiver(receiver);
	}

	/**
	 * ����ϵͳ�˵��ķ���
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = new MenuInflater(this);
		inflater.inflate(R.menu.opts, menu);
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * ϵͳ�˵���ĵ����¼�������
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent = new Intent(GlobalUtils.ACTION_PLAY_MODE_CHANGED);
		switch (item.getItemId()) {
		case R.id.sub_menu_loop://ѭ������
			intent.putExtra(GlobalUtils.EXTRA_PLAY_MODE,
					GlobalUtils.PLAY_MODE_LOOP);
			break;
		case R.id.sub_menu_random://�������
			intent.putExtra(GlobalUtils.EXTRA_PLAY_MODE,
					GlobalUtils.PLAY_MODE_RANDOM);
			break;
		case R.id.menu_opts_exit://�˳�
			finish();
			intent.setAction(GlobalUtils.ACTION_SERVICE_STOP);//���͹㲥ʱActivity����ʱServiceҲover
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
				currentMusic = (Music) intent//������ڲ������ֵ��������
						.getSerializableExtra(GlobalUtils.EXTRA_CURRENT_MUSIC);

				// ���½��棬��ʾ��ǰ������Ϣ
				tvName.setText(currentMusic.getName());
				long duration = currentMusic.getDuration();
				tvDuration.setText(GlobalUtils.format(duration));
				
			} else if (GlobalUtils.ACTION_UPDATE_PROGRESS.equals(action)) {
				
				// ��ȡ���µĲ��Ž��ȣ�ֻҪService���߳̿�����,����2������һֱ�����Եõ�����
				int progress = intent.getIntExtra(
						GlobalUtils.EXTRA_CURRENT_PROGRESS, 0);
				// ���½���
				tvProgress.setText(GlobalUtils.format(progress));
				progress = progress * 100 / (int) currentMusic.getDuration();
				sbProgress.setProgress(progress);
				
				//��Activity���뽻��״̬ǰ,��Service������Serviceͨ�����㲥��Activity��Ӧ�����õ�
				//Activity֮ǰ��״̬,��������ʹ���¿�����Activity��ʾ֮ǰ������
				
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
					btnPlayOrPasue.setText("��ͣ");
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
		// �������ֲ��ŷ���
		Intent intent = new Intent(this, MusicService.class);
		startService(intent);

		// ע��㲥������
		receiver = new InnerReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(GlobalUtils.ACTION_CURRENT_MUSIC_CHANGED);
		filter.addAction(GlobalUtils.ACTION_UPDATE_PROGRESS);
		filter.addAction(GlobalUtils.ACTION_RESPONSE);

		registerReceiver(receiver, filter);
	}
}