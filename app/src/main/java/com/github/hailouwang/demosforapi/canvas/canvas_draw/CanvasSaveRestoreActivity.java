package com.github.hailouwang.demosforapi.canvas.canvas_draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;

import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

/**
 * 一、快照(save)和回滚(restore)
 *画布的操作是不可逆的，而且很多画布操作会影响后续的步骤，
 * 例如第一个例子，两个圆形都是在坐标原点绘制的，而因为坐标系的移动绘制出来的实际位置不同。所以会对画布的一些状态进行保存和回滚。
 * 与之相关的API:
 *
 * 相关API	        简介
 * save	            把当前的画布的状态进行保存，然后放入特定的栈中
 * saveLayerXxx	    新建一个图层，并放入特定的栈中
 * restore	        把栈中最顶层的画布状态取出来，并按照这个状态恢复当前的画布
 * restoreToCount	弹出指定位置及其以上所有的状态，并按照指定位置的状态进行恢复
 * getSaveCount	    获取栈中内容的数量(即保存次数)
 *
 *
 *
 * 二、源码
 *
 * // 保存全部状态
 *
 * public int save ()
 * // 根据saveFlags参数保存一部分状态
 * public int save (int saveFlags)
 *
 *
 * SaveFlags
 * 名称	                        简介
 * ALL_SAVE_FLAG	            默认，保存全部状态
 * CLIP_SAVE_FLAG	            保存剪辑区
 * CLIP_TO_LAYER_SAVE_FLAG	    剪裁区作为图层保存
 * FULL_COLOR_LAYER_SAVE_FLAG	保存图层的全部色彩通道
 * HAS_ALPHA_LAYER_SAVE_FLAG	保存图层的alpha(不透明度)通道
 * MATRIX_SAVE_FLAG	            保存Matrix信息( translate, rotate, scale, skew)
 *
 *
 *
 *
 */
public class CanvasSaveRestoreActivity extends BaseCanvasActivity {

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

        canvas.save();
        //1、绘制矩形
        canvas.translate(tempWidth,tempHeight);//将坐标系决定在第一象限

        Rect rect1 = new Rect(0,200,120,0);
        canvas.drawRect(rect1,paint);

        canvas.rotate(180);//旋转180度

//        canvas.restore();

        //2、不恢复坐标系的前提下，再次绘制1中矩形
        canvas.translate(-tempWidth*2,0);//相对绝对坐标，移动到3/4位置
        paint.setColor(Color.GREEN);
        canvas.drawRect(rect1,paint);


        //3、restore坐标系的恢复
        canvas.restore();//调用restore，再次绘制矩形，会发现坐标系恢复到1之前
        paint.setColor(Color.RED);
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
