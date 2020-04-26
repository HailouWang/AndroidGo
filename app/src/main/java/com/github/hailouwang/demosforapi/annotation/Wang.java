package com.github.hailouwang.demosforapi.annotation;


@MyTestAnnotation(value = {
        @RepeatableAnnotation(valueContent = "有车"),
        @RepeatableAnnotation(valueContent = "有房"),
        @RepeatableAnnotation(valueContent = "有存款"),
        @RepeatableAnnotation(valueContent = "隔壁老王")}, name = "老王")
public class Wang {
}
