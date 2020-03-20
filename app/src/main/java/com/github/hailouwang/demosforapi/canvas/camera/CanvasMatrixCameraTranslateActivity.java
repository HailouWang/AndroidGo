package com.github.hailouwang.demosforapi.canvas.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;

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

public class CanvasMatrixCameraTranslateActivity extends BaseCanvasActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSelfDraw(Canvas canvas, int width, int height) {
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

        //1、Normal
        canvas.save();

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.raw.thumb1);
        Matrix matrix = new Matrix();

        canvas.drawBitmap(bitmap, matrix, paint);

        canvas.restore();

        //2、Camera  X轴平移

        canvas.save();
        matrix.reset();

        canvas.translate(tempWidth*2, 0);

        Camera camera1 = new Camera();
        camera1.translate(100f, 0, 0);//x,y,z轴

        camera1.getMatrix(matrix);

        canvas.drawBitmap(bitmap, matrix, paint);

        canvas.restore();

        //3、Camera  Y轴平移.3D坐标系与2D坐标系，Y轴方向相反

        canvas.save();
        matrix.reset();

        canvas.translate(0, tempHeight);

        Camera camera2 = new Camera();
        camera2.translate(0, 100, 0);//x,y,z轴

        camera2.getMatrix(matrix);

        canvas.drawBitmap(bitmap, matrix, paint);

        canvas.restore();

        //4、Camera  Z轴平移：沿z轴平移相当于缩放的效果，缩放中心为摄像机所在(x, y)坐标，
        // 当View接近摄像机时，看起来会变大，远离摄像机时，看起来会变小，近大远小。

        canvas.save();
        matrix.reset();

        canvas.translate(tempWidth*2, tempHeight);

        Camera camera3 = new Camera();
        camera3.translate(0, 0, 100);//x,y,z轴

        camera3.getMatrix(matrix);

        canvas.drawBitmap(bitmap, matrix, paint);

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