package com.github.hailouwang.demosforapi.canvas.basic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;

import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

public class CanvasDrawRoundRectActivity extends BaseCanvasActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSelfDraw(Canvas canvas) {
        super.onSelfDraw(canvas);
        Paint paint = getCustomPaint();
        paint.setStrokeWidth(20);

        /**
         * 其中rx=30、ry=10，分别是圆角矩形中的圆弧，也就是椭圆的两个半径
         */
        canvas.drawRoundRect(300,300,600,400,30,10,paint);


        RectF rectF = new RectF(700,600,1000,800);
        canvas.drawRoundRect(rectF,30,20,paint);

        //当rx、ry大于等于矩形的宽度和高度的一半，那么就办成一个椭圆了
        paint.setColor(Color.RED);
        canvas.drawRoundRect(300,300,600,400,150,50,paint);

        paint.setColor(Color.GREEN);
        RectF rectF1 = new RectF(300,600,500,800);
        canvas.drawRoundRect(rectF1,180,120,paint);
    }
}
