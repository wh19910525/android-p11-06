package com.tarena.day0709;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;

public class Day07_09_TouchActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //����һ��MyTextView�ؼ�
        MyTextView tv = new MyTextView(this);
        LayoutParams params = new LayoutParams(300,100);//��ʲô��
        tv.setLayoutParams(params);
        tv.setBackgroundColor(Color.RED);
        //LinearLayout test = new LinearLayout(this);
        //test.addView(tv);
        
        //���ؼ���ӵ�activity��������
        setContentView(tv);//ֱ�� ��ʾ textview Ϊʲô��ȫ���� 
        //setContentView(test);
    }
}