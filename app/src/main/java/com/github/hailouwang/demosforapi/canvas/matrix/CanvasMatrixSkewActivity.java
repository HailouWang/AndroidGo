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
public class CanvasMatrixSkewActivity extends BaseCanvasActivity {

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
        canvas.translate(0,0);

        //1、水平错切
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.raw.thumb1);
        Matrix matrix = new Matrix();

        canvas.drawBitmap(bitmap,matrix,paint);

        //
        canvas.translate(tempWidth*2,0);

        matrix.postSkew(0.4f,0);
        canvas.drawBitmap(bitmap,matrix,paint);

        //2、垂直错切
        matrix.reset();
        canvas.translate(-tempWidth*2,tempHeight);

        canvas.drawBitmap(bitmap,matrix,paint);

        canvas.translate(tempWidth*2,0);

        matrix.postSkew(0f,0.4f);
        canvas.drawBitmap(bitmap,matrix,paint);

        //3、垂直\水平错切
        matrix.reset();
        canvas.translate(-tempWidth*2,tempHeight);

        canvas.drawBitmap(bitmap,matrix,paint);

        canvas.translate(tempWidth*2,0);

        matrix.postSkew(0.4f,0.4f);
        canvas.drawBitmap(bitmap,matrix,paint);

        paint.setColor(Color.GREEN);
        canvas.drawCircle(0,0,50,paint);//确定下 0,0
        canvas.drawCircle(-150,-150,50,paint);//确定下 0,0


        //4、
        matrix.reset();
        canvas.translate(-tempWidth*2,tempHeight);

        canvas.drawBitmap(bitmap,matrix,paint);

        canvas.translate(tempWidth*2,0);

        //错切因子
        matrix.setSkew(0.4f,0.4f);
        canvas.drawBitmap(bitmap,matrix,paint);

        paint.setColor(Color.BLUE);
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
