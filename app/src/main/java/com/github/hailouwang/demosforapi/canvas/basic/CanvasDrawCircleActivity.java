package com.github.hailouwang.demosforapi.canvas.basic;

import android.graphics.Canvas;
import android.os.Bundle;

import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

public class CanvasDrawCircleActivity extends BaseCanvasActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSelfDraw(Canvas canvas) {
        super.onSelfDraw(canvas);
        canvas.drawCircle(100,100,90,getCustomPaint());
    }
}
