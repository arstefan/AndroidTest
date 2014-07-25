/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\workspace_android\\KONSOLA_PRJ_2\\src\\com\\zinga\\stockportfolio\\service\\IStockService.aidl
 */
package com.zinga.stockportfolio.service;
public interface IStockService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.zinga.stockportfolio.service.IStockService
{
private static final java.lang.String DESCRIPTOR = "com.zinga.stockportfolio.service.IStockService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.zinga.stockportfolio.service.IStockService interface,
 * generating a proxy if needed.
 */
public static com.zinga.stockportfolio.service.IStockService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.zinga.stockportfolio.service.IStockService))) {
return ((com.zinga.stockportfolio.service.IStockService)iin);
}
return new com.zinga.stockportfolio.service.IStockService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
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
case TRANSACTION_addToPortfolio:
{
data.enforceInterface(DESCRIPTOR);
com.zinga.stockportfolio.Stock _arg0;
if ((0!=data.readInt())) {
_arg0 = com.zinga.stockportfolio.Stock.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
com.zinga.stockportfolio.Stock _result = this.addToPortfolio(_arg0);
reply.writeNoException();
if ((_result!=null)) {
reply.writeInt(1);
_result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
case TRANSACTION_getPortfolio:
{
data.enforceInterface(DESCRIPTOR);
java.util.List<com.zinga.stockportfolio.Stock> _result = this.getPortfolio();
reply.writeNoException();
reply.writeTypedList(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.zinga.stockportfolio.service.IStockService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public com.zinga.stockportfolio.Stock addToPortfolio(com.zinga.stockportfolio.Stock stock) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.zinga.stockportfolio.Stock _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((stock!=null)) {
_data.writeInt(1);
stock.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_addToPortfolio, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
_result = com.zinga.stockportfolio.Stock.CREATOR.createFromParcel(_reply);
}
else {
_result = null;
}
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List<com.zinga.stockportfolio.Stock> getPortfolio() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List<com.zinga.stockportfolio.Stock> _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getPortfolio, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArrayList(com.zinga.stockportfolio.Stock.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_addToPortfolio = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getPortfolio = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public com.zinga.stockportfolio.Stock addToPortfolio(com.zinga.stockportfolio.Stock stock) throws android.os.RemoteException;
public java.util.List<com.zinga.stockportfolio.Stock> getPortfolio() throws android.os.RemoteException;
}
