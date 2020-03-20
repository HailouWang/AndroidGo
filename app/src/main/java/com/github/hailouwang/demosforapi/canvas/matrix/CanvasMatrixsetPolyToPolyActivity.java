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
 *
 *
 *
 * Set the matrix such that the specified src points would map to the
 * specified dst points. The "points" are represented as an array of floats,
 * order [x0, y0, x1, y1, ...], where each "point" is 2 float values.
 *
 * @param src   The array of src [x,y] pairs (points)
 * @param srcIndex Index of the first pair of src values
 * @param dst   The array of dst [x,y] pairs (points)
 * @param dstIndex Index of the first pair of dst values
 * @param pointCount The number of pairs/points to be used. Must be [0..4]
 * @return true if the matrix was set to the specified transformation
 *
 * public boolean setPolyToPoly(float[] src, int srcIndex,
        float[] dst, int dstIndex,
        int pointCount) {
        if (pointCount > 4) {
        throw new IllegalArgumentException();
        }
        checkPointArrays(src, srcIndex, dst, dstIndex, pointCount);
        return native_setPolyToPoly(native_instance, src, srcIndex,
        dst, dstIndex, pointCount);
        }
 *
 */
public class CanvasMatrixsetPolyToPolyActivity extends BaseCanvasActivity {

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

        canvas.drawBitmap(bitmap,matrix,paint);

        //2、平移,只指定一个点，可以实现平移的效果
        canvas.save();
        canvas.translate(tempWidth*2,0);

        float[] src = new float[]{0,0};
        float[] dest = new float[]{0+200,0+200};

        matrix.setPolyToPoly(src,0,dest,0,1);

        canvas.drawBitmap(bitmap,matrix,paint);

        Log.d(Tags.HLWANG_TAG,"CanvasMatrixTranslate matrix:"+matrix.toShortString());
        canvas.restore();

        //3、旋转，指定两个点，一个点指定圆心，一个点指定旋转角度
        matrix.reset();
        canvas.save();
        canvas.translate(0,tempHeight);

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        //图片的中心点作为旋转的中心，前后不变，右上角变化到了下方，所以导致图片旋转了90度。
        src = new float[]{w/2,h/2,w,0};
        dest = new float[]{w/2,h/2,w/2+h/2,h/2+w/2};

        matrix.setPolyToPoly(src,0,dest,0,2);

        canvas.drawBitmap(bitmap,matrix,paint);

        Log.d(Tags.HLWANG_TAG,"CanvasMatrixTranslate matrix:"+matrix.toShortString());
        canvas.restore();

        //4、三个点：错切，一个固定，另外两个移动。
        matrix.reset();
        canvas.save();
        canvas.translate(tempWidth*2,tempHeight);

        //
        src = new float[]{0,0,w,h,0,h};
        dest = new float[]{0,0,w+50,h,0+50,h};

        matrix.setPolyToPoly(src,0,dest,0,3);

        canvas.drawBitmap(bitmap,matrix,paint);

        Log.d(Tags.HLWANG_TAG,"CanvasMatrixTranslate matrix:"+matrix.toShortString());
        canvas.restore();

        //5、四个点：透视，透视就是观察的角度变化了。导致投射到平面上的二维图像变化了。
        matrix.reset();
        canvas.save();
        canvas.translate(0,tempHeight*2);

        //图片的中心点作为旋转的中心，前后不变，右上角变化到了下方，所以导致图片旋转了90度。
        src = new float[]{0,0,w,0,0,h,w,h};
        dest = new float[]{0,0,w+50,0,0+50,h,w,h};

        matrix.setPolyToPoly(src,0,dest,0,4);

        canvas.drawBitmap(bitmap,matrix,paint);

        Log.d(Tags.HLWANG_TAG,"CanvasMatrixTranslate matrix:"+matrix.toShortString());
        canvas.restore();

        //6、两个点：缩放，
//        matrix.reset();
//        canvas.save();
//        canvas.translate(tempWidth*2,tempHeight*2);
//
//        //图片的中心点作为旋转的中心，前后不变，右上角变化到了下方，所以导致图片旋转了90度。
//        src = new float[]{0,0,w/2,h};
//        dest = new float[]{0,0,w/2,h/2};
//
//        matrix.setPolyToPoly(src,0,dest,0,2);
//
//        canvas.drawBitmap(bitmap,matrix,paint);
//
//        Log.d(Utils.HLWANG_TAG,"CanvasMatrixTranslate matrix:"+matrix.toShortString());
//        canvas.restore();
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
