package com.github.hailouwang.demosforapi.canvas.canvas_draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;

import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

/**
 * 一、缩放方法
 *
 * public void scale (float sx, float sy)
 * public final void scale (float sx, float sy, float px, float py)
 *
 *
 * 二、取值说明
 *
 * 取值范围(n)	    说明
 * [-∞, -1)	        先根据缩放中心放大n倍，再根据中心轴进行翻转
 * -1	            根据缩放中心轴进行翻转
 * (-1, 0)	        先根据缩放中心缩小到n，再根据中心轴进行翻转
 * 0	            不会显示，若sx为0，则宽度为0，不会显示，sy同理
 * (0, 1)	        根据缩放中心缩小到n
 * 1	            没有变化
 * (1, +∞)	        根据缩放中心放大n倍
 *
 *
 * 三、源码
 *
 *     public final void scale(float sx, float sy, float px, float py) {
 *          translate(px, py);
 *          scale(sx, sy);
 *          translate(-px, -py);
 *      }
 */
public class CanvasScaleActivity extends BaseCanvasActivity {

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
        canvas.scale(0.5f,0.5f);
        canvas.drawRect(rect1,paint);

        //2、按照反方向缩放
        canvas.scale(2f,2f);//恢复缩放
        canvas.translate(tempWidth,0);//相对绝对坐标，移动到2/4位置
        paint.setColor(Color.RED);

        Rect rect2 = new Rect(0,-200,120,0);
        canvas.drawRect(rect2,paint);
        canvas.scale(-0.5f,-0.5f);
        canvas.drawRect(rect2,paint);

        //3、设定缩放中心，正缩放
        canvas.scale(-2f,-2f);//恢复缩放
        canvas.translate(tempWidth,0);//相对绝对坐标，移动到3/4位置
        paint.setColor(Color.GREEN);

        Rect rect3 = new Rect(0,-200,120,0);
        canvas.drawRect(rect3,paint);
        canvas.scale(0.5f,0.5f,60,0);//相对 60，0这个点进行缩放
        canvas.drawRect(rect3,paint);

        //4、设定缩放中心，反转缩放
        canvas.scale(2f,2f);//恢复缩放
        canvas.translate(-tempWidth*2-30,tempHeight);//相对绝对坐标，向左移动到1/4位置
        paint.setColor(Color.GREEN);

        Rect rect4 = new Rect(0,-200,120,0);
        canvas.drawRect(rect4,paint);
        canvas.scale(-0.5f,-0.5f,60,0);//相对 60，0这个点进行缩放
        canvas.drawRect(rect4,paint);


        //5、设定缩放中心,相对-60，0进行缩放
        canvas.scale(-2f,-2f);//恢复缩放
        canvas.translate(tempWidth-90,0);//相对绝对坐标，向右移动到2/4位置
        paint.setColor(Color.CYAN);

        Rect rect5 = new Rect(0,-200,120,0);
        canvas.drawRect(rect5,paint);
        canvas.scale(0.5f,0.5f,-60,0);//相对 -60，0这个点进行缩放
        canvas.drawRect(rect5,paint);

        //6、设定放大，放大2倍，中心设定为 60，0
        canvas.scale(2f,2f);//恢复缩放
        canvas.translate(tempWidth+30,0);//相对绝对坐标，向右移动到2/4位置
        paint.setColor(Color.BLUE);

        Rect rect6 = new Rect(0,-200,120,0);
        canvas.drawRect(rect6,paint);
        canvas.scale(2f,2f,60,0);//相对 60，0这个点进行缩放
        canvas.drawRect(rect6,paint);

        //7、显示一个美妙的图形
        canvas.scale(0.5f,0.5f);//恢复缩放
        canvas.translate(-tempWidth*2+60,tempHeight);//相对绝对坐标，向右移动到2/4位置
        paint.setColor(Color.BLACK);

        Rect rect7 = new Rect(-200,-200,200,200);
        for(int i=0;i<20;i++){
            canvas.drawRect(rect7,paint);
            canvas.scale(0.9f,0.9f);
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
