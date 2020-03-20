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
 *
 * // 第二类(Path)
 * // path
 * public void addPath (Path src)
 * public void addPath (Path src, float dx, float dy)
 * public void addPath (Path src, Matrix matrix)
 *
 *
 * // 第三类(addArc与arcTo)
 * // addArc：直接添加一个圆弧到path中
 * public void addArc (RectF oval, float startAngle, float sweepAngle)
 * // arcTo：添加一个圆弧到path，如果圆弧的起点和上次最后一个坐标点不相同，就连接两个点
 * public void arcTo (RectF oval, float startAngle, float sweepAngle)
 * public void arcTo (RectF oval, float startAngle, float sweepAngle, boolean forceMoveTo)
 *
 * 参数	        摘要
 * oval	        圆弧的外切矩形。
 * startAngle	开始角度
 * sweepAngle	扫过角度(-360 <= sweepAngle <360)
 * forceMoveTo	是否强制使用MoveTo
 *
 *
 * public void offset (float dx, float dy)
 * public void offset (float dx, float dy, Path dst)
 *
 * dst状态	        效果
 * dst不为空	        将当前path平移后的状态存入dst中，不会影响当前path
 * dat为空(null)	    平移将作用于当前path，相当于第一种方法
 *
 */
public class CanvasPath2Activity extends BaseCanvasActivity {

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

        //1、addPath 、drawPath 、
        Path path = new Path();
        Path src = new Path();

        path.addRect(-200,-200,200,200, Path.Direction.CW);
        src.addCircle(0,0,100, Path.Direction.CW);

        path.addPath(src,0,-200);

        canvas.drawPath(path,paint);

        //2、addArc

        canvas.translate(tempWidth*2,0);

        /**
         * 貌似不支持 负数操作
         */
//        RectF rectF = new RectF(0,0,300,-300);
        RectF rectF = new RectF(0,0,300,300);

        Path path1 = new Path();
        path1.lineTo(100,100);
        path1.addArc(rectF,0,270);
        canvas.drawPath(path1,paint);

        //2、arcTo,圆弧会将最后的点与直线相连

        canvas.translate(-tempWidth*2,tempHeight);

        Path path2 = new Path();
        path2.lineTo(100,100);
        path2.arcTo(rectF,0,270);
        canvas.drawPath(path2,paint);

        //3、isEmpty、 isRect、isConvex、 set 和 offset
        RectF rectF3 = new RectF();

        Path path3 = new Path();
        Log.d(Tags.HLWANG_TAG,"CanvasPath2Activity onSelfDraw path3 before isEmpty :"+path3.isEmpty());
        path3.moveTo(100,100);
        Log.d(Tags.HLWANG_TAG,"CanvasPath2Activity onSelfDraw path3 after isEmpty :"+path3.isEmpty());

        path3.lineTo(200,100);
        path3.lineTo(200,20);
        path3.lineTo(100,20);
        boolean isRect = path3.isRect(rectF3);
        Log.d(Tags.HLWANG_TAG,"CanvasPath2Activity onSelfDraw path3 isRect :"+isRect+",RectF :"+rectF3);

        //set方法
        canvas.translate(tempWidth*2,0);
        Path src3 = new Path();
        src3.addCircle(0,0,100, Path.Direction.CW);

        path3.set(src3);
        canvas.drawPath(path3,paint);

        //4、
        canvas.translate(-tempWidth*2,tempHeight);
        Path path4 = new Path();
        path4.addCircle(0,0,100, Path.Direction.CW);

        canvas.drawPath(path4,paint);

        Path path5 = new Path();
        path4.offset(80,80,path5);
        paint.setColor(Color.RED);
        canvas.drawPath(path5,paint);

        //再次绘制Path4
        canvas.translate(tempWidth,0);
        paint.setColor(Color.BLUE);
        canvas.drawPath(path4,paint);

        path5.offset(80,80);
        paint.setColor(Color.RED);
        canvas.drawPath(path5,paint);

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
