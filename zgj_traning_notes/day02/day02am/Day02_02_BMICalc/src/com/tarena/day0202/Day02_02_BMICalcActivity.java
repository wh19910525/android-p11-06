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
	 * 控件单击事件的处理方法
	 */
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btnCalc:
			// 验证有效性
			if (isEmpty(etHeight)) {
				etHeight.setError("身高不能为空");
				return;
			}
			if (isEmpty(etWeight)) {
				etWeight.setError("体重不能为空");
				return;
			}
			// 获取输入
			double h = Double.parseDouble(etHeight.getText().toString());
			double w = Double.parseDouble(etWeight.getText().toString());
			// 计算
			double bmi = w * 10000 / h / h;
			// 输出结果
			if (bmi < 18) {
				Toast.makeText(this, "您的身材偏瘦，请加强营养", 3000).show();
			} else if (bmi < 25) {
				Toast.makeText(this, "您的身材标准，请继续保持", 3000).show();
			} else if (bmi < 30) {
				Toast.makeText(this, "您的身材偏胖，请加强锻炼", 3000).show();
			} else if (bmi < 35) {
				Toast.makeText(this, "您的身材轻度肥胖，请加强锻炼", 3000).show();
			} else if (bmi < 40) {
				Toast.makeText(this, "您的身材中度肥胖，请加强锻炼", 3000).show();
			} else if (bmi < 45) {
				Toast.makeText(this, "您的身材严重肥胖，请加强锻炼", 3000).show();
			} else {
				Toast.makeText(this, "您的身材过分了，请加强锻炼", 3000).show();
			}
		case R.id.btnReset:
			etWeight.setText("");
			etHeight.setText("");
			break;
		}
	}

	private void setupView() {
	
		// 从xml文件扩充一个View对象,加载 布局
		root = inflater.inflate(R.layout.main, null);//
		
		etHeight = (EditText) root.findViewById(R.id.etHeight);
		etWeight = (EditText) root.findViewById(R.id.etWeight);
		btnCalc = (Button) root.findViewById(R.id.btnCalc);
		btnReset = (Button) root.findViewById(R.id.btnReset);		
		// 设置为Activity的内容视图
		setContentView(root);//这样 可以放在上面，也可以放在 下面


		/*
		setContentView(R.layout.main);//只能 放在 控件 之前
		// 从根容器中根据id 查找各个控件
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