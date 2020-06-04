package com.github.hailouwang.demosforapi.singleinstance;

/**
 * 1、饿汉式，线程安全
 *
 * 类装载机制的 初始化流程中，会先执行初始化流程
 */
public class SingleInstancePreInstance {

    // 私有构造，单例模式专有
    private SingleInstancePreInstance() {

    }

    private static final SingleInstancePreInstance INSTANCE = new SingleInstancePreInstance();

    public static SingleInstancePreInstance getInstance() {
        return INSTANCE;
    }
}
