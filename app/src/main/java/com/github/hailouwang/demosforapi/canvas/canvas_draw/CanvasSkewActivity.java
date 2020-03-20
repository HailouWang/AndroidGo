package com.github.hailouwang.demosforapi.canvas.canvas_draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;

import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

/**
 * 一、方法
 *
 *  public void skew (float sx, float sy)
 *
 *
 * 二、取值说明
 *
 * float sx:将画布在x方向上倾斜相应的角度，sx为倾斜角度的tan值；
 * float sy:将画布在y轴方向上倾斜相应的角度，sy为倾斜角度的tan值；
 * 注意，这里全是倾斜角度的tan值，比如我们打算在X轴方向上倾斜45度，tan45=1；
 *
 * tan =y/x，那么：
 *
 * X = x + sx * y
 * Y = sy * x + y
 *
 * 三、源码
 *
 /**
 * Preconcat the current matrix with the specified skew.
 *
 * @param sx The amount to skew in X
 * @param sy The amount to skew in Y
 *
 * public void skew(float sx, float sy) {
 *   native_skew(mNativeCanvasWrapper, sx, sy);
 * }
 *
 */
public class CanvasSkewActivity extends BaseCanvasActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSelfDraw(Canvas canvas,int width,int height) {
        super.onSelfDraw(canvas);
        Paint paint = getCustomPaint();
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.FILL);

        int tempWidth = width/4,tempHeight = height/4;

        //将画布坐标系中心点
        canvas.translate(tempWidth,tempHeight);

        //1、绘制矩形
        Rect rect1 = new Rect(0,200,120,0);
        canvas.drawRect(rect1,paint);

        //2、执行skew,(1,0)

        canvas.translate(tempWidth*2,0);

        paint.setColor(Color.RED);
        canvas.skew(1,0);
        canvas.drawRect(rect1,paint);

        //3、执行skew,(0,1)
        canvas.translate(-tempWidth*3-200,tempHeight);

        paint.setColor(Color.GREEN);
        canvas.skew(-1,1);
        canvas.drawRect(rect1,paint);

        //4、执行skew,(1,1)

        canvas.translate(tempWidth,-340);

        paint.setColor(Color.BLACK);
        canvas.skew(1,0);
        canvas.drawRect(rect1,paint);

    }

    @Override
    protected boolean isDrawHelpLine() {
        return true;
    }

    @Override
    protected int drawHelpLineNumbers() {
        return 4;
    }
}
