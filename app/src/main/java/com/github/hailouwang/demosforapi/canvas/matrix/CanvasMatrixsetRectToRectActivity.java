package com.github.hailouwang.demosforapi.canvas.matrix;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;

import com.github.hailouwang.demosforapi.Tags;
import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

/**
 * Matrix.ScaleToFit 等比例放大或则缩小，有一方达到Rect区域，则停止。
 *
 */
public class CanvasMatrixsetRectToRectActivity extends BaseCanvasActivity {

    //填充
    final Paint   mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //边距 矩形 颜色
    final Paint   mHairPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //文字
    final Paint   mLabelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    RectF mDestRectF = new RectF(0,0,100,100);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPaint.setColor(Color.BLUE);
        mHairPaint.setStyle(Paint.Style.STROKE);
        mLabelPaint.setTextSize(56);
    }

    @Override
    protected void onSelfDraw(Canvas canvas,int width,int height) {


        super.onSelfDraw(canvas);
//        Paint paint = getCustomPaint();
//        paint.setStrokeWidth(20);
//        paint.setStyle(Paint.Style.STROKE);

        int tempWidth = width/5,tempHeight = height/5;
        // CanvasMatrixTranslate width:360,height :570
        Log.d(Tags.HLWANG_TAG,"CanvasMatrixTranslate width:"+tempWidth
        +",height :"+tempHeight);

        RectF rect1 = new RectF(0,0,120,40);
        RectF rect2 = new RectF(0,0,40,120);
        RectF rect3 = new RectF(0,0,40,40);
        RectF rect4 = new RectF(0,0,120,120);

        RectF[] srcRectFData = new RectF[]{
                rect1,
                rect2,
                rect3,
                rect4,
        };

        //将画布中心点，移动到0,0
        canvas.translate(0,0);

        canvas.save();
        //1、正常

        for(RectF rectF:srcRectFData){
            drawOpear(canvas,rectF,null);
            canvas.drawRect(mDestRectF,mHairPaint);
            canvas.translate(tempWidth,0);
        }

        canvas.drawText("Normal",0,tempHeight*2/3,mLabelPaint);

        canvas.restore();
        //2、ScaleToFit full
        canvas.translate(0,tempHeight);
        canvas.save();

        Matrix matrix = new Matrix();
        for(RectF rectF:srcRectFData){
            matrix.setRectToRect(rectF,mDestRectF, Matrix.ScaleToFit.FILL);
            drawOpear(canvas,rectF,matrix);

            canvas.drawRect(mDestRectF,mHairPaint);
            canvas.translate(tempWidth,0);
        }

        canvas.drawText("FULL",0,tempHeight*2/3,mLabelPaint);
        canvas.restore();

        //3、ScaleToFit START
        canvas.translate(0,tempHeight);
        canvas.save();

        matrix.reset();
        for(RectF rectF:srcRectFData){
            matrix.setRectToRect(rectF,mDestRectF, Matrix.ScaleToFit.START);
            drawOpear(canvas,rectF,matrix);

            canvas.drawRect(mDestRectF,mHairPaint);
            canvas.translate(tempWidth,0);
        }

        canvas.drawText("START",0,tempHeight*2/3,mLabelPaint);
        canvas.restore();

        //4、ScaleToFit CENTER
        canvas.translate(0,tempHeight);
        canvas.save();

        matrix.reset();
        for(RectF rectF:srcRectFData){
            matrix.setRectToRect(rectF,mDestRectF, Matrix.ScaleToFit.CENTER);
            drawOpear(canvas,rectF,matrix);

            canvas.drawRect(mDestRectF,mHairPaint);
            canvas.translate(tempWidth,0);
        }

        canvas.drawText("CENTER",0,tempHeight*2/3,mLabelPaint);
        canvas.restore();

        //5、ScaleToFit END
        canvas.translate(0,tempHeight);
        canvas.save();

        matrix.reset();
        for(RectF rectF:srcRectFData){
            matrix.setRectToRect(rectF,mDestRectF, Matrix.ScaleToFit.END);
            drawOpear(canvas,rectF,matrix);

            canvas.drawRect(mDestRectF,mHairPaint);
            canvas.translate(tempWidth,0);
        }

        canvas.drawText("END",0,tempHeight*2/3,mLabelPaint);
        canvas.restore();
    }

    private void drawOpear(Canvas canvas,RectF src,Matrix matrix){
        canvas.save();

        canvas.concat(matrix);
        canvas.drawOval(src,mPaint);

        canvas.restore();
    }


    @Override
    protected boolean isDrawHelpLine() {
        return true;
    }

    @Override
    protected int drawHelpLineNumbers() {
        return 5;
    }

}
