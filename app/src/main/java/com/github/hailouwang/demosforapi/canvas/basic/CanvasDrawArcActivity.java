package com.github.hailouwang.demosforapi.canvas.basic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;

import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

public class CanvasDrawArcActivity extends BaseCanvasActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSelfDraw(Canvas canvas) {
        super.onSelfDraw(canvas);
        Paint paint = getCustomPaint();
        paint.setStrokeWidth(20);

        paint.setColor(Color.GRAY);
        RectF rectF = new RectF(100,200,500,600);
        canvas.drawRect(rectF,paint);

        paint.setColor(Color.YELLOW);

        /**
         * float startAngle,:开始角度
         * float sweepAngle,:结束角度
         * boolean useCenter,:是否使用圆点
         */

        canvas.drawArc(rectF,0,90,false,paint);



        //----------------------------

        paint.setColor(Color.GRAY);
        RectF rectF1 = new RectF(100,800,500,1200);
        canvas.drawRect(rectF1,paint);

        paint.setColor(Color.BLUE);

        /**
         * float startAngle,:开始角度
         * float sweepAngle,:结束角度
         * boolean useCenter,:是否使用圆点
         */

        canvas.drawArc(rectF1,0,90,true,paint);

        //绘制正圆圆弧
        paint.setColor(Color.GREEN);
        canvas.drawArc(100,1400,400,1600,-30,60,true,paint);
    }
}
