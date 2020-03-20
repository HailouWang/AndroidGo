package com.github.hailouwang.demosforapi.canvas.matrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.Tags;
import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

/**
 *  当前矩阵*inverse=单位矩阵。
 *
 */
public class CanvasMatrixInvertActivity extends BaseCanvasActivity {

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
        // CanvasMatrixTranslate width:360,height :570
        Log.d(Tags.HLWANG_TAG,"CanvasMatrixTranslate width:"+tempWidth
        +",height :"+tempHeight);

        //将画布中心点，移动到0,0
        canvas.translate(0,0);

        //1、Normal
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.raw.thumb1);
        Matrix matrix = new Matrix();

        int w = bitmap.getWidth(),h=bitmap.getHeight();
        float[] src = new float[]{0,0,0,h,w,h};
        float[] dest = new float[]{0,0,0+70,h,w+70,h};

        matrix.setPolyToPoly(src,0,dest,0,3);

        canvas.drawBitmap(bitmap,matrix,paint);

        //2、反转
        canvas.save();
        canvas.translate(0,tempHeight*2);

        Matrix matrix1 = new Matrix();
        matrix.invert(matrix1);

        canvas.drawBitmap(bitmap,matrix1,paint);

        Log.d(Tags.HLWANG_TAG,"CanvasMatrixTranslate matrix:"+matrix.toShortString());
        canvas.restore();

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
