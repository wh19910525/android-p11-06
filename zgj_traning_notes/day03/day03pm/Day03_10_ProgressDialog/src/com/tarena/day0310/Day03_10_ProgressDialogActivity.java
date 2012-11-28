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
//		dialog.setTitle("��¼");
//		dialog.setIcon(android.R.drawable.ic_dialog_info);
//		dialog.setMessage("���ڵ�¼�����Ժ�...");
//		dialog.setCancelable(false);
//		dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//		dialog.setIndeterminate(true);
			
		dialog.setTitle("������ʾ");
		dialog.setIcon(android.R.drawable.ic_dialog_info);
		dialog.setMessage("��������...");
		dialog.setCancelable(false);//����Ϊfalse�������ؼ������˳���Ĭ��Ϊtrue
		dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//���� ������  ����ʽ
		dialog.setIndeterminate(false);//false Ϊ ��ʾ �����  ���ȣ��Ա� ����09��Ĭ��Ϊfalse
		dialog.setMax(100);
	}

	public void doClick(View v) {
		
		if (!workThread.isAlive()){
			Toast.makeText(this, "����  ok��", 3000).show();
			dialog.show();

			workThread.start();//ִ�� ���� ���� run
		}
		else
			Toast.makeText(this, "���� �Ѿ� ���ˣ�", 3000).show();
			
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