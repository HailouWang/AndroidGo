package com.github.hailouwang.demosforapi.proxy;

import android.util.Log;

public class OperateImpl implements Operate {
    @Override
    public void operateMethod1() {
        Log.d("hlwang","OperateImpl operateMethod1 ");
        sleep(1000);
    }

    @Override
    public void operateMethod2() {
        Log.d("hlwang","OperateImpl operateMethod2 ");
        sleep(1000);
    }

    @Override
    public void operateMethod3() {
        Log.d("hlwang","OperateImpl operateMethod3 ");
        sleep(1000);
    }

    private static void sleep(long millSeconds) {
        try {
            Thread.sleep(millSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
