package com.cao.android.demos.binder.aidl;  
import com.cao.android.demos.binder.aidl.AIDLActivity;//���� AIDLActivity ��� aidl�ӿ�;
interface AIDLService {   
    void registerTestCall(AIDLActivity cb);   
    void invokCallBack();
}  
