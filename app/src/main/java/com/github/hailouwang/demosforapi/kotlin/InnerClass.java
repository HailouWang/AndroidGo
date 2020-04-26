package com.github.hailouwang.demosforapi.kotlin;

public class InnerClass {
    static class Test{
        private static String a = "666";

        public static String getA(){
            return a;
        }
    }
}