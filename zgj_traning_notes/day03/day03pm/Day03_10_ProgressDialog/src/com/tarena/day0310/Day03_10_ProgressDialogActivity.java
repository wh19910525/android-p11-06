package com.tarena.day0310;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Day03_10_ProgressDialogActivity extends Activity {
	
	private ProgressDialog dialog;
	private Thread workThread;

	private void setupView() {
		dialog = new ProgressDialog(this);
//		dialog.setTitle("登录");
//		dialog.setIcon(android.R.drawable.ic_dialog_info);
//		dialog.setMessage("正在登录，请稍候...");
//		dialog.setCancelable(false);
//		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//		dialog.setIndeterminate(true);
			
		dialog.setTitle("下载提示");
		dialog.setIcon(android.R.drawable.ic_dialog_info);
		dialog.setMessage("正在下载...");
		dialog.setCancelable(false);//设置为false，按返回键不能退出。默认为true
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//设置 进度条  的样式
		dialog.setIndeterminate(false);//false 为 显示 具体的  进度，对比 例子09，默认为false
		dialog.setMax(100);
	}

	public void doClick(View v) {
		
		if (!workThread.isAlive()){
			Toast.makeText(this, "进程  ok！", 3000).show();
			dialog.show();

			workThread.start();//执行 进程 函数 run
		}
		else
			Toast.makeText(this, "进程 已经 死了！", 3000).show();
			
	}

	private void sleep(int i) {
		// TODO Auto-generated method stub
		
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
		
		workThread = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
//				try {
//					sleep(5000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				for(int i=0;i<=100;i+=10){
					try {
						sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					dialog.setProgress(i);//
				}				
				dialog.dismiss();
			}
		};
	}
}