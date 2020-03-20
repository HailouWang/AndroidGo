package com.github.hailouwang.demosforapi.drawable;

import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.R;

/**
 *
 * InsetDrawable 表示一个drawable嵌入到另外一个drawable内部，并且在内部留一些间距，
 * 这一点很像drawable的padding属性，区别在于 padding表示drawable的内容与drawable本身的边距，
 * insetDrawable表示两个drawable和容器之间的边距。当控件需要的背景比实际的边框小的时候比较适合使用InsetDrawable。
 *
 *解析成：InsetDrawable
 *
 *
 * 语法：
 *
 <?xml version="1.0" encoding="utf-8"?>
 <inset
 xmlns:android="http://schemas.android.com/apk/res/android"
 android:drawable="@drawable/drawable_resource"
 android:insetTop="dimension"
 android:insetRight="dimension"
 android:insetBottom="dimension"
 android:insetLeft="dimension" />


 * 说明：
 *
 *
 <inset>
 定义这个drawable为InsetDrawable，必须作为根节点。
 属性：
 xmlns:android
 String类型。必须的，定义XML文件的命名空间，必须是 "http://schemas.android.com/apk/res/android".
 android:drawable
 Drawable 资源 。必须的。引用一个drawable资源
 android:insetTop
 尺寸。与顶部的距离。可以使一个尺寸值，或者一个尺寸的资源。
 android:insetRight
 尺寸。与右边的距离。可以使一个尺寸值，或者一个尺寸的资源。
 android:insetBottom
 尺寸。与底部的距离。可以使一个尺寸值，或者一个尺寸的资源。
 android:insetLeft
 尺寸。与左边的距离。可以使一个尺寸值，或者一个尺寸的资源。






 */
public class InsetDrawableTest extends AppCompatActivity {

    boolean isTansition01 = false;
    boolean isTansition02 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inset_drawable);

        //xml方式


        //代码方式
        InsetDrawable insetDrawable = new InsetDrawable(getDrawable(R.drawable.msg_bubble_outgoing),150,40,50,40);
        Button btn2 = (Button) findViewById(R.id.btn02);
        btn2.setBackground(insetDrawable);
    }
}
