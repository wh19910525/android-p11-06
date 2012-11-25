package com.tarena.day0301;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Day03_01_AlertDialogActivity extends Activity {
	private AlertDialog dialog;//

	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btnShow:
			//dialog.setMessage("ssssssss" + new Random().nextInt());
			// dialog.setIcon(R.drawable.ic_launcher);
			// dialog.setTitle("�Ի���");
			dialog.show();
			break;
		}
	}

	private void setupView() {
		// Resources res = this.getResources();
		// Drawable icon = res.getDrawable(R.drawable.ic_launcher);
		// String title = res.getString(R.string.hello);
		Builder builder = new Builder(this);
		dialog = builder.setTitle("����").setIcon(R.drawable.ic_launcher)
				.setMessage("�ò���������ɾ�����ļ������ɻָ���ȷ��ִ��ô?")//Ϊʲôû�� ��ʾ
				.setCancelable(false)//����Ϊfalse�������ؼ������˳���Ĭ��Ϊtrue
				.setPositiveButton("ȷ��",new OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						//dialog.dismiss();
						Toast.makeText(Day03_01_AlertDialogActivity.this, "����� ȷ�� ��ť��", 3000).show();
					}
				}).setNegativeButton("ȡ��", null).setNeutralButton("����", null)
				.create();//�˴� ���� �� show() ���棻
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();

	}
}