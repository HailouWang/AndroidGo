package com.github.hailouwang.demosforapi.view.performance.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;

public class SubView extends androidx.appcompat.widget.AppCompatTextView {
    public SubView(Context context) {
        super(context);
    }

    public SubView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SubView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("hlwang", "SubView onMeasure......");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("hlwang", "SubView onLayout......");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("hlwang", "SubView onDraw......");
    }
}
