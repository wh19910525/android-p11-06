package com.tarena.day0209;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

public class Day02_09_CheckBoxActivity extends Activity implements
		OnCheckedChangeListener {
	private CheckBox chkEat, chkSleep, chkPlay, chkRead;

	private void setupView() {
		chkEat = (CheckBox) findViewById(R.id.chkEat);
		chkPlay = (CheckBox) findViewById(R.id.chkPlay);
		chkRead = (CheckBox) findViewById(R.id.chkRead);
		chkSleep = (CheckBox) findViewById(R.id.chkSleep);
	}

	private void addListener() {
		chkEat.setOnCheckedChangeListener(this);
		chkPlay.setOnCheckedChangeListener(this);
		chkRead.setOnCheckedChangeListener(this);
		chkSleep.setOnCheckedChangeListener(this);
	}

	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		String message = null;
		if (isChecked) {
			message = "您喜欢" + buttonView.getText();
		} else {
			message = "您确定您不喜欢" + buttonView.getText() + "么？";
		}

		Toast.makeText(this, message, 3000).show();

	}

	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btnOk:
			StringBuilder sb = new StringBuilder("您的爱好是:\n");//
			if (chkEat.isChecked())
				sb.append(chkEat.getText()).append(',');
			if (chkPlay.isChecked())
				sb.append(chkPlay.getText()).append(',');
			if (chkRead.isChecked())
				sb.append(chkRead.getText()).append(',');
			if (chkSleep.isChecked())
				sb.append(chkSleep.getText()).append(',');

			sb.deleteCharAt(sb.length() - 1);//因为 最后 一个 字符是 逗号，所以删除 最后 一个 逗号

			Toast.makeText(this, sb.toString(), 3000).show();
			break;

		case R.id.btnReset:
			chkEat.setChecked(true);
			chkPlay.setChecked(true);
			chkRead.setChecked(true);
			chkSleep.setChecked(true);
			break;
		}

	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
		addListener();
	}
}