package com.github.hailouwang.demosforapi.canvas.matrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

/**
 *
 */
public class CanvasMatrixRotateActivity extends BaseCanvasActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSelfDraw(Canvas canvas,int width,int height) {
        super.onSelfDraw(canvas);
        Paint paint = getCustomPaint();
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);

        int tempWidth = width/4,tempHeight = height/4;

        //将画布中心点，移动到0,0
        canvas.translate(tempWidth,0);

        //1、旋转
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.raw.thumb1);
        Matrix matrix = new Matrix();

        canvas.drawBitmap(bitmap,matrix,paint);

        //2、旋转
        matrix.reset();
        canvas.translate(0,tempHeight);
        matrix.postRotate(45);

        canvas.drawBitmap(bitmap,matrix,paint);

        paint.setColor(Color.RED);
        canvas.drawCircle(0,0,50,paint);//确定下 0,0
        canvas.drawCircle(-150,-150,50,paint);//确定下 0,0

        //3、按照中心点旋转
        matrix.reset();
        canvas.translate(tempWidth*2,0);

        matrix.postRotate(45,bitmap.getWidth()/2,bitmap.getHeight()/2);

        canvas.drawBitmap(bitmap,matrix,paint);

        paint.setColor(Color.BLUE);
        canvas.drawCircle(0,0,50,paint);//确定下 0,0
        canvas.drawCircle(-150,-150,50,paint);//确定下 0,0


        //4、按照中心点旋转
        matrix.reset();
        canvas.translate(-tempWidth*2,tempHeight*2);

        //旋转180，sin180 = 0；cos180 = -1;
        matrix.setSinCos(0,-1);

        canvas.drawBitmap(bitmap,matrix,paint);

        paint.setColor(Color.GREEN);
        canvas.drawCircle(0,0,50,paint);//确定下 0,0
        canvas.drawCircle(-150,-150,50,paint);//确定下 0,0

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
