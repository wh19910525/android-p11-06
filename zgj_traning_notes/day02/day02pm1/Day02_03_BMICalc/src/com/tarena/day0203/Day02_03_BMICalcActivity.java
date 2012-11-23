package com.tarena.day0203;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Day02_03_BMICalcActivity extends Activity {
	private EditText etHeight, etWeight;

	private void setupView() {
		etHeight = (EditText) findViewById(R.id.etHeight);
		etWeight = (EditText) findViewById(R.id.etWeight);
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ����Activity��������ͼ
		setContentView(R.layout.main);

		setupView();
	}

	private boolean isEmpty(EditText et) {
		if (et.getText() == null || "".equals(et.getText().toString().trim())) {
			return true;
		}

		return false;
	}

	public void doClick(View v)//�� layout��xml�ﶨ��,���� xml�ﶨ���� ���������� ������ û�� д ��� ����������ô�������ʱ�� �� �����Ƴ� Ӧ�� ����;
	{
		switch (v.getId()) {
		
		case R.id.btnReset:
			etWeight.setText("");
			etHeight.setText("");
			break;
		}
		
	}
	public void whClick(View v) {//������ ���� �������� ������������  xml�����ļ��� ����, ������������ �Լ���;
		switch (v.getId()) {
		case R.id.btnCalc:
			// ��֤��Ч��
			if (isEmpty(etHeight)) {
				etHeight.setError("��߲���Ϊ��");
				return;
			}
			if (isEmpty(etWeight)) {
				etWeight.setError("���ز���Ϊ��");
				return;
			}
			// ��ȡ����
			double h = Double.parseDouble(etHeight.getText().toString());
			double w = Double.parseDouble(etWeight.getText().toString());
			// ����
			double bmi = w * 10000 / h / h;
			// ������
			if (bmi < 18) {
				Toast.makeText(this, "�������ƫ�ݣ����ǿӪ��", 3000).show();
			} else if (bmi < 25) {
				Toast.makeText(this, "������ı�׼�����������", 3000).show();
			} else if (bmi < 30) {
				Toast.makeText(this, "�������ƫ�֣����ǿ����", 3000).show();
			} else if (bmi < 35) {
				Toast.makeText(this, "���������ȷ��֣����ǿ����", 3000).show();
			} else if (bmi < 40) {
				Toast.makeText(this, "��������жȷ��֣����ǿ����", 3000).show();
			} else if (bmi < 45) {
				Toast.makeText(this, "����������ط��֣����ǿ����", 3000).show();
			} else {
				Toast.makeText(this, "������Ĺ����ˣ����ǿ����", 3000).show();
			}
		case R.id.btnReset:
			etWeight.setText("");
			etHeight.setText("");
			break;
		}
	}
}