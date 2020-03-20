package com.github.hailouwang.demosforapi.canvas.path;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.util.Log;

import com.github.hailouwang.demosforapi.Tags;
import com.github.hailouwang.demosforapi.canvas.BaseCanvasActivity;

/**
 *  作用	        相关方法	        备注
 * 移动起点	    moveTo	        移动下一次操作的起点位置
 * 设置终点	    setLastPoint	重置当前path中最后一个点位置，如果在绘制之前调用，效果和moveTo相同
 * 连接直线	    lineTo	        添加上一个点到当前点之间的直线到Path
 * 闭合路径	    close	        连接第一个点连接到最后一个点，形成一个闭合区域
 * 添加内容	    addRect, addRoundRect, addOval, addCircle, addPath, addArc, arcTo	添加(矩形， 圆角矩形， 椭圆， 圆， 路径， 圆弧) 到当前Path (注意addArc和arcTo的区别)
 * 是否为空	    isEmpty	        判断Path是否为空
 * 是否为矩形	    isRect	        判断path是否是一个矩形
 * 替换路径	    set	            用新的路径替换到当前路径所有内容
 * 偏移路径	    offset	        对当前路径之前的操作进行偏移(不会影响之后的操作)
 * 贝塞尔曲线	    quadTo, cubicTo	分别为二次和三次贝塞尔曲线的方法
 * rXxx方法	    rMoveTo, rLineTo, rQuadTo, rCubicTo	    不带r的方法是基于原点的坐标系(偏移量)， rXxx方法是基于当前点坐标系(偏移量)
 * 填充模式	    setFillType, getFillType, isInverseFillType, toggleInverseFillType	设置,获取,判断和切换填充模式
 * 提示方法	    incReserve	    提示Path还有多少个点等待加入(这个方法貌似会让Path优化存储结构)
 * 布尔操作(API19)	op	        对两个Path进行布尔运算(即取交集、并集等操作)
 * 计算边界	    computeBounds	计算Path的边界
 * 重置路径	    reset, rewind	清除Path中的内容 reset不保留内部数据结构，但会保留FillType. rewind会保留内部的数据结构，但不保留FillType
 * 矩阵操作	    transform	    矩阵变换
 *
 *
 * boolean op (Path path, Path.Op op)
 * boolean op (Path path1, Path path2, Path.Op op)
 *
 * // 对 path1 和 path2 执行布尔运算，运算方式由第二个参数指定，运算结果存入到path1中。
 * path1.op(path2, Path.Op.DIFFERENCE);
 *
 * // 对 path1 和 path2 执行布尔运算，运算方式由第三个参数指定，运算结果存入到path3中。
 * path3.op(path1, path2, Path.Op.DIFFERENCE)
 *
 */
public class CanvasPath3Activity extends BaseCanvasActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSelfDraw(Canvas canvas,int width,int height) {
        super.onSelfDraw(canvas);
        Paint paint = getCustomPaint();
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        int tempWidth = width/4,tempHeight = height/4;

        //将画布中心点，移动到第一个1/4的位置
        canvas.translate(0,0);

        //1、lineTo 连接 （100，100，100，200）
        Path path1 = new Path();
        path1.moveTo(100,100);
        path1.lineTo(100,200);
        canvas.drawPath(path1,paint);

        //2、rLineTo连接 （100,100，100，200）

        canvas.translate(tempWidth*2,0);

        Path path2 = new Path();
        path2.moveTo(100,100);
        path2.rLineTo(100,200);
        canvas.drawPath(path2,paint);

        //3、Path.Op.DIFFERENCE：差集，保留path1
        canvas.translate(-tempWidth,tempHeight*2);

        Path path3_1 = new Path();
        Path path3_2 = new Path();

        path3_1.addCircle(-50,0,100, Path.Direction.CW);
        path3_2.addRect(-10,-30,70,90, Path.Direction.CW);
        path3_1.op(path3_2, Path.Op.DIFFERENCE);

        canvas.drawPath(path3_1,paint);

        //4、Path.Op.DIFFERENCE:交集
        canvas.translate(tempWidth,0);

        Path path4_1 = new Path();
        Path path4_2 = new Path();

        path4_1.addCircle(-50,0,100, Path.Direction.CW);
        path4_2.addRect(-10,-30,70,90, Path.Direction.CW);
        path4_1.op(path4_2, Path.Op.INTERSECT);

        canvas.drawPath(path4_1,paint);

        //5、Path.Op.DIFFERENCE：差集：保留path2
        canvas.translate(tempWidth,0);

        Path path5_1 = new Path();
        Path path5_2 = new Path();
        Path path5_3 = new Path();

        path5_1.addCircle(-50,0,100, Path.Direction.CW);
        path5_2.addRect(-10,-30,70,90, Path.Direction.CW);
        path5_3.op(path5_1,path5_2, Path.Op.REVERSE_DIFFERENCE);

        canvas.drawPath(path5_3,paint);

        //6、Path.Op.DIFFERENCE：并集
        canvas.translate(-tempWidth*2,tempHeight);

        Path path6_1 = new Path();
        Path path6_2 = new Path();

        path6_1.addCircle(-50,0,100, Path.Direction.CW);
        path6_2.addRect(-10,-30,70,90, Path.Direction.CW);
        path6_1.op(path6_2, Path.Op.UNION);

        canvas.drawPath(path6_1,paint);

        //7、Path.Op.DIFFERENCE：异或集
        canvas.translate(tempWidth,0);

        Path path7_1 = new Path();
        Path path7_2 = new Path();
        Path path7_3 = new Path();

        path7_1.addCircle(-50,0,100, Path.Direction.CW);
        path7_2.addRect(-10,-30,70,90, Path.Direction.CW);
        path7_3.op(path7_1,path7_2, Path.Op.XOR);

        canvas.drawPath(path7_3,paint);

        RectF bounds = new RectF();
        path7_3.computeBounds(bounds,true);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
        canvas.drawRect(bounds,paint);

        Log.d(Tags.HLWANG_TAG,"CanvasPath3Activity onSelfDraw..."+bounds);

        //8、
        canvas.translate(tempWidth,0);

        Path path8_1 = new Path();
        Path path8_2 = new Path();
        Path path8_3 = new Path();
        Path path8_4 = new Path();

        path8_1.addCircle(0,0,100, Path.Direction.CW);
        path8_2.addRect(0,-100,100,100, Path.Direction.CW);
        path8_3.addCircle(0,-50,50,Path.Direction.CW);
        path8_4.addCircle(0,50,50,Path.Direction.CW);

        path8_1.op(path8_2,Path.Op.DIFFERENCE);
        path8_1.op(path8_3,Path.Op.UNION);
        path8_1.op(path8_4, Path.Op.DIFFERENCE);
        canvas.drawPath(path8_1,paint);
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
