package com.cao.android.demos.binder.aidl;  
import com.cao.android.demos.binder.aidl.AIDLActivity;//调用 AIDLActivity 这个 aidl接口;
interface AIDLService {   
    void registerTestCall(AIDLActivity cb);   
    void invokCallBack();
}  
