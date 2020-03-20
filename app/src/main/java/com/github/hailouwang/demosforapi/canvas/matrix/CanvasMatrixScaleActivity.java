package com.github.hailouwang.demosforapi.canvas.matrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

/**
 *
 */
public class CanvasMatrixScaleActivity extends BaseCanvasActivity {

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

        //将画布中心点，移动到0,0
        canvas.translate(0,0);

        //1、缩放
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.raw.thumb1);
        Matrix matrix = new Matrix();

        canvas.drawBitmap(bitmap,matrix,paint);

        //放大2倍
        canvas.translate(0,tempHeight);
        matrix.postScale(2f,2f);

        canvas.drawBitmap(bitmap,matrix,paint);

        //放大
        matrix.reset();
        canvas.translate(0,tempHeight*2);
        matrix.postScale(2f,0.5f);

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
