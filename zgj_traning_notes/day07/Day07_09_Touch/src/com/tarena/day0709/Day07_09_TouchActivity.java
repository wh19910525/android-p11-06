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
        //创建一个MyTextView控件
        MyTextView tv = new MyTextView(this);
        LayoutParams params = new LayoutParams(300,100);//有什么用
        tv.setLayoutParams(params);
        tv.setBackgroundColor(Color.RED);
        //LinearLayout test = new LinearLayout(this);
        //test.addView(tv);
        
        //将控件添加到activity的内容区
        setContentView(tv);//直接 显示 textview 为什么是全屏？ 
        //setContentView(test);
    }
}