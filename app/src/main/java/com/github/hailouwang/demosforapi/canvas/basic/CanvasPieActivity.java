package com.github.hailouwang.demosforapi.canvas.basic;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 save	保存当前画布状态
 restore	回滚到上一次保存的状态
 translate	相对于当前位置位移
 rotate	旋转
 */
public class CanvasPieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<PieView.PieData> pieList = new ArrayList<>();

        PieView.PieData pieData1 = new PieView.PieData(23,Color.YELLOW);
        PieView.PieData pieData2 = new PieView.PieData(56,Color.GREEN);
        PieView.PieData pieData3 = new PieView.PieData(78,Color.BLUE);

        pieList.add(pieData1);
        pieList.add(pieData2);
        pieList.add(pieData3);


        PieView pieView = new PieView(this);
        pieView.setPieDataList(pieList);
        setContentView(pieView);
    }

    static class PieView extends View {

        private int width,height;
        private PieData mDefaultPieData = new PieData(1,Color.RED);
        private Paint mPaint;
        private List<PieData> mPieDataList = new ArrayList<>();
        private float mStartAngle = 0;//default 0

        public PieView(Context context) {
            super(context);
            mPaint = initPaint();
        }

        private Paint initPaint(){
            Paint paint = new Paint();
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(3);
            return paint;
        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            width = w;
            height = h;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            drawPie(canvas);
        }

        private void drawPie(Canvas canvas){

            int x,y,radius;//圆形、半径
            // 将画布坐标原点移动到中心位置
            canvas.translate(width/2,height/2);

            radius = (int)(Math.min(width,height)/2 * 0.8);
            RectF rectF = new RectF(-radius,-radius,radius,radius);


            float sumCount = 0;
            if(mPieDataList.size() == 0){
                mPieDataList.add(mDefaultPieData);
                sumCount = mDefaultPieData.getValue();
            }else{
                for(PieData data:mPieDataList){
                    sumCount += data.getValue();
                }
            }

            float valueAngle = 0;

            for(PieData data : mPieDataList){

                mPaint.setColor(data.getColor());

                valueAngle = ((float)(data.getValue()/sumCount))*360f;
                if(valueAngle+mStartAngle>360){
                    valueAngle = 360 - mStartAngle;
                }

                canvas.drawArc(rectF,mStartAngle,valueAngle,true,mPaint);
                mStartAngle += valueAngle;
            }


        }

        public void setPieDataList(List<PieData> pieDataList) {
            mPieDataList.clear();
            mPieDataList.addAll(pieDataList);
            invalidate();
        }

        static class PieData{
            float value;
            int color;

            public PieData(float value, int color) {
                this.value = value;
                this.color = color;
            }

            public float getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public int getColor() {
                return color;
            }

            public void setColor(int color) {
                this.color = color;
            }
        }

    }

}
