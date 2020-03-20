package com.github.hailouwang.demosforapi.canvas.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;

import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

public class CanvasPaintCapActivity extends BaseCanvasActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSelfDraw(Canvas canvas) {
        super.onSelfDraw(canvas);
        Paint paint = getCustomPaint();
        paint.setStrokeWidth(50);
        paint.setTextSize(50);
        paint.setStyle(Paint.Style.STROKE);

        //Paint Cap，比较形象的解释就是 用来控制我们的画笔在离开画板时候留下的最后一点图形
        // 设置线帽样式，取值有Cap.ROUND(圆形线帽)、Cap.SQUARE(方形线帽)、Paint.Cap.BUTT(无线帽)
        // 线冒，可以理解为一条线两个端点，设置线冒样式使线条两端不看起来不那么死板
        // 样式有三种：BUTT=默认 ROUND=圆形  SQUARE=矩形

        Path path = new Path();
        path.moveTo(300,300);
        path.lineTo(500,300);

        paint.setStrokeCap(Paint.Cap.BUTT);
        paint.setColor(Color.BLUE);
        canvas.drawPath(path,paint);

        drawText(canvas,"Paint.Cap.BUTT 默认",800,300,paint);

        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(Color.RED);
        path.reset();
        path.moveTo(300,600);
        path.lineTo(500,600);
        canvas.drawPath(path,paint);

        drawText(canvas,"Paint.Cap.ROUND 圆形",800,600,paint);

        paint.setStrokeCap(Paint.Cap.SQUARE);
        paint.setColor(Color.GREEN);
        path.reset();
        path.moveTo(300,900);
        path.lineTo(500,900);
        canvas.drawPath(path,paint);

        drawText(canvas,"Paint.Cap.SQUARE 方形",800,900,paint);

        //辅助线1
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1);
        canvas.drawLine(300,150,300,1200,paint);

        //辅助线2
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1);
        canvas.drawLine(500,150,500,1200,paint);

    }

    private void drawText(Canvas canvas,String text,int x,int y,Paint paint){
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("Paint.Join.MITER 锐角",x,y,paint);
        paint.setStyle(Paint.Style.STROKE);
    }
}
