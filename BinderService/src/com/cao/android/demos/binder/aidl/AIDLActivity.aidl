package com.cao.android.demos.binder.aidl;  
import com.cao.android.demos.binder.aidl.Rect1;//调用 Rect1 这个 aidl接口;
interface AIDLActivity {   
    void performAction(in Rect1 rect);   
}  
