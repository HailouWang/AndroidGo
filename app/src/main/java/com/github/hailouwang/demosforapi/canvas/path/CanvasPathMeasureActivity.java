package com.github.hailouwang.demosforapi.canvas.path;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;

import com.github.hailouwang.demosforapi.Tags;
import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

/**
 *
 * 1、构造方法
 *
 * 方法名	释义
 *
 * PathMeasure()	创建一个空的PathMeasure。用这个构造函数可创建一个空的 PathMeasure，
 * 但是使用之前需要先调用 setPath 方法来与 Path 进行关联。被关联的 Path 必须是已经创建好的，
 * 如果关联之后 Path 内容进行了更改，则需要使用 setPath 方法重新关联。
 *
 * PathMeasure(Path path, boolean forceClosed)	创建 PathMeasure 并关联一个指定的Path(Path需要已经创建完成)。
 *
 * 2、公共方法
 *
 * 返回值	方法名	释义
 * void	setPath(Path path, boolean forceClosed)	关联一个Path
 * boolean	isClosed()	是否闭合
 * float	getLength()	获取Path的长度
 * boolean	nextContour()	跳转到下一个轮廓
 * boolean	getSegment(float startD, float stopD, Path dst, boolean startWithMoveTo)	截取片段
 * boolean	getPosTan(float distance, float[] pos, float[] tan)	获取指定长度的位置坐标及该点切线值
 * boolean	getMatrix(float distance, Matrix matrix, int flags)	获取指定长度的位置坐标及该点Matrix
 *
 *
 *
 * 3、getSegment 用于获取Path的一个片段
 *  boolean getSegment (float startD, float stopD, Path dst, boolean startWithMoveTo)
 *
 * 方法各个参数释义：
 *
 * 参数	            作用	                            备注
 * 返回值(boolean)	判断截取是否成功	                true 表示截取成功，结果存入dst中，false 截取失败，不会改变dst中内容
 * startD	        开始截取位置距离 Path 起点的长度	取值范围: 0 <= startD < stopD <= Path总长度
 * stopD	        结束截取位置距离 Path 起点的长度	取值范围: 0 <= startD < stopD <= Path总长度
 * dst	            截取的 Path 将会添加到 dst 中	    注意: 是添加，而不是替换
 * startWithMoveTo	起始点是否使用 moveTo	            用于保证截取的 Path 第一个点位置不变
 *
 *
 * 4、nextContour
 * 我们知道 Path 可以由多条曲线构成，但不论是 getLength ,
 * getgetSegment 或者是其它方法，都只会在其中第一条线段上运行，
 * 而这个 nextContour 就是用于跳转到下一条曲线到方法，如果跳转成功，则返回 true， 如果跳转失败，则返回 false。
 *
 *
 * 5、boolean getPosTan (float distance, float[] pos, float[] tan)
 * 这个方法是用于得到路径上某一长度的位置以及该位置的正切值：
 *
 * 参数	作用	备注
 * 返回值(boolean)	判断获取是否成功	true表示成功，数据会存入 pos 和 tan 中，
 * false 表示失败，pos 和 tan 不会改变
 * distance	距离 Path 起点的长度	取值范围: 0 <= distance <= getLength
 * pos	该点的坐标值	当前点在画布上的位置，有两个数值，分别为x，y坐标。
 * tan	该点的正切值	当前点在曲线上的方向，使用 Math.atan2(tan[1], tan[0]) 获取到正切角的弧度值。
 *
 */
public class CanvasPathMeasureActivity extends BaseCanvasActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSelfDraw(Canvas canvas, int width, int height) {
        super.onSelfDraw(canvas);
        Paint paint = getCustomPaint();
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);

        int tempWidth = width / 4, tempHeight = height / 4;

        //将画布中心点，移动到第一个1/4的位置
        canvas.translate(tempWidth, tempHeight);

        //1、PathMeasure
        Path path1 = new Path();
        path1.moveTo(100, 100);
        path1.lineTo(200, 100);
        path1.lineTo(200, 200);
        path1.lineTo(100, 200);

        PathMeasure pathMeasure1 = new PathMeasure(path1, false);
        PathMeasure pathMeasure2 = new PathMeasure(path1, true);
        //CanvasPathMeasureActivity onSelfDraw value1:300.0,value2:400.0
        Log.d(Tags.HLWANG_TAG, "CanvasPathMeasureActivity onSelfDraw value1:" + pathMeasure1.getLength()
                + ",value2:" + pathMeasure2.getLength());

        path1.lineTo(100, 100);

        canvas.drawPath(path1, paint);

        //2、getSegment
