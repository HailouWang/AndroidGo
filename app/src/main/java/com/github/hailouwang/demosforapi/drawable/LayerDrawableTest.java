package com.github.hailouwang.demosforapi.drawable;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.R;

/**
 *
 * 一个LayerDrawable是一个可以管理一组drawable对象的drawable。
 * 在LayerDrawable的drawable资源按照列表的顺序绘制，列表的最后一个drawable绘制在最上层。
 * 它所包含的一组drawable资源用多个<item>元素表示，一个<item>元素代表一个drawable资源。
 *
 *
 * 语法：
 *
 <?xml version="1.0" encoding="utf-8"?>
 <layer-list
 xmlns:android="http://schemas.android.com/apk/res/android" >
 <item
 android:drawable="@[package:]drawable/drawable_resource"
 android:id="@[+][package:]id/resource_name"
 android:top="dimension"
 android:right="dimension"
 android:bottom="dimension"
 android:left="dimension" />
 </layer-list>




 <layer-list>
 必须的。 并且要作为根节点。 包含一个或者多个<item>元素
 属性：
 xmlns:android
 字符串。 必须的。 定义xml文件的命名空间，必须是 "http://schemas.android.com/apk/res/android"。
 <item>
 定义一个drawable放置在layer drawable中，具体的位置有它的。必须是<selector>的子元素（这个不太理解）。可接受<bitmap>做为子元素
 属性:
 android:drawable
 Drawable资源。必须的。引用的drawable资源
 android:id
 资源ID。一个为这个item定义的唯一的资源ID。 使用:"@+id/name".这样的方式。可以检索或修改这个drawable通过下面的方式：View.findViewById() orActivity.findViewById().
 android:top
 Integer。与top的距离，单位像素
 android:right
 Integer。与right的距离，单位像素
 android:bottom
 Integer。与bottom的距离，单位像素
 android:left
 Integer。与left的距离，单位像素
 在默认的情况下，所有的drawable item都会缩放到合适的大小来适应视图。因此，在一个layer-list中定义不同的位置可能会增加视图的尺寸和被自动缩放。为了避免被缩放，可以再<item>节点里加上<bitmap>元素来指定一个drawable，并且定义一些不会被拉伸的gravity属性，例如center。
 举个例子，下面在item里面定义一个drawable，图片就会自动缩放以适应视图的大小。
 <item android:drawable="@drawable/image" />
 为了避免缩放，可以使用<bitmap>的子元素来指定drawable资源
 <item>
 <bitmap android:src="@drawable/image"
 android:gravity="center" />
 </item>




 */
public class LayerDrawableTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layer_drawable);

        ImageView imageView1 = (ImageView) findViewById(R.id.imageView_01);
        imageView1.setBackgroundResource(R.drawable.layerdrawable_test);


        Drawable drawable1 = getResources().getDrawable(R.drawable.android_system_logo);
        Drawable drawable2 = getResources().getDrawable(R.drawable.android_system_logo);
        Drawable drawable3 = getResources().getDrawable(R.drawable.android_system_logo);

        ImageView imageView2 = (ImageView) findViewById(R.id.imageView_02);
        imageView2.setBackground(drawable1);

        Drawable[] drawables = new Drawable[]{
                drawable1,
                drawable2,
                drawable3,
        };

        LayerDrawable layerDrawable = new LayerDrawable(drawables);
        layerDrawable.setLayerInset(0,0,0,0,0);
        layerDrawable.setLayerInset(1,drawable1.getIntrinsicWidth()/2,drawable1.getIntrinsicHeight()/2,0,0);
        layerDrawable.setLayerInset(2,0,0,drawable1.getIntrinsicWidth()/2,drawable1.getIntrinsicHeight()/2);

        ImageView imageView3 = (ImageView) findViewById(R.id.imageView_03);
        imageView3.setBackground(layerDrawable);
    }
}
