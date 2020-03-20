package com.github.hailouwang.demosforapi.canvas.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.Tags;
import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

/**
 * Created by ifei on 2017/8/30.
 *
 * 坐标系	    2D坐标系	    3D坐标系
 * 原点默认位置	左上角	    左上角
 * X 轴默认方向	右	        右
 * Y 轴默认方向	下	        上
 * Z 轴默认方向	无	        垂直屏幕向内
 *
 *
 */

public class CanvasMatrixCameraRotateActivity extends BaseCanvasActivity {

    private float degree = 0;
    private float scale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 获取手机像素密度 （即dp与px的比例）
        scale = getResources().getDisplayMetrics().density;
    }

    @Override
    protected void onSelfDraw(View view, Canvas canvas, int width, int height) {
        super.onSelfDraw(canvas);
        Paint paint = getCustomPaint();
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);

        int tempWidth = width / 4, tempHeight = height / 4;
        // CanvasMatrixTranslate width:360,height :570
        Log.d(Tags.HLWANG_TAG, "CanvasMatrixTranslate width:" + tempWidth
                + ",height :" + tempHeight);

        //将画布中心点，移动到0,0
        canvas.translate(0, 0);

        degree +=10;

        //1、Normal
        canvas.save();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.raw.thumb1);
        Matrix matrix = new Matrix();

        canvas.drawBitmap(bitmap, matrix, paint);

        canvas.restore();

        //2、Camera  X轴旋转

        canvas.save();
        matrix.reset();

        canvas.translate(0,tempHeight);

        Camera camera1 = new Camera();
        camera1.rotateX(degree);

        camera1.getMatrix(matrix);

        fixMatrix(matrix);

        canvas.drawBitmap(bitmap, matrix, paint);

        canvas.restore();

        //3、Camera  Y轴旋转

        canvas.save();
        matrix.reset();

        canvas.translate(tempWidth*2,tempHeight*2);

        Camera camera2 = new Camera();
        camera2.rotateY(degree);

        camera2.getMatrix(matrix);

        fixMatrix(matrix);

        canvas.drawBitmap(bitmap, matrix, paint);

        canvas.restore();

        //4、Camera  Z轴旋转

        canvas.save();
        matrix.reset();

        canvas.translate(0,tempHeight*3);

        Camera camera3 = new Camera();
        camera3.rotateZ(degree);

        camera3.getMatrix(matrix);

        fixMatrix(matrix);

        matrix.setTranslate(tempWidth/2,tempHeight/2);

        canvas.drawBitmap(bitmap, matrix, paint);

        canvas.restore();

        view.postInvalidateDelayed(100);
    }

    private void fixMatrix(Matrix matrix){
        float[] srcFloat = new float[9];
        matrix.getValues(srcFloat);

        Log.d(Tags.HLWANG_TAG,"CanvasMatrixCameraRotation scale :"+scale
            +",srcFloat[6]:"+srcFloat[6]
            +",srcFloat[7]:"+srcFloat[7]);

        srcFloat[6] = srcFloat[6]/scale;
        srcFloat[7] = srcFloat[7]/scale;
        matrix.setValues(srcFloat);
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