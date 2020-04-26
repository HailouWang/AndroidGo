package com.github.hailouwang.demosforapi.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @Retention(RetentionPolicy.CLASS) // CLASS 文档中保留，RUNTIME 中不可用
@Retention(RetentionPolicy.RUNTIME) // CLASS 文档中保留，RUNTIME可使用
@Target(ElementType.TYPE) // 作用域
@Documented
@Inherited
public @interface MyTestAnnotation {
    // 方法 , Repeatable 要求这里是数组
    RepeatableAnnotation[] value();

    String name() default "";
}
