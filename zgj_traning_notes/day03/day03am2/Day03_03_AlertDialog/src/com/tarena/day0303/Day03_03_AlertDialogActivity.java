package com.tarena.day0303;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Day03_03_AlertDialogActivity extends Activity {
	private String[] items = { "item1", "item2", "item3" };
	private AlertDialog dialog;

	private void setupView() {
		dialog = new Builder(this).setTitle("对话框")
				.setIcon(android.R.drawable.ic_dialog_info)
				.setSingleChoiceItems(items, 1, new OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						// 输出选中项信息
						Toast.makeText(Day03_03_AlertDialogActivity.this,
								"您选择了:" + items[which], 3000).show();

						// 关闭对话框
						dialog.dismiss();
					}
				}).setNegativeButton("取消", null).create();
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}

	public void doClick(View v) {
		dialog.show();
	}
}