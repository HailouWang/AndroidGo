package com.github.hailouwang.demosforapi.canvas.canvas_draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;

import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

/**
 * 一、旋转方法
 *
 *  public void rotate (float degrees)
 *  public final void rotate (float degrees, float px, float py)
 *
 *
 *
 * 二、源码
 *
 *         public final void rotate(float degrees, float px, float py) {
 *              translate(px, py);
 *              rotate(degrees);
 *              translate(-px, -py);
 *         }
 */
public class CanvasRotateActivity extends BaseCanvasActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSelfDraw(Canvas canvas,int width,int height) {
        super.onSelfDraw(canvas);
        Paint paint = getCustomPaint();
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);

        int tempWidth = width/4,tempHeight = height/4;

        //将画布中心点，移动到第一个1/4的位置
        canvas.translate(tempWidth,tempHeight);

        //1、绘制矩形
        Rect rect1 = new Rect(0,-200,120,0);
        canvas.drawRect(rect1,paint);
        canvas.rotate(180);//旋转180度
        canvas.drawRect(rect1,paint);

        //2、旋转是叠加的,这个时候坐标系也是旋转的，如果想要向右转换坐标系，那么需要 -tempWidth
        paint.setColor(Color.RED);
        canvas.translate(-tempWidth,0);

        Rect rect2 = new Rect(0,-200,120,0);
        canvas.drawRect(rect2,paint);

        //3、
        canvas.rotate(-180);//恢复旋转角度
        canvas.translate(tempWidth,0);//相对绝对坐标，移动到3/4位置
        paint.setColor(Color.GREEN);

        Rect rect3 = new Rect(0,-200,120,0);
        canvas.drawRect(rect3,paint);
        canvas.rotate(180,80,0);//以80，0这个点进行旋转
        canvas.drawRect(rect3,paint);

        //4、矩形旋转的应用
        canvas.rotate(-180);
        canvas.translate(-tempWidth*2-160,tempHeight);//相对绝对坐标，向左移动到1/4位置
        paint.setColor(Color.BLUE);

        Rect rect4 = new Rect(0,-200,120,0);
        for(int i=0;i<20;i++){
            canvas.drawRect(rect4,paint);
//            canvas.rotate(10,10,0);
            canvas.rotate(10);
        }


        //5、圆形旋转的应用
        canvas.rotate(-200);
        canvas.translate(tempWidth*2,0);//相对绝对坐标，向右移动到2/4位置
        paint.setColor(Color.CYAN);

        int radius1 = 200;
        canvas.drawCircle(0,0,radius1,paint);

        for(int i=0;i<36;i++){
            canvas.drawLine(0,0,0,-radius1-10,paint);
            canvas.rotate(10);
        }

        //6、圆形旋转的应用
        canvas.translate(-tempWidth,tempHeight);//相对绝对坐标，向右移动到2/4位置
        paint.setColor(Color.GREEN);

        int radius2 = 200;
        float degree = 10;
        int rotationX = 80,rotationY = 0;

        for(int i=0;i<36;i++){
//            canvas.drawLine(-(int)(rotationX/Math.sin(90-degree)),0,-(int)(rotationX/Math.sin(90-degree)),-radius2-10,paint);
            canvas.drawLine(-rotationX,0,-rotationX,-radius1-10,paint);
            canvas.rotate(degree,0,0);
        }
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
