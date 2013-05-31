/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: I:\\android-p11-06\\BinderService\\src\\com\\cao\\android\\demos\\binder\\aidl\\AIDLService.aidl
 */
package com.cao.android.demos.binder.aidl;
//���� AIDLActivity ��� aidl�ӿ�;

public interface AIDLService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.cao.android.demos.binder.aidl.AIDLService
{
private static final java.lang.String DESCRIPTOR = "com.cao.android.demos.binder.aidl.AIDLService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.cao.android.demos.binder.aidl.AIDLService interface,
 * generating a proxy if needed.
 */
public static com.cao.android.demos.binder.aidl.AIDLService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.cao.android.demos.binder.aidl.AIDLService))) {
return ((com.cao.android.demos.binder.aidl.AIDLService)iin);
}
return new com.cao.android.demos.binder.aidl.AIDLService.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_registerTestCall:
{
data.enforceInterface(DESCRIPTOR);
com.cao.android.demos.binder.aidl.AIDLActivity _arg0;
_arg0 = com.cao.android.demos.binder.aidl.AIDLActivity.Stub.asInterface(data.readStrongBinder());
this.registerTestCall(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_invokCallBack:
{
data.enforceInterface(DESCRIPTOR);
this.invokCallBack();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.cao.android.demos.binder.aidl.AIDLService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public void registerTestCall(com.cao.android.demos.binder.aidl.AIDLActivity cb) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
mRemote.transact(Stub.TRANSACTION_registerTestCall, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void invokCallBack() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_invokCallBack, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_registerTestCall = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_invokCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public void registerTestCall(com.cao.android.demos.binder.aidl.AIDLActivity cb) throws android.os.RemoteException;
public void invokCallBack() throws android.os.RemoteException;
}
