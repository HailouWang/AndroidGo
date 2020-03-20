package com.github.hailouwang.demosforapi.canvas.path;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;

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
 *
 * // 第一类(基本形状)
 * // 圆形
 * public void addCircle (float x, float y, float radius, Path.Direction dir)
 * // 椭圆
 * public void addOval (RectF oval, Path.Direction dir)
 * // 矩形
 * public void addRect (float left, float top, float right, float bottom, Path.Direction dir)
 * public void addRect (RectF rect, Path.Direction dir)
 * // 圆角矩形
 * public void addRoundRect (RectF rect, float[] radii, Path.Direction dir)
 * public void addRoundRect (RectF rect, float rx, float ry, Path.Direction dir)
 *
 *
 * Path.Direction：在添加图形时确定闭合顺序(各个点的记录顺序)
 *
 * 类型	    解释	                翻译
 * CW	    clockwise	        顺时针
 * CCW	    counter-clockwise	逆时针
 *
 *
 */
public class CanvasPathActivity extends BaseCanvasActivity {

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

        //将画布中心点，移动到第一个1/4的位置
        canvas.translate(tempWidth,tempHeight);

        //1、实例化path并绘制，lineTo

        Path path = new Path();
        path.lineTo(300,300);
        path.lineTo(300,0);

        canvas.drawPath(path,paint);

        //2、moveTo

        paint.setColor(Color.RED);

        canvas.translate(tempWidth*2,0);
        path.moveTo(0,300);
        path.lineTo(200,20);
        canvas.drawPath(path,paint);

        //3、setLastPoint

        paint.setColor(Color.GREEN);

        canvas.translate(-tempWidth*2,tempHeight);

        path = new Path();
        path.lineTo(200,200);
        path.setLastPoint(200,100);
        path.lineTo(200,0);

        canvas.drawPath(path,paint);

        //4、close方法
        //close的作用是封闭路径，与连接当前最后一个点和第一个点并不等价。
        //如果连接了最后一个点和第一个点仍然无法形成封闭图形，则close什么 也不做。

        canvas.translate(tempWidth*2,0);

        paint.setColor(Color.BLUE);

        path.close();

        canvas.drawPath(path,paint);

        //5、逆时针，添加addRect
        canvas.translate(-tempWidth*2,tempHeight);

        paint.setColor(Color.BLACK);

        path = new Path();
        //逆时针
        path.addRect(-100f,-100f,100f,100f,Path.Direction.CCW);
        canvas.drawPath(path,paint);

        //6、逆时针，添加addRect，修改最后一个点
        canvas.translate(tempWidth,0);

        paint.setColor(Color.BLUE);

        Path path1 = new Path();
        //逆时针
        path1.addRect(-100,-100,100,100,Path.Direction.CCW);

        path1.setLastPoint(-200,200);

        canvas.drawPath(path1,paint);

        //7、逆时针，添加addRect，修改最后一个点
        canvas.translate(tempWidth,0);

        paint.setColor(Color.RED);

        Path path2 = new Path();
        //顺时针
        path2.addRect(-100,-100,100,100,Path.Direction.CW);

        path2.setLastPoint(-200,200);

        canvas.drawPath(path2,paint);

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
