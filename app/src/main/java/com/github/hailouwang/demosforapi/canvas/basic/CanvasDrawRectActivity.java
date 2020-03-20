package com.github.hailouwang.demosforapi.canvas.basic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;

import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

public class CanvasDrawRectActivity extends BaseCanvasActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSelfDraw(Canvas canvas) {
        super.onSelfDraw(canvas);
        Paint paint = getCustomPaint();
        paint.setStrokeWidth(20);

        paint.setColor(Color.RED);
        canvas.drawRect(300,300,600,330,paint);

        paint.setColor(Color.GREEN);
        canvas.drawRect(435,300,465,600,paint);

        paint.setColor(Color.BLUE);
        Rect rectInt = new Rect(300,450,600,480);
        canvas.drawRect(rectInt,paint);

        paint.setColor(Color.YELLOW);
        RectF rectFloat = new RectF(300,600,600,630);
        canvas.drawRect(rectFloat,paint);
    }
}
