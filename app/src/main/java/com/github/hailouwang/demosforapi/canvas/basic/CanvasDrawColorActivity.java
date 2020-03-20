package com.github.hailouwang.demosforapi.canvas.basic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;

import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

public class CanvasDrawColorActivity extends BaseCanvasActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSelfDraw(Canvas canvas) {
        super.onSelfDraw(canvas);
        canvas.drawColor(Color.YELLOW);
    }
}
