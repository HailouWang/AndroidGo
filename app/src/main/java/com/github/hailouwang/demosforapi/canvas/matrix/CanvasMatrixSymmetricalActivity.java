package com.github.hailouwang.demosforapi.canvas.matrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.Tags;
import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

/**
 *
 */
public class CanvasMatrixSymmetricalActivity extends BaseCanvasActivity {

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

        //1、对称 对称轴 Y = 0;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.raw.thumb1);
        Matrix matrix = new Matrix();

        canvas.drawBitmap(bitmap,matrix,paint);

        //
        canvas.translate(tempWidth*4,bitmap.getHeight());

        float[] floats = new float[]{-1,0,0,0,-1,0,0,0,1};
        matrix.setValues(floats);

        canvas.drawBitmap(bitmap,matrix,paint);

        paint.setColor(Color.GREEN);
        canvas.drawCircle(0,0,50,paint);//确定下 0,0
        canvas.drawCircle(-150,-150,50,paint);//确定下 0,0

        Log.d(Tags.HLWANG_TAG,"CanvasMatrixTranslate matrix:"+matrix.toShortString());
        // [1.0, 0.0, 360.0]
        // [0.0, 1.0, 570.0]
        // [0.0, 0.0, 1.0]



        //2、对称 对称轴 x=0;
        canvas.translate(0,tempHeight - bitmap.getHeight());

        matrix.reset();

        float[] floats1 = new float[]{-1,0,0,0,1,0,0,0,1};
        matrix.setValues(floats1);

        canvas.drawBitmap(bitmap,matrix,paint);

        paint.setColor(Color.RED);
        canvas.drawCircle(0,0,50,paint);//确定下 0,0
        canvas.drawCircle(-150,-150,50,paint);//确定下 0,0

        //3、对称 对称轴 Y = x;画圈的位置是0，0
        matrix.reset();
        canvas.translate(-tempWidth*2,tempHeight*2);

        float[] floats2 = new float[]{0,-1,0,-1,0,0,0,0,1};
        matrix.setValues(floats2);

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
