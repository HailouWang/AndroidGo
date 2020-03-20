package com.github.hailouwang.demosforapi.canvas.path;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Bundle;

import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

/**
 *
 * 贝塞尔曲线是用一系列点来控制曲线状态的，我将这些点简单分为两类：
 * 类型	    作用
 * 数据点	确定曲线的起始和结束位置
 * 控制点	确定曲线的弯曲程度
 *
 */
public class CanvasPathBezier3Activity extends BaseCanvasActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private int mPointColor = Color.BLACK,mHelpLineColor = Color.GRAY,mBezierColor = Color.RED;

    @Override
    protected void onSelfDraw(Canvas canvas,int width,int height) {
        super.onSelfDraw(canvas);
        Paint paint = getCustomPaint();
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);

        int tempWidth = width/4,tempHeight = height/4;

        //将画布中心点，移动到第一个1/4的位置
        canvas.translate(tempWidth,tempHeight);

        int R = 165;
        int radius = 300;
        Path bezierPath1,bezierPath2,bezierPath3,bezierPath4;

        //1、4个贝塞尔曲线点，决定一段圆弧，绘制第一个圆弧
        PointF pointF1_1 = new PointF(-radius,0);
        PointF pointF1_2 = new PointF(-radius,-R);
        PointF pointF1_3 = new PointF(0-R,-radius);
        PointF pointF1_4 = new PointF(0,-radius);
        PointF[] pointFs1 = new PointF[]{pointF1_1,pointF1_2,pointF1_3,pointF1_4};

        bezierPath1 = initDraw(canvas,paint,pointFs1);

        //2、4个贝塞尔曲线点，决定一段圆弧，绘制第二个圆弧

        canvas.translate(tempWidth*2,0);

        PointF pointF2_1 = new PointF(radius,0);
        PointF pointF2_2 = new PointF(radius,-R);
        PointF pointF2_3 = new PointF(0+R,-radius);
        PointF pointF2_4 = new PointF(0,-radius);
        PointF[] pointFs2 = new PointF[]{pointF2_1,pointF2_2,pointF2_3,pointF2_4};

        bezierPath2 = initDraw(canvas,paint,pointFs2);

        //3、4个贝塞尔曲线点，决定一段圆弧，绘制第三个圆弧

        canvas.translate(-tempWidth*2,tempHeight*2);

        PointF pointF3_1 = new PointF(-radius,0);
        PointF pointF3_2 = new PointF(-radius,R);
        PointF pointF3_3 = new PointF(0-R,radius);
        PointF pointF3_4 = new PointF(0,radius);
        PointF[] pointFs3 = new PointF[]{pointF3_1,pointF3_2,pointF3_3,pointF3_4};

        bezierPath3 = initDraw(canvas,paint,pointFs3);

        //4、4个贝塞尔曲线点，决定一段圆弧，绘制第四个圆弧

        canvas.translate(tempWidth*2,0);

        PointF pointF4_1 = new PointF(radius,0);
        PointF pointF4_2 = new PointF(radius,R);
        PointF pointF4_3 = new PointF(0+R,radius);
        PointF pointF4_4 = new PointF(0,radius);
        PointF[] pointFs4 = new PointF[]{pointF4_1,pointF4_2,pointF4_3,pointF4_4};

        bezierPath4= initDraw(canvas,paint,pointFs4);


        //5、4个贝塞尔曲线点，决定一段圆弧，合并

        canvas.translate(-tempWidth,-tempHeight);
        paint.setStrokeWidth(4);
        paint.setColor(mBezierColor);

        canvas.drawPath(bezierPath1,paint);
        canvas.drawPath(bezierPath2,paint);
        canvas.drawPath(bezierPath3,paint);
        canvas.drawPath(bezierPath4,paint);

    }

    private Path initDraw(Canvas canvas,Paint paint,PointF[] pointFs){
        //asset pointFs isValid
        Path path = null;
        for(PointF pointF : pointFs){
            //1、绘制辅助点
            paint.setStrokeWidth(20);
            paint.setColor(mPointColor);
            canvas.drawPoint(pointF.x,pointF.y,paint);
            if(path==null){
                path = new Path();
                path.moveTo(pointF.x,pointF.y);
            }
            //2、绘制辅助线
            path.lineTo(pointF.x,pointF.y);
        }
        paint.setStrokeWidth(4);
        paint.setColor(mHelpLineColor);
        canvas.drawPath(path,paint);

        //绘制贝塞尔曲线
        Path bezier1 = new Path();
        bezier1.moveTo(pointFs[0].x,pointFs[0].y);

        bezier1.cubicTo(pointFs[1].x,pointFs[1].y,pointFs[2].x,pointFs[2].y,pointFs[3].x,pointFs[3].y);
        paint.setColor(mBezierColor);
        canvas.drawPath(bezier1,paint);

        return bezier1;
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