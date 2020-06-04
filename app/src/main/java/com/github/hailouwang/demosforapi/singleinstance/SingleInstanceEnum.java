package com.github.hailouwang.demosforapi.singleinstance;

/**
 * 线程安全
 */
public enum SingleInstanceEnum {
    INSTANCE;

    private SingleInstanceEnum() {

    }
}
