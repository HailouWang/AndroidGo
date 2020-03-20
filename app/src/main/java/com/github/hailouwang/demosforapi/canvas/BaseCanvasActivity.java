package com.github.hailouwang.demosforapi.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.Tags;
import com.github.hailouwang.demosforapi.app.lifecycle.util.StatusTracker;

/**
 * Canvas对象的获取方式有三种：
 * 1、通过重写View.onDraw方法获取Canvas对象。
 * 这种方式根据环境还分为两种：一种是普通View的Canvas，还有一种是SurfaceView的Canvas。
 * 两种的主要是区别就是，可以在SurfaceView中定义一个专门的线程来完成画图工作，应用程序不需要等待View的刷图，提高性能。
 * 前面一种适合处理量比较小，帧率比较低的动画方面的绘图，比如说象棋游戏之类的；而后一种主要用在游戏或高品质动画方面的绘图。
 *
 * 2、直接创建一个Canvas对象：
 * Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
 * com.example.android.extra.Canvas c = new com.example.android.extra.Canvas(bitmap);
 * 上面代码创建了一个尺寸是100*100的Bitmap，使用它作为Canvas操作的对象，这时候的Canvas就是使用创建的方式。
 * 当你使用创建的Canvas在bitmap上执行绘制方法后，你还可以将绘制的结果提交给另外一个Canvas，
 * 这样就可以达到两个Canvas协作完成的效果，简化逻辑。
 * 但是android SDK建议使用View.onDraw参数里提供的Canvas就好，没必要自己创建一个新的Canvas对象。
 *
 * 3、调用SurfaceHolder.lockCanvas()也会返回一个Canvas对象，可以在 surfaceView 或 TextureView中使用。
 */
public class BaseCanvasActivity extends AppCompatActivity {

    StatusTracker sStatusTracker = StatusTracker.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sStatusTracker.clear();

        View view = new CustomView(this);
        setContentView(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(Tags.HLWANG_TAG,"BaseCanvasActivity..onClickListener :"
                        +sStatusTracker.getMethodList().toString()
                );
            }
        });
    }

    protected void onSelfDraw(Canvas canvas){}

    protected void onSelfDraw(Canvas canvas,int width,int heitht){
        onSelfDraw(canvas);
    }

    protected void onSelfDraw(View view,Canvas canvas,int width,int heitht){
        onSelfDraw(canvas,width,heitht);
    }

    public Paint getCustomPaint(){
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);
        return paint;
    }

    class CustomView extends View {
        private int width,height;
        public CustomView(Context context) {
            super(context);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            if(isDrawHelpLine()){
                drawHelpLine(canvas);
            }
            BaseCanvasActivity.this.onSelfDraw(this,canvas,width,height);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            sStatusTracker.setStatus(CustomView.class.getName(),"onMeasure");
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            int widthSize = MeasureSpec.getSize(widthMeasureSpec);
            int widthMode = MeasureSpec.getMode(widthMeasureSpec);

            int heightSize = MeasureSpec.getSize(heightMeasureSpec);
            int heightMode = MeasureSpec.getMode(heightMeasureSpec);

            Log.d(Tags.HLWANG_TAG,"BaseCanvasActivity onMeasure widthSize :"+widthSize
            +",widthMode :"+widthMode
            +",heightSize:"+heightSize
            +",heightMode:"+heightMode);
            width = widthSize;
            height = heightSize;
        }

        @Override
        protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
            super.onLayout(changed, left, top, right, bottom);
            sStatusTracker.setStatus(CustomView.class.getName(),"onMeasure");
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            sStatusTracker.setStatus(CustomView.class.getName(),"onSizeChanged");
            Log.d(Tags.HLWANG_TAG,"BaseCanvasActivity onSizeChanged w :"+w
                    +",h :"+h
                    +",oldW:"+oldw
                    +",oldH:"+oldh);
            width = w;
            height = h;
        }

        private void drawHelpLine(Canvas canvas){

            int numbers = drawHelpLineNumbers();

            Paint paint = getCustomPaint();
            paint.setColor(Color.BLACK);

            //横线
            for(int i=1;i<=numbers;i++){
                canvas.drawLine(0,height*i/numbers,width,height*i/numbers,paint);
            }

            //竖线
            for(int j=1;j<=numbers;j++){
                canvas.drawLine(width*j/numbers,0,width*j/numbers,height,paint);
            }
        }

    }

    protected boolean isDrawHelpLine(){
        return false;
    }

    protected int drawHelpLineNumbers(){
        return 0;
    }

}
