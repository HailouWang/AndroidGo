package com.github.hailouwang.demosforapi.singleinstance;

/**
 * 线程安全
 */
public class SingleInstanceStaticSubClass {

    static class Helper {
        private static SingleInstanceStaticSubClass INSTANCE = new SingleInstanceStaticSubClass();
    }

    private SingleInstanceStaticSubClass() {

    }

    public static SingleInstanceStaticSubClass getInstance() {
        return Helper.INSTANCE;
    }
}
