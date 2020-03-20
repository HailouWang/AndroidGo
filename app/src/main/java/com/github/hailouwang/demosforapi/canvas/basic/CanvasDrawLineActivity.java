package com.github.hailouwang.demosforapi.canvas.basic;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;

import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

public class CanvasDrawLineActivity extends BaseCanvasActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSelfDraw(Canvas canvas) {
        super.onSelfDraw(canvas);
        Paint paint = getCustomPaint();
        paint.setStrokeWidth(20);

        canvas.drawLine(300,300,600,300,getCustomPaint());

        canvas.drawLine(450,300,450,600,getCustomPaint());

        canvas.drawLine(300,450,600,450,getCustomPaint());

        canvas.drawLine(300,600,600,600,getCustomPaint());
    }
}
