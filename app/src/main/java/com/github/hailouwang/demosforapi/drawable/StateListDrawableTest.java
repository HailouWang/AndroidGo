package com.github.hailouwang.demosforapi.drawable;

import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.R;

/**
 *
 * Drawable 会解析成StateListDrawable
 *
 *
 *
 *
 * 语法：
 *
 <?xml version="1.0" encoding="utf-8"?>
 <selector xmlns:android="http://schemas.android.com/apk/res/android"
 android:constantSize=["true" | "false"]
 android:dither=["true" | "false"]
 android:variablePadding=["true" | "false"] >
 <item
 android:drawable="@[package:]drawable/drawable_resource"
 android:state_pressed=["true" | "false"]
 android:state_focused=["true" | "false"]
 android:state_hovered=["true" | "false"]
 android:state_selected=["true" | "false"]
 android:state_checkable=["true" | "false"]
 android:state_checked=["true" | "false"]
 android:state_enabled=["true" | "false"]
 android:state_activated=["true" | "false"]
 android:state_window_focused=["true" | "false"] />
 </selector>


 * 说明：
 *
 *
 <selector>：必须的，必须最为根元素，包含一个或多个<item>元素
 属性
 xmlns:android
 字符串。 必须的。定义命名空间，必须是 "http://schemas.android.com/apk/res/android"。
 android:constantSize
 布尔值。内部大小（所有状态中最大的那个）改变时是否报道。默认为false
 android:dither
 布尔值。如果位图与屏幕的像素配置不同时，是否允许抖动.（例如：一个位图的像素设置是 ARGB 8888，但屏幕的设置是RGB 565）
 android:variablePadding
 布尔值。默认为false，是否要进行绘图填充。（不明白）
 <item>
 为某个状态定义一个drawable，必须作为<selector>的子元素。
 属性:
 android:drawable
 必须的，指向一个drawable资源
 android:state_pressed
 Boolean。是否按下
 android:state_focused
 Boolean。是否获得获得焦点
 android:state_hovered
 Boolean。鼠标在上面滑动的状态。通常和state_focused使用同样的drawable
 api14后新增的
 android:state_selected
 Boolean。是否选中
 android:state_checkable
 Boolean。是否可以被勾选（checkable）。只能用在可以勾选的控件上
 android:state_checked
 Boolean。是否被勾选上
 android:state_enabled
 Boolean。是否可用
 android:state_activated
 Boolean。是否被激活并持久的选择
 api11后新增
 android:state_window_focused
 Boolean。当前应用程序是否获得焦点
 注意：Android系统将会选中第一条符合当前状态的item。。因此，如果第一项列表中包含了所有的状态属性，那么它是每次就只用他。这就是为什么你的默认值应该放在最后面。

 */
public class StateListDrawableTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statelist_drawable);

        //xml方式

        //代码方式
        StateListDrawable stateListDrawable = new StateListDrawable();
        //如果要设置莫项为false，在前面加负号 ，
        // 比如android.R.attr.state_focesed标志true，-android.R.attr.state_focesed就标志false
        stateListDrawable.addState(new int[]{android.R.attr.state_selected},
                getResources().getDrawable(R.drawable.stat_sad));
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed},
                getResources().getDrawable(R.drawable.stat_happy));
        stateListDrawable.addState(new int[]{},
                getResources().getDrawable(R.drawable.stat_neutral));//默认,必须在最后一位
//        stateListDrawable.addState(new int[]{android.R.attr.state_focused},
//                getResources().getDrawable(R.drawable.stat_sad,getTheme()));

        final Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setBackground(stateListDrawable);
    }
}
