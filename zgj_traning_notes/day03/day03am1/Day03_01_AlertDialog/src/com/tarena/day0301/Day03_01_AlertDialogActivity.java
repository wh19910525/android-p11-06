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
			// dialog.setTitle("对话框");
			dialog.show();
			break;
		}
	}

	private void setupView() {
		// Resources res = this.getResources();
		// Drawable icon = res.getDrawable(R.drawable.ic_launcher);
		// String title = res.getString(R.string.hello);
		Builder builder = new Builder(this);
		dialog = builder.setTitle("警告").setIcon(R.drawable.ic_launcher)
				.setMessage("该操作将物理删除该文件，不可恢复，确认执行么?")//为什么没有 显示
				.setCancelable(false)//设置为false，按返回键不能退出。默认为true
				.setPositiveButton("确定",new OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						//dialog.dismiss();
						Toast.makeText(Day03_01_AlertDialogActivity.this, "点击了 确定 按钮！", 3000).show();
					}
				}).setNegativeButton("取消", null).setNeutralButton("忽略", null)
				.create();//此处 可以 用 show() 代替；
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();

	}
}