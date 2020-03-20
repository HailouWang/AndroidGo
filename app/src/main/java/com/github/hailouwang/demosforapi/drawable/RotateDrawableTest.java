package com.github.hailouwang.demosforapi.drawable;

import android.graphics.drawable.RotateDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.R;

/**
 *

 *
 *解析成：RotateDrawable
 *
 *
 * 语法：
 *
 <?xml version="1.0" encoding="utf-8"?>
 <rotate
 xmlns:android="http://schemas.android.com/apk/res/android"
 android:drawable=""
 android:visible=""
 android:fromDegrees=""
 android:toDegrees=""
 android:pivotX=""
 android:pivotY=""/>


 * 说明：
 *
 *
 <rotate>
 定义一个RotateDrawable，必须作为根元素。
 属性：
 xmlns:android
 String类型。 必须的。定义XML文件的命名空间。必须是 "http://schemas.android.com/apk/res/android".
 android:drawable
 Drawable 资源。必须的。引用一个drawable资源。
 android:visible
 Boolean。是否可见。
 android:fromDegrees
 整形。  从多少的角度开始旋转

 android:toDegrees
 整形。  到多少的角度结束旋转

 android:pivotX
 百分比。  旋转的中心在图片X轴的百分比

 android:visible
 百分比。  旋转的中心在图片Y轴的百分比






 */
public class RotateDrawableTest extends AppCompatActivity {

    private static final int MAX_LEVEL = 10000;

    RotateDrawable rotateDrawable01,rotateDrawable02;

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
                    rotateDrawable01.setLevel(level1);

                    break;
                case MSG_REFRESH_IMAGE_02:
                    if (msg.arg1 >= 10) {
                        msg.arg1 = 0;
                    } else {
                        msg.arg1++;
                    }

                    int level2 = Math.abs(MAX_LEVEL + msg.arg1 * 1000) - MAX_LEVEL;

                    rotateDrawable02.setLevel(level2);
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
        setContentView(R.layout.activity_base_drawable_layout);

        TextView tv01 = (TextView) findViewById(R.id.tv01);
        tv01.setText("正常状态");

        ImageView imageView01 = (ImageView) findViewById(R.id.imageView_01);
        imageView01.setImageResource(R.drawable.image1);

        //xml方式

        TextView tv02 = (TextView) findViewById(R.id.tv02);
        tv02.setText("循环从-90到90度，然后从90到-90度");

        ImageView imageView02 = (ImageView) findViewById(R.id.imageView_02);
        imageView02.setImageResource(R.drawable.rotation_drawable);
        rotateDrawable01 = (RotateDrawable) imageView02.getDrawable();
        handler.sendEmptyMessageDelayed(MSG_REFRESH_IMAGE_01,1000);

        //代码方式

        TextView tv03 = (TextView) findViewById(R.id.tv03);
        tv03.setText("循环从-90到90度");

        rotateDrawable02 = new RotateDrawable();
        rotateDrawable02.setDrawable(getDrawable(R.drawable.image1));
        rotateDrawable02.setFromDegrees(-90);
        rotateDrawable02.setToDegrees(90);
        rotateDrawable02.setPivotX(0.5f);
        rotateDrawable02.setPivotY(0.5f);

        ImageView imageView03 = (ImageView) findViewById(R.id.imageView_03);
        imageView03.setImageDrawable(rotateDrawable02);

        handler.sendEmptyMessageDelayed(MSG_REFRESH_IMAGE_02,1000);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

}
