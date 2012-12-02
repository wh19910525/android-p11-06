package com.tarena.day0502;

import com.tarena.entity.Music;
import com.tarena.utils.GlobalUtils;
import com.tarena.utils.TimeFormatter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MusicOpActivity extends Activity {
	
	private int opType;
	private EditText etName, etAlbum, etSinger, etComposer, etDuration,
			etMusicPath, etId;
	private Button btnOp;

	private void setupView() {
		etId = (EditText) findViewById(R.id.etId);
		etName = (EditText) findViewById(R.id.etName);
		etAlbum = (EditText) findViewById(R.id.etAlbum);
		etSinger = (EditText) findViewById(R.id.etSinger);
		etComposer = (EditText) findViewById(R.id.etComposer);
		etDuration = (EditText) findViewById(R.id.etDuration);
		etMusicPath = (EditText) findViewById(R.id.etPath);

		btnOp = (Button) findViewById(R.id.btnOp);
		
		switch (opType) {
		case GlobalUtils.OP_TYPE_ADD:
			btnOp.setText("添加");
			int id = getIntent().getIntExtra(GlobalUtils.EXTRA_MUSIC_ID, 0);
			if (id != 0) {
				etId.setText(id + "");
			}
			break;

		case GlobalUtils.OP_TYPE_UPDATE:
			btnOp.setText("修改");
			Music music = (Music) getIntent().getSerializableExtra(//返回 参数 键 的 值，如果 获取不到，就返回 null
					GlobalUtils.EXTRA_OP_DATA);
			if (music != null) {
				etId.setText(music.getId() + "");
				etName.setText(music.getName());
				etAlbum.setText(music.getAlbum());
				etSinger.setText(music.getSinger());
				etComposer.setText(music.getComposer());
				etDuration.setText(music.getDuration() + "");
				etMusicPath.setText(music.getMusicPath());
			}
			break;
		}
	}

	public void doClick(View v) {
		
		Music music = new Music();
		music.setId(Integer.parseInt(etId.getText().toString()));
		music.setName(etName.getText().toString());
		music.setAlbum(etAlbum.getText().toString());
		music.setSinger(etSinger.getText().toString());
		music.setComposer(etComposer.getText().toString());
		music.setMusicPath(etMusicPath.getText().toString());
		music.setDuration(Long.parseLong(etDuration.getText().toString()));

		Intent data = new Intent();
		data.putExtra(GlobalUtils.EXTRA_OP_DATA, music);//想用 putExtra 这个方法 传送 类，那么 这个类需要  实现 Serializable 接口
		data.putExtra(GlobalUtils.EXTRA_OP_TYPE, opType);
		setResult(RESULT_OK, data);//当执行完 此方法之后，会自动启动 onActivityResult，所以 这里 就启动了 另一个 activity，
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.musicop);
		opType = getIntent().getIntExtra(GlobalUtils.EXTRA_OP_TYPE,//返回 第一参数 键 的 值，如果 获取不到，就用 第二个参数的值
				GlobalUtils.OP_TYPE_ADD);
		setupView();

	}
}
