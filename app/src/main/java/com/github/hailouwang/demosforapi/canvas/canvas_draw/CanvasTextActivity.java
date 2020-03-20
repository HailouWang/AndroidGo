package com.github.hailouwang.demosforapi.canvas.canvas_draw;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;

import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

/**
 * 一、方法
 *
 * // 第一类
 * 第一类只能指定文本基线位置(基线x默认在字符串左侧，基线y默认在字符串下方)。
 * public void drawText (String text, float x, float y, Paint paint)
 * public void drawText (String text, int start, int end, float x, float y, Paint paint)
 * public void drawText (CharSequence text, int start, int end, float x, float y, Paint paint)
 * public void drawText (char[] text, int index, int count, float x, float y, Paint paint)
 *
 * // 第二类
 * 第二类可以分别指定每个文字的位置。
 * public void drawPosText (String text, float[] pos, Paint paint)
 * public void drawPosText (char[] text, int index, int count, float[] pos, Paint paint)
 *
 * // 第三类
 * 第三类是指定一个路径，根据路径绘制文字。
 * public void drawTextOnPath (String text, Path path, float hOffset, float vOffset, Paint paint)
 * public void drawTextOnPath (char[] text, int index, int count, Path path, float hOffset, float vOffset, Paint paint)
 *
 *
 * Paint
 *
 * 标题	    相关方法	                    备注
 * 色彩	    setColor setARGB setAlpha	设置颜色，透明度
 * 大小	    setTextSize	                设置文本字体大小
 * 字体	    setTypeface	                设置或清除字体样式
 * 样式	    setStyle	                填充(FILL),描边(STROKE),填充加描边(FILL_AND_STROKE)
 * 对齐	    setTextAlign	            左对齐(LEFT),居中对齐(CENTER),右对齐(RIGHT)
 * 测量	    measureText	                测量文本大小(注意，请在设置完文本各项参数后调用)
 *
 *
 *
 *
 *
 * 二、源码
 *
 *
 *
 */
public class CanvasTextActivity extends BaseCanvasActivity {

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

        //1、在0，0绘制
        String text = "您好，DemosForApi!!!";
        paint.setTextSize(50);

        canvas.translate(tempWidth,tempHeight);

        canvas.drawText(text,0,0,paint);

        //2、通过drawText来绘制
        canvas.translate(tempWidth*2,0);
        //坐标以文字的左下角作为为准
        canvas.drawText(text,50,50,paint);

        //3、截取字符一段
        canvas.translate(-tempWidth*2,tempHeight);
        canvas.drawText(text,1,3,0,0,paint);

        //4、drawTextOnPath
        canvas.translate(tempWidth*2,0);
        Path path = new Path();
        path.moveTo(10,10);
        path.lineTo(200,200);
        path.lineTo(10,200);

        canvas.drawTextOnPath(text,path,10,10,paint);

        //5、drawTextOnPath
        canvas.translate(-tempWidth*2,tempHeight);
        path.reset();
        path.addArc(new RectF(-200,-200,200,200),-180,180);
        canvas.drawTextOnPath(text,path,70,0,paint);
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
