package com.github.hailouwang.demosforapi.drawable;

import android.graphics.drawable.ScaleDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.R;

/**
 *
 * 对另一个drawable资源，基于当前的level，进行尺寸变换的drawable。
 *
 *解析成：ScaleDrawable
 *
 *
 * 语法：
 *
 <?xml version="1.0" encoding="utf-8"?>
 <scale
 xmlns:android="http://schemas.android.com/apk/res/android"
 android:drawable="@drawable/drawable_resource"
 android:scaleGravity=["top" | "bottom" | "left" | "right" | "center_vertical" |
 "fill_vertical" | "center_horizontal" | "fill_horizontal" |
 "center" | "fill" | "clip_vertical" | "clip_horizontal"]
 android:scaleHeight="percentage"
 android:scaleWidth="percentage" />


 * 说明：
 *
 *
 <scale>
 定义一个ScaleDrawable，必须作为根元素。
 属性：
 xmlns:android
 String类型。 必须的。定义XML文件的命名空间。必须是 "http://schemas.android.com/apk/res/android".
 android:drawable
 Drawable 资源。必须的。引用一个drawable资源。
 android:scaleGravity
 关键字。指定缩放后的gravity的位置。
 必须是下面的一个或多个值（多个值之间用”|“分隔），下面的值和描述和上一篇的ClipDrawable一样。
 值	描述
 top	Put the object at the top of its container, not changing its size.
 bottom	Put the object at the bottom of its container, not changing its size.
 left	Put the object at the left edge of its container, not changing its size. This is thedefault.
 right	Put the object at the right edge of its container, not changing its size.
 center_vertical	Place object in the vertical center of its container, not changing its size.
 fill_vertical	Grow the vertical size of the object if needed so it completely fills its container.
 center_horizontal	Place object in the horizontal center of its container, not changing its size.
 fill_horizontal	Grow the horizontal size of the object if needed so it completely fills its container.
 center	Place the object in the center of its container in both the vertical and horizontal axis, notchanging its size.
 fill	Grow the horizontal and vertical size of the object if needed so it completely fills itscontainer.
 clip_vertical	Additional option that can be set to have the top and/or bottom edges of the child clipped toits container's bounds. The clip is based on the vertical gravity: a top gravity clips thebottom edge, a bottom gravity clips the top edge, and neither clips both edges.
 clip_horizontal	Additional option that can be set to have the left and/or right edges of the child clipped toits container's bounds. The clip is based on the horizontal gravity: a left gravity clipsthe right edge, a right gravity clips the left edge, and neither clips both edges.
 android:scaleHeight
 Percentage（百分比）缩放的高度，以百分比的方式表示drawable的缩放。形式例如：100%，12.5%。
 android:scaleWidth
 Percentage（百分比）缩放的宽度，以百分比的方式表示drawable的缩放。形式例如：100%，12.5%。






 */
public class ScaleDrawableTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scale_drawable);

        //normal
        final ImageView imageView01 = (ImageView) findViewById(R.id.imageView_01);

        //xml方式
        final ImageView imageView02 = (ImageView) findViewById(R.id.imageView_02);
        final ScaleDrawable scaleDrawable2 = (ScaleDrawable) imageView02.getDrawable();
        scaleDrawable2.setLevel(1);

        //代码方式
        ScaleDrawable scaleDrawable3 = new ScaleDrawable(getDrawable(R.drawable.android_system_logo),
                Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL,-0.5f,-0.5f);//scaleHeight 大于1，则设定为1.小于0，设定为1，即不缩放。

        final ImageView imageView03 = (ImageView) findViewById(R.id.imageView_03);
        imageView03.setImageDrawable(scaleDrawable3);
        scaleDrawable3.setLevel(1);//大于1：可见   0：不可见
    }
}
