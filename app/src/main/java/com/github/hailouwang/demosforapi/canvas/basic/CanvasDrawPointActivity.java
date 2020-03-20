package com.github.hailouwang.demosforapi.canvas.basic;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;

import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

public class CanvasDrawPointActivity extends BaseCanvasActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSelfDraw(Canvas canvas) {
        super.onSelfDraw(canvas);
        Paint paint = getCustomPaint();
        paint.setStrokeWidth(20);

        canvas.drawPoint(200,200,paint);
        canvas.drawPoint(300,200,paint);
        canvas.drawPoint(400,200,paint);

        canvas.drawPoint(300,300,paint);

        canvas.drawPoint(200,400,paint);
        canvas.drawPoint(300,400,paint);
        canvas.drawPoint(400,400,paint);

        canvas.drawPoint(300,500,paint);

        canvas.drawPoint(200,600,paint);
        canvas.drawPoint(300,600,paint);
        canvas.drawPoint(400,600,paint);
    }
}
