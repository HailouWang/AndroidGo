package com.github.hailouwang.demosforapi.canvas.paint;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

public class CanvasPaintStyleActivity extends BaseCanvasActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSelfDraw(Canvas canvas) {
        super.onSelfDraw(canvas);
        Paint paint = getCustomPaint();
        paint.setStrokeWidth(60);

        //Paint样式有三种：STROKE=描边 FILL=填充  FILL_AND_STROKE=描边加填充

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.YELLOW);
        canvas.drawCircle(300,300,120,paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawCircle(300,600,120,paint);


        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.GREEN);
        canvas.drawCircle(300,900,120,paint);

        //辅助线1
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1);
        canvas.drawLine(150,150,150,1200,paint);

        //辅助线2
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1);
        canvas.drawLine(180,150,180,1200,paint);

    }
}
