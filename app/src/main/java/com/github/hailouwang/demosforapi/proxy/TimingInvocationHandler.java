package com.github.hailouwang.demosforapi.proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TimingInvocationHandler implements InvocationHandler {

    private Object target;

    public TimingInvocationHandler() {
    }

    public TimingInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = method.invoke(target, args);
        Log.d("hlwang", " cost time is:" + (System.currentTimeMillis() - start));
        return obj;
    }
}