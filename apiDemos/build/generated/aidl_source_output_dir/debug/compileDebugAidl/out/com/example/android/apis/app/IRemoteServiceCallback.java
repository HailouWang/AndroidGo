/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package com.example.android.apis.app;
/**
 * Example of a callback interface used by IRemoteService to send
 * synchronous notifications back to its clients.  Note that this is a
 * one-way interface so the server does not block waiting for the client.
 */
public interface IRemoteServiceCallback extends android.os.IInterface
{
  /** Default implementation for IRemoteServiceCallback. */
  public static class Default implements com.example.android.apis.app.IRemoteServiceCallback
  {
    /**
         * Called when the service has a new value for you.
         */
    @Override public void valueChanged(int value) throws android.os.RemoteException
    {
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements com.example.android.apis.app.IRemoteServiceCallback
  {
    private static final java.lang.String DESCRIPTOR = "com.example.android.apis.app.IRemoteServiceCallback";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an com.example.android.apis.app.IRemoteServiceCallback interface,
     * generating a proxy if needed.
     */
    public static com.example.android.apis.app.IRemoteServiceCallback asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof com.example.android.apis.app.IRemoteServiceCallback))) {
        return ((com.example.android.apis.app.IRemoteServiceCallback)iin);
      }
      return new com.example.android.apis.app.IRemoteServiceCallback.Stub.Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }
    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {
      java.lang.String descriptor = DESCRIPTOR;
      switch (code)
      {
        case INTERFACE_TRANSACTION:
        {
          reply.writeString(descriptor);
          return true;
        }
        case TRANSACTION_valueChanged:
        {
          data.enforceInterface(descriptor);
          int _arg0;
          _arg0 = data.readInt();
          this.valueChanged(_arg0);
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements com.example.android.apis.app.IRemoteServiceCallback
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
      /**
           * Called when the service has a new value for you.
           */
      @Override public void valueChanged(int value) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeInt(value);
          boolean _status = mRemote.transact(Stub.TRANSACTION_valueChanged, _data, null, android.os.IBinder.FLAG_ONEWAY);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().valueChanged(value);
            return;
          }
        }
        finally {
          _data.recycle();
        }
      }
      public static com.example.android.apis.app.IRemoteServiceCallback sDefaultImpl;
    }
    static final int TRANSACTION_valueChanged = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    public static boolean setDefaultImpl(com.example.android.apis.app.IRemoteServiceCallback impl) {
      if (Stub.Proxy.sDefaultImpl == null && impl != null) {
        Stub.Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
    public static com.example.android.apis.app.IRemoteServiceCallback getDefaultImpl() {
      return Stub.Proxy.sDefaultImpl;
    }
  }
  /**
       * Called when the service has a new value for you.
       */
  public void valueChanged(int value) throws android.os.RemoteException;
}
