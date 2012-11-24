package wh.zgj;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class Zgj_day02_t2Activity extends Activity implements OnClickListener{
	
	private LayoutInflater inflater;
	
	private EditText etWeight, etHeight;
	private Button btCal, btReset;
	private View root;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        inflater = LayoutInflater.from(this);
        setupView();
        addListeners();
    }
    
	private void setupView()
	{
		root = inflater.inflate(R.layout.main, null);
		
		//第一行		
		etHeight = (EditText)root.findViewById(R.id.etHeight);
			
		//第二行		
		etWeight = (EditText)root.findViewById(R.id.etWeight);
		etWeight.setInputType(InputType.TYPE_CLASS_NUMBER);
		
		//第三行
		btCal = (Button)root.findViewById(R.id.btCal);	
		btReset = (Button)root.findViewById(R.id.btReset);
		
		setContentView(root);
		
	} 
	private void addListeners()
	{
		btCal.setOnClickListener(this);
		btReset.setOnClickListener(this);
	}
	
	private boolean isEmpty(EditText et) {
		if (et.getText() == null || "".equals(et.getText().toString().trim())) {
			return true;
		}
		return false;
	}
	
	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.btCal:
			if(isEmpty(etHeight))
			{
				etHeight.setError("身高 不能 为空");
				return;
			}
			
			if (isEmpty(etWeight)) {
				etWeight.setError("体重不能为空");
				return;
			}
			
			double h = Double.parseDouble(etHeight.getText().toString());
			double w = Double.parseDouble(etWeight.getText().toString());
			
			// 计算
			double bmi = w * 10000 / h / h;
			
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
						
		case R.id.btReset:
			etWeight.setText("");
			etHeight.setText("");
			break;
		}
	}
    
}