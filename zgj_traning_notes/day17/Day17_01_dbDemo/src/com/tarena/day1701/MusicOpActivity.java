package com.tarena.day1701;

import com.tarena.biz.MusicBiz;
import com.tarena.entity.Music;
import com.tarena.utils.GlobalUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MusicOpActivity extends Activity {
	
	private int opType;
	private MusicBiz biz;
	private EditText etName, etAlbum, etSinger, etAuthor, etComposer,
			etDuration, etAlbumPath, etMusicPath, etId;
	private Button btnOp;

	private void setupView() {
		etId = (EditText) findViewById(R.id.etId);
		etName = (EditText) findViewById(R.id.etName);
		etAlbum = (EditText) findViewById(R.id.etAlbum);
		etSinger = (EditText) findViewById(R.id.etSinger);
		etAuthor = (EditText) findViewById(R.id.etAuthor);
		etComposer = (EditText) findViewById(R.id.etComposer);
		etDuration = (EditText) findViewById(R.id.etDuration);
		etAlbumPath = (EditText) findViewById(R.id.etAlbumPath);
		etMusicPath = (EditText) findViewById(R.id.etMusicPath);

		btnOp = (Button) findViewById(R.id.btnOp);

		switch (opType) {
		case GlobalUtils.OP_TYPE_ADD:// 添加
			btnOp.setText("添加");
			break;

		case GlobalUtils.OP_TYPE_UPDATE:// 修改
			btnOp.setText("修改");
			Music m = (Music) getIntent().getSerializableExtra(
					GlobalUtils.EXTRA_OP_DATA);
			etId.setText(m.getId() + "");
			etDuration.setText(m.getDuration() + "");
			etName.setText(m.getName());
			etAlbum.setText(m.getAlbum());
			etSinger.setText(m.getSinger());
			etAuthor.setText(m.getAuthor());
			etComposer.setText(m.getComposer());
			etAlbumPath.setText(m.getAlbumPath());
			etMusicPath.setText(m.getMusicPath());

			break;
		}

	}

	public void doClick(View v) {
		Music m = new Music();
		m.setName(etName.getText().toString());
		m.setAlbum(etAlbum.getText().toString());
		m.setSinger(etSinger.getText().toString());
		m.setAuthor(etAuthor.getText().toString());
		m.setComposer(etComposer.getText().toString());
		m.setDuration(Integer.parseInt(etDuration.getText().toString()));
		m.setAlbumPath(etAlbumPath.getText().toString());
		m.setMusicPath(etMusicPath.getText().toString());

		switch (opType) {
		case GlobalUtils.OP_TYPE_ADD:
			biz.addMusic(m);
			break;

		case GlobalUtils.OP_TYPE_UPDATE:
			m.setId(Integer.parseInt(etId.getText().toString()));
			biz.modifyMusic(m);
			break;
		}

		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.music_op);
		opType = getIntent().getIntExtra(GlobalUtils.EXTRA_OP_TYPE, GlobalUtils.OP_TYPE_ADD);
		biz = new MusicBiz(this);
		setupView();
	}
}
