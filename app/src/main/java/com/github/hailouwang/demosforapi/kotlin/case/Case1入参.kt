package com.github.hailouwang.demosforapi.kotlin.case

class Case1入参 {
    /**
     * Kotlin 入参是常量
     */
    fun print(a: Int = 1, b: String = "") {
        // a = 10; // 错误：Val cannot be reassigned!!!
    }
}