//        canvas.translate(tempWidth*2,0);

        //如果destPath中没有内容
        paint.setColor(Color.RED);
        Path path2 = new Path();
        pathMeasure1.getSegment(50,200,path2,true);
        canvas.drawPath(path2,paint);

        //如果destPath中有内容
        canvas.translate(tempWidth*2,0);
        paint.setColor(Color.GREEN);
        Path path3 = new Path();
        path3.lineTo(-100,-100);
        pathMeasure1.getSegment(50,200,path3,true);
        canvas.drawPath(path3,paint);

        //3、如果startWithMoveTo = false
        // 如果 startWithMoveTo 为 true, 则被截取出来到Path片段保持原状，
        // 如果 startWithMoveTo 为 false，则会将截取出来的 Path
        // 片段的起始点移动到 dst 的最后一个点，以保证 dst 的连续性。

        paint.setColor(Color.BLUE);

        canvas.translate(-tempWidth*2,tempHeight);

        Path path4 = new Path();
        path4.lineTo(-100,-100);
        pathMeasure1.getSegment(50,200,path4,false);
        canvas.drawPath(path4,paint);


        //4、如果startWithMoveTo = false
        // 如果 startWithMoveTo 为 true, 则被截取出来到Path片段保持原状，
        // 如果 startWithMoveTo 为 false，则会将截取出来的 Path
        // 片段的起始点移动到 dst 的最后一个点，以保证 dst 的连续性。

        paint.setColor(Color.BLUE);

        canvas.translate(tempWidth*2,0);

        Path path5 = new Path();
        path5.moveTo(-100,-100);
        path5.lineTo(-100,100);
        path5.lineTo(100,100);

        path5.addRect(0,0,200,-200,Path.Direction.CW);

        PathMeasure pathMeasure4 = new PathMeasure(path5, false);
        Log.d(Tags.HLWANG_TAG,"CanvasPathMeasureActivity ....length :"+pathMeasure4.getLength());
        boolean isNextContor1 = pathMeasure4.nextContour();
        Log.d(Tags.HLWANG_TAG,"CanvasPathMeasureActivity ....isNextContor1 :"+isNextContor1
                +",length :"+pathMeasure4.getLength());

        canvas.drawPath(path5,paint);

        //5、

        paint.setColor(Color.BLUE);
        canvas.translate(-tempWidth*2,tempHeight);

        Path path6 = new Path();
        path6.addCircle(0,0,200, Path.Direction.CW);

        paint.setColor(Color.BLUE);
        canvas.drawPath(path6,paint);

        PathMeasure path6Measure = new PathMeasure(path6,false);
        final float length = path6Measure.getLength();
        Matrix metrics = new Matrix();
        Log.d(Tags.HLWANG_TAG,"CanvasPathMeasureActivity onSelfDraw。。length:"+length);

        for(int i=0;i<length;i+=50){
            float[] pos = new float[2];
            float[] tan = new float[2];
            boolean posTan = path6Measure.getPosTan(i,pos,tan);
            //弧度
            float arcDegree = (float) Math.atan2(tan[1],tan[0]);
            //角度
            float degree = (float) (arcDegree * 180/Math.PI);
            Log.d(Tags.HLWANG_TAG,"CanvasPathMeasureActivity onSelfDraw pos1:"+pos[0]
            +",pos2:"+pos[1]
            +",tan[1]:"+tan[0]
            +",tan[2]:"+tan[1]
            +",arcDegree:"+arcDegree
            +",degree:"+degree);

            paint.setColor(Color.RED);
            RectF rectF = new RectF(pos[0]-40,pos[1]-20,pos[0]+40,pos[1]+20);
            canvas.drawRect(rectF,paint);
        }


        //6、TODO

        paint.setColor(Color.BLUE);
        canvas.translate(tempWidth*2,0);

        Path path7 = new Path();
        path7.addCircle(0,0,200, Path.Direction.CW);

        PathMeasure path7Measure = new PathMeasure(path7,false);

        paint.setColor(Color.BLUE);
        canvas.drawPath(path7,paint);

        Matrix matrix7 = new Matrix();

        for(int i=0;i<length;i+=50){
            float[] pos = new float[2];
            float[] tan = new float[2];
            boolean posTan = path7Measure.getPosTan(i,pos,tan);
            boolean getMatrix = path7Measure.getMatrix(i,matrix7,PathMeasure.TANGENT_MATRIX_FLAG | PathMeasure.POSITION_MATRIX_FLAG);

            Log.d(Tags.HLWANG_TAG,"CanvasPathMeasureActivity onSelfDraw posTan:"+posTan
                    +",getMatrix:"+getMatrix
                    +",tan[1]:"+tan[0]
                    +",tan[2]:"+tan[1]
                    +",pos[1]:"+pos[0]
                    +",pos[2]:"+pos[1]
            );

//            matrix7.preTranslate(pos[0],pos[1]);
            matrix7.postTranslate(canvas.getWidth()*3/4+pos[0],canvas.getHeight()*3/4+pos[1]);

            paint.setColor(Color.RED);
            RectF rectF = new RectF(-40,-20,+40,+20);
            canvas.setMatrix(matrix7);
//            canvas.concat(matrix7);
            canvas.drawRect(rectF,paint);
        }

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
