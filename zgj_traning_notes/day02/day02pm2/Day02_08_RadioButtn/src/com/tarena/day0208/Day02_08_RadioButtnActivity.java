package com.tarena.day0208;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class Day02_08_RadioButtnActivity extends Activity {
	private RadioGroup rgSex;

	private void addListener() {
		rgSex.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.rdoMale:
					Toast.makeText(Day02_08_RadioButtnActivity.this, "��h", 3000)
							.show();
					break;

				case R.id.rdoFemale:
					Toast.makeText(Day02_08_RadioButtnActivity.this, "Ůh", 3000)
							.show();
					break;
				}
			}
		});
	}

	public void doClick(View v) {
		// �����ѡ�еĵ�ѡ��ť��ֵ
		 int id = rgSex.getCheckedRadioButtonId();//��ȡ ��ǰ ��ѡ�а�ť ��ֵ
		 switch (id) {
		 case R.id.rdoMale:
		 Toast.makeText(this, "��w", 3000).show();
		 break;
		
		 case R.id.rdoFemale:
		 Toast.makeText(this, "Ůw", 3000).show();
		 break;
		 }

		// ���õ�ѡ��ť��ѡ��
		String sex = "Ů";
		if ("Ů".equals(sex)) {
			rgSex.check(R.id.rdoFemale);
		} else {
			rgSex.check(R.id.rdoMale);
		}
	}

	private void setupView() {
		rgSex = (RadioGroup) findViewById(R.id.rgSex);
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