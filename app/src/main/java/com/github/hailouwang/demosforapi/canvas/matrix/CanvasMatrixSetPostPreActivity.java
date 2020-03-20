package com.github.hailouwang.demosforapi.canvas.matrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.Tags;
import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

/**
 *
 */
public class CanvasMatrixSetPostPreActivity extends BaseCanvasActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSelfDraw(Canvas canvas,int width,int height) {
        super.onSelfDraw(canvas);
        Paint paint = getCustomPaint();
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);

        int tempWidth = width/4,tempHeight = height/4;
        // CanvasMatrixTranslate width:360,height :570
        Log.d(Tags.HLWANG_TAG,"CanvasMatrixTranslate width:"+tempWidth
        +",height :"+tempHeight);

        //将画布中心点，移动到0,0
        canvas.translate(0,0);

        //1、正常
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.raw.thumb1);
        Matrix matrix = new Matrix();

        canvas.drawBitmap(bitmap,matrix,paint);

        //2、set调用方式，只是最后一个set实现效果
        canvas.translate(tempWidth*2,0);

        matrix.setSkew(0.5f,0.5f);
        matrix.setTranslate(100,100);
        canvas.drawBitmap(bitmap,matrix,paint);

        //3、pre调用方式，相当于矩阵后乘
        canvas.translate(-tempWidth*2,tempHeight);

        /**
         * 缩放矩阵 M
         * [0.5, 0.0, 0.0]
         * [0.0, 0.5, 0.0]
         * [0.0, 0.0, 1.0]
         */
        matrix.setScale(0.5f,0.5f);
        Log.d(Tags.HLWANG_TAG,"Set3 matrix："+matrix);
        /**
         * 平移矩阵 N
         * [1.0, 0.0, 100.0]
         * [0.0, 1.0, 100.0]
         * [0.0, 0.0, 1.0]
         */
        matrix.preTranslate(100,100);
        /**
         * preXXX相当于后乘，即：
         *
         * preXXX = M * N;
         *
         *[0.5, 0.0, 50.0]
         *[0.0, 0.5, 50.0]
         *[0.0, 0.0, 1.0]
         */
        Log.d(Tags.HLWANG_TAG,"Pre3 matrix："+matrix);
        canvas.drawBitmap(bitmap,matrix,paint);

        //4、post调用方式，相当于矩阵前程
        canvas.translate(tempWidth*2,0);

        /**
         * 缩放矩阵 M
         * [0.5, 0.0, 0.0]
         * [0.0, 0.5, 0.0]
         * [0.0, 0.0, 1.0]
         */
        matrix.setScale(0.5f,0.5f);
        Log.d(Tags.HLWANG_TAG,"Set4 matrix："+matrix);
        /**
         * 平移矩阵 N
         * [1.0, 0.0, 100.0]
         * [0.0, 1.0, 100.0]
         * [0.0, 0.0, 1.0]
         */
        matrix.postTranslate(100,100);
        /**
         * postXXX相当于后乘，即：
         *
         * postXXX = N * M ;
         *
         *[0.5, 0.0, 100.0]
         *[0.0, 0.5, 100.0]
         *[0.0, 0.0, 1.0]
         */
        Log.d(Tags.HLWANG_TAG,"Post4 matrix："+matrix);
        canvas.drawBitmap(bitmap,matrix,paint);

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
