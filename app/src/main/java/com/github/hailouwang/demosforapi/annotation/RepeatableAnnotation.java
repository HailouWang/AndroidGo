package com.github.hailouwang.demosforapi.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // CLASS 文档中保留，RUNTIME可使用
@Target(ElementType.TYPE) // 作用域
@Documented
@Inherited
@Repeatable(MyTestAnnotation.class) // 注解中的属性
public @interface RepeatableAnnotation {
    String valueContent() default "";
}
