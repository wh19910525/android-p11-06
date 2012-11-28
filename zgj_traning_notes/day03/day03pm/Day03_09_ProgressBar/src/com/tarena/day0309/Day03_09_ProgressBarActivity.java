package com.tarena.day0309;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class Day03_09_ProgressBarActivity extends Activity {
	
	private ProgressBar pb;
	private Thread workThread;

	private void setupView() {
		pb = (ProgressBar) findViewById(R.id.progressBar1);
	}

	public void doClick(View v) {
		if (!workThread.isAlive())
			workThread.start();//执行 进程 函数 run
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
		workThread = new Thread() {
			@Override
			public void run() {//
				// TODO Auto-generated method stub
				for (int i = 0; i <= 100; i += 10) {
					pb.setProgress(i);//
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				finish();
			}
		};
	}
}