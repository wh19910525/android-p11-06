package com.onda.cn;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

public class ShutDownNow extends Activity {

	private static final int EXIT = 1;
	private AlertDialog dialog;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);//有什么用？
		// ==============================================
		dialog = new AlertDialog.Builder(this)
				.setTitle(R.string.power_off)
				.setMessage(getResources().getString(R.string.power_select))
				.setPositiveButton(
						getResources().getString(R.string.power_yes),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {

								beginShutdown(); // shutdown
							}
						})
				.setNegativeButton(getResources().getString(R.string.power_no),
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								Message m = Message.obtain(mHandler, EXIT);
								mHandler.sendMessage(m);
								dialog.cancel();
							}
						}).create();

		dialog.setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {//监听 其他 按键
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
				switch (keyCode) {
				
				case KeyEvent.KEYCODE_BACK:
					Log.d("wanghai", "======KEYCODE_BACK");
					Message m2 = Message.obtain(mHandler, EXIT);
					mHandler.sendMessage(m2);
					return true;
				case KeyEvent.KEYCODE_HOME:
					Log.d("wanghai", "======KEYCODE_HOME");
					Message m3 = Message.obtain(mHandler, EXIT);
					mHandler.sendMessage(m3);
					return true;
				default:
					Log.d("wanghai", "======other");
				}
				return false;
			}
		});

		// dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
		dialog.show();
	}

	Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case EXIT:
				Log.d("wanghai", "======dismiss");
			//	dialog.dismiss();
				finish();
				break;
			}
		}
	};
	
	private void beginShutdown() {

		final PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
		Thread thr = new Thread("Reboot") {
			@Override
			public void run() {
				Log.d("ShutDownNow", "===normal_reboot");
				pm.reboot("charging_reboot");//跟踪 这个 函数
			}
		};
		thr.start();
	}

//	public void onAttachedToWindow() {
//		// this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
//		super.onAttachedToWindow();
//	}
//
//	public boolean onKeyDown(int keyCode, KeyEvent msg) {
//
//		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			Log.d("wanghai", "onKeyDown======finish");
//			finish();
//		}
//		return super.onKeyDown(keyCode, msg);
//	}
}
