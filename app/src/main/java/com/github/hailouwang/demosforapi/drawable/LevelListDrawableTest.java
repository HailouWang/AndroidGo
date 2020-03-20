package com.github.hailouwang.demosforapi.drawable;

import android.graphics.drawable.LevelListDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.Tags;

/**
 *
 * 一个LeveListDrawable管理着一组交替的drawable资源。
 * LeveListDrawable里面的每一个drawable资源与一个最大数值结合起来，作为LevelListDrawable资源的一项。
 * 调用Drawable的setLevel()方法可以加载level-list或代码中定义的某个drawable资源，
 * 判断加载某项的方式：level-list中某项的Android:maxLevel数值大于或者等于setLevel设置的数值，就会被加载。
 *
 *解析成：LevelListDrawable
 *
 *
 * 语法：
 *
 <?xml version="1.0" encoding="utf-8"?>
 <level-list
 xmlns:android="http://schemas.android.com/apk/res/android" >
 <item
 android:drawable="@drawable/drawable_resource"
 android:maxLevel="integer"
 android:minLevel="integer" />
 </level-list>



 * 说明：
 *
 *
 <level-list>
 必须作为根节点。包含多个<item>节点。
 属性：
 xmlns:android
 String类型。必须的。定义XML文件的命名空间，必须是 "http://schemas.android.com/apk/res/android".
 <item>
 定义某个level使用的drawable资源
 属性：
 android:drawable
 Drawable 资源。必须的。引用一个Drawable资源
 android:maxLevel
 Integer类型。该项所允许的最大level。
 android:minLevel
 Integer类型。该项所允许的最小level。



 使用LevelDrawable注意几点：
 1、默认的level为0，如果没有和0匹配的level，那么就不显示。
 2、level匹配以maxLevel优先。即如果有个item，min：1，max：2。   另一份item，min：2，max：3。
 如果此时设置level=2，那么会匹配第一个item。

 */
public class LevelListDrawableTest extends AppCompatActivity {

    int level01 = 0;
    int level02 = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levellist_drawable);

        //xml方式
        final ImageView imageView01 = (ImageView) findViewById(R.id.imageView01);
        imageView01.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(level01>=2)level01=0;
                else level01++;
                Log.d(Tags.HLWANG_TAG,"LevelListDrawable level01:"+level01);
                imageView01.getDrawable().setLevel(level01);
            }
        });

        //代码方式
        LevelListDrawable levelListDrawable = new LevelListDrawable();
        levelListDrawable.addLevel(0,0,getResources().getDrawable(R.drawable.stat_sad,getTheme()));
        levelListDrawable.addLevel(0,1,getResources().getDrawable(R.drawable.stat_neutral,getTheme()));
        levelListDrawable.addLevel(0,2,getResources().getDrawable(R.drawable.stat_happy,getTheme()));

        final ImageView imageView02 = (ImageView) findViewById(R.id.imageView02);
        imageView02.setImageDrawable(levelListDrawable);
        imageView02.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(level02>=2)level02=0;
                else level02++;
//                imageView02.setImageLevel(level02);
                Log.d(Tags.HLWANG_TAG,"LevelListDrawable level02:"+level02);
                imageView02.getDrawable().setLevel(level02);
            }
        });
    }
}
