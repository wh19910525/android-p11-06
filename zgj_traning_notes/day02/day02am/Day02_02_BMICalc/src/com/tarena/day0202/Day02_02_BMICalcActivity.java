package com.tarena.day0202;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Day02_02_BMICalcActivity extends Activity implements
		OnClickListener {
	private EditText etHeight, etWeight;
	private Button btnCalc, btnReset;
	private View root;//
	private LayoutInflater inflater;//

	private boolean isEmpty(EditText et) {
		if (et.getText() == null || "".equals(et.getText().toString().trim())) {
			return true;
		}

		return false;
	}

	private void addListeners() {
		btnCalc.setOnClickListener(this);
		btnReset.setOnClickListener(this);
	}

	/**
	 * �ؼ������¼��Ĵ�����
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub
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

	private void setupView() {
	
		// ��xml�ļ�����һ��View����,���� ����
		root = inflater.inflate(R.layout.main, null);//
		
		etHeight = (EditText) root.findViewById(R.id.etHeight);
		etWeight = (EditText) root.findViewById(R.id.etWeight);
		btnCalc = (Button) root.findViewById(R.id.btnCalc);
		btnReset = (Button) root.findViewById(R.id.btnReset);		
		// ����ΪActivity��������ͼ
		setContentView(root);//���� ���Է������棬Ҳ���Է��� ����


		/*
		setContentView(R.layout.main);//ֻ�� ���� �ؼ� ֮ǰ
		// �Ӹ������и���id ���Ҹ����ؼ�
		etHeight = (EditText) findViewById(R.id.etHeight);
		etWeight = (EditText) findViewById(R.id.etWeight);
		btnCalc = (Button) findViewById(R.id.btnCalc);
		btnReset = (Button) findViewById(R.id.btnReset);
*/


	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		inflater = LayoutInflater.from(this);
		setupView();
		addListeners();
	}
}