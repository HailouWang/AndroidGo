package com.github.hailouwang.demosforapi.drawable;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.R;

/**
 *

 ClipDrawable 是对一个Drawable进行剪切操作，可以控制这个drawable的剪切区域，
 以及相相对于容器的对齐方式，Android中的进度条就是使用一个ClipDrawable实现效果的，它根据level的属性值，决定剪切区域的大小。
 需要注意的是ClipDrawable是根据level的大小控制图片剪切操作的，
 官方文档的note中提到：The drawable is clipped completely and not visible when the level is 0 and
 fully revealed when the level is 10,000。也就是level的大小从0到10000，level为0时完全不显示，为10000时完全显示。
 是用Drawable提供的setLevel（int level）方法来设置剪切区域。

 *
 *解析成：ClipDrawable
 *
 *
 * 语法：
 *
 <?xml version="1.0" encoding="utf-8"?>
 <clip
 xmlns:android="http://schemas.android.com/apk/res/android"
 android:drawable="@drawable/drawable_resource"
 android:clipOrientation=["horizontal" | "vertical"]
 android:gravity=["top" | "bottom" | "left" | "right" | "center_vertical" |
 "fill_vertical" | "center_horizontal" | "fill_horizontal" |
 "center" | "fill" | "clip_vertical" | "clip_horizontal"] />


 * 说明：
 *
 *
 <clip>
 定义这是个ClipDrawable，必须作为根元素。
 属性：
 xmlns:android
 String类型。必须的，定义XML文件的命名空间，必须是"http://schemas.android.com/apk/res/android".
 android:drawable
 Drawable资源。 必须的。表示该ClipDrawable引用的drawable资源。
 android:clipOrientation
 关键字。 裁剪的方向。
 必须是下面两种的数值之一：
 值	描述
 horizontal	水平方向裁剪
 vertical	垂直方向裁剪
 android:gravity
 关键字。指定从哪个地方裁剪。
 必须是下面一个或多个值（多个值之间用“|”分隔）：
 值	描述
 top	将这个对象放在容器的顶部，不改变其大小。当clipOrientation 是"vertical"，裁剪发生在drawable的底部（bottom）
 bottom	将这个对象放在容器的底部，不改变其大小。当clipOrientation 是 "vertical"，裁剪发生在drawable的顶部（top）
 left	将这个对象放在容器的左部，不改变其大小。当clipOrientation 是 "horizontal"，裁剪发生在drawable的右边（right）。默认的是这种情况。
 right	将这个对象放在容器的右部，不改变其大小。当clipOrientation 是 "horizontal"，裁剪发生在drawable的左边（left）。
 center_vertical	将对象放在垂直中间，不改变其大小。裁剪的情况和”center“一样。
 fill_vertical	垂直方向上不发生裁剪。（除非drawable的level是 0，才会不可见，表示全部裁剪完）
 center_horizontal	将对象放在水平中间，不改变其大小。裁剪的情况和”center“一样。
 fill_horizontal	水平方向上不发生裁剪。（除非drawable的level是 0，才会不可见，表示全部裁剪完）
 center	将这个对象放在水平垂直坐标的中间，不改变其大小。当clipOrientation 是 "horizontal"裁剪发生在左右。当clipOrientation是"vertical",裁剪发生在上下。
 fill	填充整个容器，不会发生裁剪。(除非drawable的level是 0，才会不可见，表示全部裁剪完)。
 clip_vertical
 额外的选项，它能够把它的容器的上下边界，设置为子对象的上下边缘的裁剪边界。裁剪要基于对象垂直重力设置：如果重力设置为top，则裁剪下边，如果设置为bottom，则裁剪上边，否则则上下两边都要裁剪。
 clip_horizontal
 额外的选项，它能够把它的容器的左右边界，设置为子对象的左右边缘的裁剪边界。
 裁剪要基于对象垂直重力设置：如果重力设置为right，则裁剪左边，如果设置为left，则裁剪右边，否则则左右两边都要裁剪。






 */
public class ClipDrawableTest extends AppCompatActivity {

    private static final int MAX_LEVEL = 10000;

    ClipDrawable clipDrawable01,clipDrawable02;

    private static final int MSG_REFRESH_IMAGE_01 = 0;
    private static final int MSG_REFRESH_IMAGE_02 = 1;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch(msg.what){
                case MSG_REFRESH_IMAGE_01:
                    if(msg.arg1>=20){
                        msg.arg1 = 0;
                    }else{
                        msg.arg1++;
                    }

                    int level1 = MAX_LEVEL - Math.abs(-MAX_LEVEL+msg.arg1 * 1000);
                    clipDrawable01.setLevel(level1);

                    break;
                case MSG_REFRESH_IMAGE_02:
                    if (msg.arg1 >= 10) {
                        msg.arg1 = 0;
                    } else {
                        msg.arg1++;
                    }

                    int level2 = Math.abs(MAX_LEVEL + msg.arg1 * 1000) - MAX_LEVEL;

                    clipDrawable02.setLevel(level2);
                    break;
            }
            Message myMsg1 = obtainMessage(msg.what,msg.arg1,msg.arg2);
            myMsg1.setTarget(this);
            sendMessageDelayed(myMsg1,1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_drawable);

        //xml方式
        ImageView imageView01 = (ImageView) findViewById(R.id.imageView01);
        clipDrawable01 = (ClipDrawable) imageView01.getDrawable();
        imageView01.setImageDrawable(clipDrawable01);

        handler.sendEmptyMessageDelayed(MSG_REFRESH_IMAGE_01,1000);

        //代码方式
        clipDrawable02 = new ClipDrawable(getDrawable(R.drawable.android_system_logo),
                Gravity.LEFT,ClipDrawable.HORIZONTAL);
        ImageView imageView02 = (ImageView) findViewById(R.id.imageView02);
        imageView02.setImageDrawable(clipDrawable02);
        handler.sendEmptyMessageDelayed(MSG_REFRESH_IMAGE_02,1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
