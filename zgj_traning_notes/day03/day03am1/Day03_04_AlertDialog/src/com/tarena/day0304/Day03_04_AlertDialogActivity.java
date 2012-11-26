package com.tarena.day0304;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Day03_04_AlertDialogActivity extends Activity {
	
	private String[] items = { "item1", "item2", "item3" };
	private boolean[] choices = { true, false, false };
	private AlertDialog dialog;

	private void setupView() {
		dialog = new Builder(this)
				.setTitle("复选对话框")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setMultiChoiceItems(items, choices,
						new OnMultiChoiceClickListener() {

							public void onClick(DialogInterface dialog,
									int which, boolean isChecked) {
								// TODO Auto-generated method stub
								choices[which] = isChecked;//选中 选择 项；
							}
						}).setPositiveButton("确定", new OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						StringBuilder sb = new StringBuilder("您选择了:\n");//
						
						for (int i = 0; i < choices.length; i++) {
							if (choices[i]) {
								sb.append(items[i]).append(',');//
							}
						}
						sb.deleteCharAt(sb.length() - 1);//

						Toast.makeText(Day03_04_AlertDialogActivity.this,
								sb.toString(), 3000).show();
					}
				}).setNegativeButton("取消", null).create();
	}

	public void doClick(View v) {
		dialog.show();
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}
}