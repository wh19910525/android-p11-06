package com.tarena.day0312;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Day03_12_DemoActivity extends Activity {
	private static final int DIALOG_REG_MESSAGE = 1;
	private static final int DIALOG_ABOUT = 2;
	
	private static final int PASSWORD_OK = 1;
	private static final int PASSWORD_LENGTH_ERROR = 2;
	private static final int PASSWORD_ERROR = 3;
	
	private static final int MENU_OPTS_ABOUT = 1;
	private static final int MENU_OPTS_EXIT = 2;

	private EditText etUserName, etRealName, etUserPass, etPassAgain;
	private RadioGroup rgSex;
	private CheckBox chkEat, chkRead, chkPlay, chkSleep;
	private String msg;

	//�Ѷ�
	private void setupView() {
		etUserName = (EditText) findViewById(R.id.etUserName);
		etUserPass = (EditText) findViewById(R.id.etUserPass);
		etRealName = (EditText) findViewById(R.id.etRealName);
		etPassAgain = (EditText) findViewById(R.id.etPassAgain);

		rgSex = (RadioGroup) findViewById(R.id.rgSex);

		chkEat = (CheckBox) findViewById(R.id.chkEat);
		chkPlay = (CheckBox) findViewById(R.id.chkPlay);
		chkRead = (CheckBox) findViewById(R.id.chkRead);
		chkSleep = (CheckBox) findViewById(R.id.chkSleep);
	}

//�Ѷ�
	private boolean isEmpty(EditText et) {
		if (et.getText() == null || "".equals(et.getText().toString())) {
			return true;
		}
		return false;
	}

//�Ѷ�
	private int checkPass(String pass, String passAgain) {
		if (pass.length() < 6)
			return PASSWORD_LENGTH_ERROR;

		if (!pass.equals(passAgain)) {
			return PASSWORD_ERROR;
		}

		return PASSWORD_OK;
	}

//�Ѷ�
	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btnReg:// ע��
			// �ǿ���֤
			if (isEmpty(etUserName)) {
				etUserName.setError("�û�������Ϊ��");
				return;
			}
			if (isEmpty(etUserPass)) {
				etUserPass.setError("���벻��Ϊ��");
				return;
			}
			if (isEmpty(etPassAgain)) {
				etPassAgain.setError("���벻��Ϊ��");
				return;
			}
			if (isEmpty(etRealName)) {
				etRealName.setError("��ʵ��������Ϊ��");
				return;
			}

			// ��ȡ�û�����
			String userName = etUserName.getText().toString();
			String password = etUserPass.getText().toString();
			String passAgain = etPassAgain.getText().toString();
			String realName = etRealName.getText().toString();
			String sex = rgSex.getCheckedRadioButtonId() == R.id.rdoMale ? "��"
					: "Ů";
			// String sex = null;
			// if(rgSex.getCheckedRadioButtonId()==R.id.rdoMale)
			// sex = "��";
			// else
			// sex = "Ů";

			// ������Ч����֤
			switch (checkPass(password, passAgain)) {
			case PASSWORD_ERROR:
				Toast.makeText(this, "������������벻һ��", 3000).show();
				return;
			case PASSWORD_LENGTH_ERROR:
				Toast.makeText(this, "���벻������6λ", 3000).show();
				return;
			}
			// ��ʾע����Ϣ
			StringBuilder sb = new StringBuilder("����ע����Ϣ����:\n");
			sb.append("�û��� : ").append(userName).append('\n');
			sb.append("���� : ").append(password).append('\n');
			sb.append("��ʵ���� : ").append(realName).append('\n');
			sb.append("�Ա� : ").append(sex).append('\n');
			sb.append("���� :\n ");
			if (chkEat.isChecked())
				sb.append("��").append(',');
			if (chkPlay.isChecked())
				sb.append("��").append(',');
			if (chkSleep.isChecked())
				sb.append("˯��").append(',');
			if (chkRead.isChecked())
				sb.append("����").append(',');

			sb.deleteCharAt(sb.length() - 1);
			msg = sb.toString();
			// dialog.setMessage(msg);

			showDialog(DIALOG_REG_MESSAGE);
			break;

		case R.id.btnReset:// ����
			etPassAgain.setText("");
			etRealName.setText("");
			etUserName.setText("");
			etUserPass.setText("");

			rgSex.check(R.id.rdoMale);

			chkEat.setChecked(true);
			chkPlay.setChecked(true);
			chkRead.setChecked(true);
			chkSleep.setChecked(true);
			break;
		}
	}

//�Ѷ�
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		AlertDialog dialog = null;
		switch (id) {
		case DIALOG_REG_MESSAGE:
			dialog = new Builder(this).setTitle("ע����Ϣ")
					.setIcon(R.drawable.ic_launcher).setMessage("ssss")
					.setPositiveButton("ȷ��", null).create();
			break;

		case DIALOG_ABOUT:
			dialog = new Builder(this).setTitle("����")
					.setIcon(android.R.drawable.ic_menu_info_details)
					.setMessage("���ڱ�����:��ţ��һ������").setCancelable(true)
					.setPositiveButton("ȷ��", null).create();
			break;
		}
		return dialog;
	}

//�Ѷ�
	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		// TODO Auto-generated method stub
		if (id == DIALOG_REG_MESSAGE) {
			Log.i("info", "msg=" + msg);
			((AlertDialog) dialog).setMessage(msg);
		}
		super.onPrepareDialog(id, dialog);
	}

	//�Ѷ�
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		setupView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuItem item = menu.add(1, MENU_OPTS_ABOUT, 2, "����");
		item.setIcon(android.R.drawable.ic_menu_info_details);

		menu.add(1, MENU_OPTS_EXIT, 1, "�˳�").setIcon(
				android.R.drawable.ic_menu_close_clear_cancel);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case MENU_OPTS_ABOUT:// ����
			showDialog(DIALOG_ABOUT);
			break;

		case MENU_OPTS_EXIT:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}