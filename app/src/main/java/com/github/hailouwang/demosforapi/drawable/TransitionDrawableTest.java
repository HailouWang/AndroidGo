package com.github.hailouwang.demosforapi.drawable;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.R;

/**
 *
 * 一个TransitionDrawable是一个特殊的Drawable对象，可以实现两个drawable资源之间淡入淡出的效果。
 * <transition>节点下的每个<item>代表一个drawable资源。只能有两个<item>。先前转换调用startTransition()。
 * 向后，调用 reverseTransition()。
 *
 *解析成：TransitionDrawable
 *
 *
 * 语法：
 *
 <?xml version="1.0" encoding="utf-8"?>
 <transition
 xmlns:android="http://schemas.android.com/apk/res/android" >
 <item
 android:drawable="@[package:]drawable/drawable_resource"
 android:id="@[+][package:]id/resource_name"
 android:top="dimension"
 android:right="dimension"
 android:bottom="dimension"
 android:left="dimension" />
 </transition>



 * 说明：
 *
 *
 <transition>
 必须的。  必须作为根节点，包含一个或多个<item>元素。
 属性：
 xmlns:android
 字符串类型，必须的。定义XML文件的命名空间，必须是 "http://schemas.android.com/apk/res/android".
 <item>
 定义一个TransitionDrawable中所使用的一个drawable。必须是<transition>子节点。可以接受<bitmap>子节点。
 属性：
 android:drawable
 Drawable 资源。 必须的。引用一个Drawable资源。
 android:id
 资源ID。drawable资源的唯一标识。使用"@+id/name"方式来给这个item定义一个新的资源ID。可以使用View.findViewById() 或者 Activity.findViewById()等方式检索和修改这个item。
 android:top
 Integer。 与顶部的距离
 android:right
 Integer。与右边的距离
 android:bottom
 Integer。 与下边的距离
 android:left
 Integer。与左边的距离






 */
public class TransitionDrawableTest extends AppCompatActivity {

    boolean isTansition01 = false;
    boolean isTansition02 = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_drawable);

        //xml方式
        final ImageView imageView01 = (ImageView) findViewById(R.id.imageView01);
        final TransitionDrawable drawable = (TransitionDrawable) imageView01.getDrawable();
        imageView01.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                isTansition01 = !isTansition01;
                if(isTansition01){
                    drawable.startTransition(500);
                }else{
                    drawable.reverseTransition(500);
                }
            }
        });

        //代码方式
        final TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{
                getResources().getDrawable(R.drawable.stat_sad,getTheme()),
                getResources().getDrawable(R.drawable.stat_happy,getTheme()),
        });

        final ImageView imageView02 = (ImageView) findViewById(R.id.imageView02);
        imageView02.setImageDrawable(transitionDrawable);
        imageView02.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                isTansition02 = !isTansition02;
                if(isTansition02){
                    transitionDrawable.startTransition(500);
                }else{
                    transitionDrawable.resetTransition();
                }
            }
        });
    }
}
