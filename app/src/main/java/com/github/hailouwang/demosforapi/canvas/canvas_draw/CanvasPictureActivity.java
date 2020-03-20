package com.github.hailouwang.demosforapi.canvas.canvas_draw;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;

import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

/**
 * 一、方法
 *
 *  public com.example.android.extra.Canvas beginRecording (int width, int height)
 *
 *  public void endRecording ()
 *
 *
 * public void drawPicture (Picture picture)
 *
 * Rect会让绘制内容缩放
 * public void drawPicture (Picture picture, Rect dst)
 *
 * public void drawPicture (Picture picture, RectF dst)
 *
 * 二、源码
 *
 * PictureDrawable.java
 *
 *     public void draw(com.example.android.extra.Canvas canvas) {
 *          if (mPicture != null) {
 *              Rect bounds = getBounds();
 *              canvas.save();
 *              canvas.clipRect(bounds);
 *              canvas.translate(bounds.left, bounds.top);
 *              canvas.drawPicture(mPicture);
 *              canvas.restore();
 *          }
 *     }
 *
 */
public class CanvasPictureActivity extends BaseCanvasActivity {

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

        //将画布中心点，移动到第一个2/4的位置
        canvas.translate(tempWidth,tempHeight);

        //1、设定Picture，但不会绘制
        Picture picture = new Picture();
        Canvas canvasForPic = picture.beginRecording(tempWidth,tempHeight);

        paint.setColor(Color.GREEN);
        Rect rect1 = new Rect(0,-200,120,0);
        canvasForPic.drawRect(rect1,paint);
        paint.setColor(Color.BLUE);
        canvasForPic.drawCircle(0,0,80,paint);
        picture.endRecording();


        //2、通过picture.draw 绘制
        canvas.translate(tempWidth*2,0);
        picture.draw(canvas);

        //3、通过Canvas.drawPicture来绘制

        canvas.translate(-tempWidth*2,tempHeight);
        canvas.drawPicture(picture);

        //4、通过Canvas.drawPicture(Picture picture, Rect dst)来绘制

        Rect rect4 = new Rect(0,-140,60,0);

        canvas.translate(tempWidth*2,0);

        canvas.drawPicture(picture,rect4);

        //5、利用PictureDrawable绘制
        canvas.translate(-tempWidth*2,tempHeight);

        PictureDrawable pictureDrawable = new PictureDrawable(picture);
        /**
         * setBound貌似只能按照Picture中的Canvas设定的有效画面开始截取
         */
        pictureDrawable.setBounds(0,0,40,80);
        pictureDrawable.draw(canvas);
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
