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
					Toast.makeText(Day02_08_RadioButtnActivity.this, "男h", 3000)
							.show();
					break;

				case R.id.rdoFemale:
					Toast.makeText(Day02_08_RadioButtnActivity.this, "女h", 3000)
							.show();
					break;
				}
			}
		});
	}

	public void doClick(View v) {
		// 输出被选中的单选按钮的值
		 int id = rgSex.getCheckedRadioButtonId();//获取 当前 被选中按钮 的值
		 switch (id) {
		 case R.id.rdoMale:
		 Toast.makeText(this, "男w", 3000).show();
		 break;
		
		 case R.id.rdoFemale:
		 Toast.makeText(this, "女w", 3000).show();
		 break;
		 }

		// 设置单选按钮的选中
		String sex = "女";
		if ("女".equals(sex)) {
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