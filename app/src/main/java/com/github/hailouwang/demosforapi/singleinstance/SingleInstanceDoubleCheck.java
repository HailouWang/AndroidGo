package com.github.hailouwang.demosforapi.singleinstance;

/**
 * 线程安全
 * <p>
 * 但是如果方法的构造方法中，逻辑过重，同步引起的性能开销就会过大
 */
public class SingleInstanceDoubleCheck {

    private static volatile SingleInstanceDoubleCheck INSTANCE;

    private SingleInstanceDoubleCheck() {

    }

    public static SingleInstanceDoubleCheck getInstance() {
        if (INSTANCE == null) {
            synchronized (SingleInstanceStaticSubClass.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SingleInstanceDoubleCheck();
                }
            }
        }

        return INSTANCE;
    }
}
