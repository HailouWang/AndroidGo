package com.github.hailouwang.demosforapi.canvas.canvas_draw;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

import java.io.IOException;
import java.io.InputStream;

/**
 * 一、方法
 *
 * 1、资源文件(drawable/mipmap/raw):
 * Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),R.raw.bitmap);
 *
 * 2、通过assets目录加载
 * Bitmap bitmap=null;
 * try {
 *      InputStream is = mContext.getAssets().open("bitmap.png");
 *      bitmap = BitmapFactory.decodeStream(is);
 *      is.close();
 * } catch (IOException e) {
 *      e.printStackTrace();
 * }
 *
 * 3、通过sdcard加载
 * Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/bitmap.png");
 *
 * 4、通过输入流加载
 * // 此处省略了获取网络输入流的代码
 * Bitmap bitmap = BitmapFactory.decodeStream(is);
 * is.close();
 *
 * 二、源码
 *
 */
public class CanvasBitmapActivity extends BaseCanvasActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSelfDraw(Canvas canvas,int width,int height) {
        super.onSelfDraw(canvas);
        Paint paint = getCustomPaint();
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.FILL);

        int tempWidth = width/4,tempHeight = height/4;

        //将画布中心点，移动到 0,0 位置
        canvas.translate(0,0);

        //1、通过BitmapFactory获得Bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(), R.raw.thumb1);

        canvas.drawBitmap(bitmap,new Matrix(),paint);


        //2、通过picture.draw 绘制

        canvas.translate(tempWidth*2,0);

        try {
            InputStream is = this.getAssets().open("thumb2.png");
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //设定距离左、上的间距
        canvas.drawBitmap(bitmap,20,20,paint);

        //3、通过Canvas.drawBitmap 来绘制
        canvas.translate(-tempWidth*2,tempHeight);

        Rect src = new Rect(0,0,bitmap.getWidth()/2,bitmap.getHeight());
        Rect dest = new Rect(0,0,bitmap.getWidth()/2,bitmap.getHeight());
        canvas.drawBitmap(bitmap,src,dest,paint);

    }

    @Override
    protected boolean isDrawHelpLine() {
        return true;
    }

    @Override
    protected int drawHelpLineNumbers() {
        return 4;
    }
}
