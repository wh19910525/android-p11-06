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
			btnOp.setText("Ìí¼Ó");
			int id = getIntent().getIntExtra(GlobalUtils.EXTRA_MUSIC_ID, 0);
			if (id != 0) {
				etId.setText(id + "");
			}
			break;

		case GlobalUtils.OP_TYPE_UPDATE:
			btnOp.setText("ÐÞ¸Ä");
			Music music = (Music) getIntent().getSerializableExtra(
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
		data.putExtra(GlobalUtils.EXTRA_OP_DATA, music);
		data.putExtra(GlobalUtils.EXTRA_OP_TYPE, opType);
		setResult(RESULT_OK, data);
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.musicop);
		opType = getIntent().getIntExtra(GlobalUtils.EXTRA_OP_TYPE,
				GlobalUtils.OP_TYPE_ADD);
		setupView();

	}
}
