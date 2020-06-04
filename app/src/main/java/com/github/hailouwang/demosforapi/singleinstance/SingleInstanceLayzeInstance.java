package com.github.hailouwang.demosforapi.singleinstance;

/**
 * 1、懒式，线程安全与非安全两种写法
 */
public class SingleInstanceLayzeInstance {

    // 私有构造，单例模式专有
    private SingleInstanceLayzeInstance() {

    }

    private static SingleInstanceLayzeInstance INSTANCE = null;

    /**
     * 非线程安全
     * @return
     */
    public static SingleInstanceLayzeInstance getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SingleInstanceLayzeInstance();
        }

        return INSTANCE;
    }

    public static synchronized SingleInstanceLayzeInstance getInstanceSync() {
        if (INSTANCE == null) {
            INSTANCE = new SingleInstanceLayzeInstance();
        }

        return INSTANCE;
    }
}
