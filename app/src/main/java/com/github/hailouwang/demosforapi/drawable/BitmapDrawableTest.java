package com.github.hailouwang.demosforapi.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.R;

/**
 *
 * <?xml version="1.0" encoding="utf-8"?>
 <bitmap
 xmlns:android="http://schemas.android.com/apk/res/android"
 android:src="@[package:]drawable/drawable_resource"
 android:antialias=["true" | "false"]
 android:dither=["true" | "false"]
 android:filter=["true" | "false"]
 android:gravity=["top" | "bottom" | "left" | "right" | "center_vertical" |
 "fill_vertical" | "center_horizontal" | "fill_horizontal" |
 "center" | "fill" | "clip_vertical" | "clip_horizontal"]
 android:tileMode=["disabled" | "clamp" | "repeat" | "mirror"] />



 <bitmap>
 定义位图的来源和属性
 属性:
 xmlns:android
 类型:String。定义了XML的命名空间，必须是"http://schemas.android.com/apk/res/android"。如果<bitmap>是根元素，那么他是必须的，如果是嵌套在<itme>里面，那么就不是必须的。
 android:src
 类型：Drawable resource。必需。 引用一个drawableresource.
 android:antialias
 类型：Boolean。是否开启抗锯齿。
 android:dither
 类型：Boolean。如果位图与屏幕的像素配置不同时，是否允许抖动.（例如：一个位图的像素设置是 ARGB 8888，但屏幕的设置是RGB 565）
 android:filter
 类型：Boolean。是否允许对位图进行滤波。对位图进行收缩或者延展使用滤波可以获得平滑的外观效果。
 android:gravity
 类型：关键字。定义位图的重力（gravity），如果位图小于其容器，使用重力指明在何处绘制
 必需是下面的属性，多个之间用  |  分隔
 Value	Description
 top	Put the object at the top of its container, not changing its size.
 bottom	Put the object at the bottom of its container, not changing its size.
 left	Put the object at the left edge of its container, not changing its size.
 right	Put the object at the right edge of its container, not changing its size.
 center_vertical	Place object in the vertical center of its container, not changing its size.
 fill_vertical	Grow the vertical size of the object if needed so it completely fills its container.
 center_horizontal	Place object in the horizontal center of its container, not changing its size.
 fill_horizontal	Grow the horizontal size of the object if needed so it completely fills its container.
 center	Place the object in the center of its container in both the vertical and horizontal axis, notchanging its size.
 fill	Grow the horizontal and vertical size of the object if needed so it completely fills itscontainer. This is the default.
 clip_vertical	Additional option that can be set to have the top and/or bottom edges of the child clipped toits container's bounds. The clip is based on the vertical gravity: a top gravity clips thebottom edge, a bottom gravity clips the top edge, and neither clips both edges.
 clip_horizontal	Additional option that can be set to have the left and/or right edges of the child clipped toits container's bounds. The clip is based on the horizontal gravity: a left gravity clipsthe right edge, a right gravity clips the left edge, and neither clips both edges.
 android:tileMode
 类型：Keyword。
 定义了tile模式。当tile模式被启用，位图是重复的，并且gravity属性将被忽略。

 必须是下列之一常量值：
 Value	Description
 disabled	Do not tile the bitmap. This is the default value.
 clamp	Replicates the edge color if the shader draws outside of its original bounds
 repeat	Repeats the shader's image horizontally and vertically.
 mirror	Repeats the shader's image horizontally and vertically, alternating mirror images so thatadjacent images always seam.
 示例：
 */
public class BitmapDrawableTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap_drawable);

        TextView tv_code = (TextView) findViewById(R.id.code_textview);
        Drawable drawable = getResources().getDrawable(R.drawable.tile_mode_test_disable);
        boolean isBitmapDrawable = drawable instanceof BitmapDrawable;
        String tv_code_value = "代码获取Bitmap,drawable == BitmapDrawable?"+isBitmapDrawable;
        tv_code.setText(tv_code_value);
        ImageView imageViewFromCode = (ImageView) findViewById(R.id.iv_code);
        imageViewFromCode.setImageDrawable(drawable);


        Bitmap bitmapFromFactory1 = BitmapFactory.decodeResource(getResources(),R.drawable.android_system_logo);
        ImageView imageViewBitmapFactory1 = (ImageView) findViewById(R.id.iv_bitmapfactory1);
        imageViewBitmapFactory1.setImageBitmap(bitmapFromFactory1);


        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inScaled = false;
        Bitmap bitmapFromFactory2 = BitmapFactory.decodeResource(getResources(),R.drawable.android_system_logo,opts);
        ImageView imageViewBitmapFactory2 = (ImageView) findViewById(R.id.iv_bitmapfactory2);
        imageViewBitmapFactory2.setImageBitmap(bitmapFromFactory2);

    }
}
