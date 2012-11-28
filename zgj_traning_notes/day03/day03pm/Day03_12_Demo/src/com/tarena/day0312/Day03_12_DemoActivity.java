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

	//已读
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

//已读
	private boolean isEmpty(EditText et) {
		if (et.getText() == null || "".equals(et.getText().toString())) {
			return true;
		}
		return false;
	}

//已读
	private int checkPass(String pass, String passAgain) {
		if (pass.length() < 6)
			return PASSWORD_LENGTH_ERROR;

		if (!pass.equals(passAgain)) {
			return PASSWORD_ERROR;
		}

		return PASSWORD_OK;
	}

//已读
	public void doClick(View v) {
		switch (v.getId()) {
		case R.id.btnReg:// 注册
			// 非空验证
			if (isEmpty(etUserName)) {
				etUserName.setError("用户名不能为空");
				return;
			}
			if (isEmpty(etUserPass)) {
				etUserPass.setError("密码不能为空");
				return;
			}
			if (isEmpty(etPassAgain)) {
				etPassAgain.setError("密码不能为空");
				return;
			}
			if (isEmpty(etRealName)) {
				etRealName.setError("真实姓名不能为空");
				return;
			}

			// 获取用户输入
			String userName = etUserName.getText().toString();
			String password = etUserPass.getText().toString();
			String passAgain = etPassAgain.getText().toString();
			String realName = etRealName.getText().toString();
			String sex = rgSex.getCheckedRadioButtonId() == R.id.rdoMale ? "男"
					: "女";
			// String sex = null;
			// if(rgSex.getCheckedRadioButtonId()==R.id.rdoMale)
			// sex = "男";
			// else
			// sex = "女";

			// 数据有效性验证
			switch (checkPass(password, passAgain)) {
			case PASSWORD_ERROR:
				Toast.makeText(this, "两次输入的密码不一致", 3000).show();
				return;
			case PASSWORD_LENGTH_ERROR:
				Toast.makeText(this, "密码不能少于6位", 3000).show();
				return;
			}
			// 显示注册信息
			StringBuilder sb = new StringBuilder("您的注册信息如下:\n");
			sb.append("用户名 : ").append(userName).append('\n');
			sb.append("密码 : ").append(password).append('\n');
			sb.append("真实姓名 : ").append(realName).append('\n');
			sb.append("性别 : ").append(sex).append('\n');
			sb.append("爱好 :\n ");
			if (chkEat.isChecked())
				sb.append("吃").append(',');
			if (chkPlay.isChecked())
				sb.append("玩").append(',');
			if (chkSleep.isChecked())
				sb.append("睡觉").append(',');
			if (chkRead.isChecked())
				sb.append("读书").append(',');

			sb.deleteCharAt(sb.length() - 1);
			msg = sb.toString();
			// dialog.setMessage(msg);

			showDialog(DIALOG_REG_MESSAGE);
			break;

		case R.id.btnReset:// 重置
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

//已读
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		AlertDialog dialog = null;
		switch (id) {
		case DIALOG_REG_MESSAGE:
			dialog = new Builder(this).setTitle("注册信息")
					.setIcon(R.drawable.ic_launcher).setMessage("ssss")
					.setPositiveButton("确定", null).create();
			break;

		case DIALOG_ABOUT:
			dialog = new Builder(this).setTitle("关于")
					.setIcon(android.R.drawable.ic_menu_info_details)
					.setMessage("关于本程序:老牛的一个程序").setCancelable(true)
					.setPositiveButton("确定", null).create();
			break;
		}
		return dialog;
	}

//已读
	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		// TODO Auto-generated method stub
		if (id == DIALOG_REG_MESSAGE) {
			Log.i("info", "msg=" + msg);
			((AlertDialog) dialog).setMessage(msg);
		}
		super.onPrepareDialog(id, dialog);
	}

	//已读
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
		MenuItem item = menu.add(1, MENU_OPTS_ABOUT, 2, "关于");
		item.setIcon(android.R.drawable.ic_menu_info_details);

		menu.add(1, MENU_OPTS_EXIT, 1, "退出").setIcon(
				android.R.drawable.ic_menu_close_clear_cancel);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case MENU_OPTS_ABOUT:// 关于
			showDialog(DIALOG_ABOUT);
			break;

		case MENU_OPTS_EXIT:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}