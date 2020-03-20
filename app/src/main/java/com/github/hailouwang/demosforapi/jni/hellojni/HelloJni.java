/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.hailouwang.demosforapi.jni.hellojni;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.R;

/**
 * 一、JNI层是桥梁，连通 JAVA层和C/C++层实现
 *
 * 二、使用：
 * 1、Java层通过System.loadLibrary来加载类库，声明native修饰的方法接口。
 * 2、JNI层：方法关联。
 * 关联方式一：静态注册：javac、javah。javah -o output packagename.classname.class;javah -jni packagename.classname.class
 * 关联方式二：动态注册：JNINativeMethod，javaap -s -p class可以得到方法签名信息
 * 3、C/C++层：
 *
 * 三、核心实现：
 * JNIEnv：调用Java函数，操作object对象。
 *
 * 获取方法ID：GetMethodID
 * 调用非静态方法：CallVoidethod
 * 静态方法：CallStatic<Type>Method
 *
 * 获取成员变量：GetFieldID
 * 获取：Get<Type>Field
 * 设定：Set<Type>Field
 *
 *
 * 对应关系：
 * 1、每个进程对应一个JVM
 * 2、线程得到JNIEnv通过JVM.AttachCurrentThread来获取
 *
 * 四、垃圾回收&异常处理
 *
 * 全局引用：env->NewGlobalRef、env->DeleteGlobalRef
 * 本地引用：env->NewStringUTF、env->DeleteLocalRef
 *
 * ThrowNew用来向Java层抛出异常
 * ExceptionClear：清除异常
 *
 *
 */
public class HelloJni extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
         * 通过获取native方法来设定给TextView
         */
        setContentView(R.layout.activity_hello_jni);
        TextView tv = (TextView)findViewById(R.id.hello_textview);
        tv.setText( stringFromJNI() );
    }
    /*
     * native方法，由'hello-jni'来实现
     */
    public native String  stringFromJNI();

    /*
     *
     * native方法，'hello-jni'不实现它，但依旧可以声明这样的方法，但调用会抛出异常。
     * java.lang.UnsatisfiedLinkError exception !
     */
    public native String  unimplementedStringFromJNI();

    /*
     * 当应用程序启动时，加载'hello-jni'类库，类库会在应用程序安装时，放置在
     * /data/data/com.github.hailouwang.demosforapi.jni.hellojni/lib/libhello-jni.so
     */
    static {
        System.loadLibrary("hello-jni");
    }
}
