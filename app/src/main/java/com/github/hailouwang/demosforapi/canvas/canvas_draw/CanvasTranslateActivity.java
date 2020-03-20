package com.github.hailouwang.demosforapi.canvas.canvas_draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

public class CanvasTranslateActivity extends BaseCanvasActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSelfDraw(Canvas canvas) {
        super.onSelfDraw(canvas);
        Paint paint = getCustomPaint();
        paint.setStrokeWidth(20);

        canvas.drawCircle(300,300,80,paint);

        /**
         * Translate是坐标系的移动，而且是基于当前位置来定的，并不是基于屏幕(0,0)来定。
         */
        canvas.translate(300,300);

        paint.setColor(Color.RED);
        canvas.drawCircle(300,300,80,paint);
    }
}
