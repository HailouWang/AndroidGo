package com.github.hailouwang.demosforapi.canvas.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;

import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

public class CanvasPaintJoinActivity extends BaseCanvasActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSelfDraw(Canvas canvas) {
        super.onSelfDraw(canvas);
        Paint paint = getCustomPaint();
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(50);

        //Paint Join的理解也很容易，他是用来控制画的图形接触时候的样式的
        // 样式有三种：MITER=锐角,BEVEL=直角 ROUND=圆角
        // 设置结合处效果

        Path path = new Path();
        path.moveTo(300,300);
        path.lineTo(500,300);
        path.lineTo(300,150);

        paint.setStrokeJoin(Paint.Join.MITER);
        paint.setColor(Color.BLUE);
        canvas.drawPath(path,paint);

        paint.setStyle(Paint.Style.FILL);
        drawText(canvas,"Paint.Join.MITER 锐角",800,300,paint);

        paint.setStrokeJoin(Paint.Join.BEVEL);
        paint.setColor(Color.RED);
        path.reset();
        path.moveTo(300,600);
        path.lineTo(500,600);
        path.lineTo(300,450);
        canvas.drawPath(path,paint);

        drawText(canvas,"Paint.Join.BEVEL 直角",800,600,paint);

        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setColor(Color.GREEN);
        path.reset();
        path.moveTo(300,900);
        path.lineTo(500,900);
        path.lineTo(300,750);
        canvas.drawPath(path,paint);

        drawText(canvas,"Paint.Join.ROUND 圆角",800,900,paint);

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
