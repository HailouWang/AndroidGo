/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package com.example.android.apis.app;
/**
 * Example of defining an interface for calling on to a remote service
 * (running in another process).
 */
public interface IRemoteService extends android.os.IInterface
{
  /** Default implementation for IRemoteService. */
  public static class Default implements com.example.android.apis.app.IRemoteService
  {
    /**
         * Often you want to allow a service to call back to its clients.
         * This shows how to do so, by registering a callback interface with
         * the service.
         */
    @Override public void registerCallback(com.example.android.apis.app.IRemoteServiceCallback cb) throws android.os.RemoteException
    {
    }
    /**
         * Remove a previously registered callback interface.
         */
    @Override public void unregisterCallback(com.example.android.apis.app.IRemoteServiceCallback cb) throws android.os.RemoteException
    {
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements com.example.android.apis.app.IRemoteService
  {
    private static final java.lang.String DESCRIPTOR = "com.example.android.apis.app.IRemoteService";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an com.example.android.apis.app.IRemoteService interface,
     * generating a proxy if needed.
     */
    public static com.example.android.apis.app.IRemoteService asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof com.example.android.apis.app.IRemoteService))) {
        return ((com.example.android.apis.app.IRemoteService)iin);
      }
      return new com.example.android.apis.app.IRemoteService.Stub.Proxy(obj);
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
        case TRANSACTION_registerCallback:
        {
          data.enforceInterface(descriptor);
          com.example.android.apis.app.IRemoteServiceCallback _arg0;
          _arg0 = com.example.android.apis.app.IRemoteServiceCallback.Stub.asInterface(data.readStrongBinder());
          this.registerCallback(_arg0);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_unregisterCallback:
        {
          data.enforceInterface(descriptor);
          com.example.android.apis.app.IRemoteServiceCallback _arg0;
          _arg0 = com.example.android.apis.app.IRemoteServiceCallback.Stub.asInterface(data.readStrongBinder());
          this.unregisterCallback(_arg0);
          reply.writeNoException();
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements com.example.android.apis.app.IRemoteService
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
           * Often you want to allow a service to call back to its clients.
           * This shows how to do so, by registering a callback interface with
           * the service.
           */
      @Override public void registerCallback(com.example.android.apis.app.IRemoteServiceCallback cb) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
          boolean _status = mRemote.transact(Stub.TRANSACTION_registerCallback, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().registerCallback(cb);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      /**
           * Remove a previously registered callback interface.
           */
      @Override public void unregisterCallback(com.example.android.apis.app.IRemoteServiceCallback cb) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeStrongBinder((((cb!=null))?(cb.asBinder()):(null)));
          boolean _status = mRemote.transact(Stub.TRANSACTION_unregisterCallback, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().unregisterCallback(cb);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      public static com.example.android.apis.app.IRemoteService sDefaultImpl;
    }
    static final int TRANSACTION_registerCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_unregisterCallback = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    public static boolean setDefaultImpl(com.example.android.apis.app.IRemoteService impl) {
      if (Stub.Proxy.sDefaultImpl == null && impl != null) {
        Stub.Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
    public static com.example.android.apis.app.IRemoteService getDefaultImpl() {
      return Stub.Proxy.sDefaultImpl;
    }
  }
  /**
       * Often you want to allow a service to call back to its clients.
       * This shows how to do so, by registering a callback interface with
       * the service.
       */
  public void registerCallback(com.example.android.apis.app.IRemoteServiceCallback cb) throws android.os.RemoteException;
  /**
       * Remove a previously registered callback interface.
       */
  public void unregisterCallback(com.example.android.apis.app.IRemoteServiceCallback cb) throws android.os.RemoteException;
}
