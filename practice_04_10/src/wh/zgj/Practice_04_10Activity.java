package wh.zgj;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class Practice_04_10Activity extends Activity {
	
	TextView m_TextView;
	DatePicker m_DatePicker;
	TimePicker m_TimePicker;
	Button m_tButton;
	Button m_dButton;
	
	//java中的 Calendar 类
	Calendar c;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    
        c = Calendar.getInstance();
    
        m_TextView = (TextView)findViewById(R.id.tv);
        m_tButton = (Button)findViewById(R.id.bt);
        m_dButton = (Button)findViewById(R.id.bd);
        
        m_DatePicker = (DatePicker)findViewById(R.id.dp);
        m_TimePicker = (TimePicker)findViewById(R.id.tp);
        
        //将日历 初始化 为当前系统时间，并设置 其事件 监听
        m_DatePicker.init(c.get(Calendar.YEAR), c.get(Calendar.MONTH), 
        		c.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
			
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				//当日期更改时，在这里处理,但我不知道如何处理
				//c.set(year, monthOfYear, dayOfMonth);
			}
		});
        
        //设置为24小时制显示
        m_TimePicker.setIs24HourView(true);
        
      //监听时间改变
        m_TimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
			
			@Override
			public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
				// TODO Auto-generated method stub
				//时间改变时处理，但我 不知道 如何 处理
				//c.set(year, month, day, hourOfDay, minute, second);
			}
		});
        
        m_dButton.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				new DatePickerDialog(Practice_04_10Activity.this, new DatePickerDialog.OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						// TODO Auto-generated method stub
						//设置日历，但我不知道如何设置
					}
				}, 2008, 10, 05).show();
			}
        	
        });
        
        m_tButton.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new TimePickerDialog(Practice_04_10Activity.this, new TimePickerDialog.OnTimeSetListener() {
					
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						// TODO Auto-generated method stub
						//设置时间，但我不知道如何设置
					}
				}, c.get(Calendar.HOUR_OF_DAY), 35, true).show();
			}
        	
        } );
        
    }


}

