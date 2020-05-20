package com.github.hailouwang.demosforapi.widget.autowrapline;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.github.hailouwang.demosforapi.R;

public class AutoLinefeedLayout extends ViewGroup {

    private int mVerticalGap = 0;
    private int mHorizontalGap = 0;
    private int mChildLine = -1;

    public AutoLinefeedLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.autoWrapLineLayout);
        mHorizontalGap = ta.getDimensionPixelSize(R.styleable.autoWrapLineLayout_horizontalGap, 0);
        mVerticalGap = ta.getDimensionPixelSize(R.styleable.autoWrapLineLayout_verticalGap, 0);
        mChildLine = ta.getInteger(R.styleable.autoWrapLineLayout_childCount, 0);
        ta.recycle();
    }

    public AutoLinefeedLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoLinefeedLayout(Context context) {
        this(context, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
        if (heightMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.UNSPECIFIED) {
            final int width = MeasureSpec.getSize(widthMeasureSpec);
            super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(
                    getDesiredHeight(width), MeasureSpec.EXACTLY));
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        layoutHorizontal();
    }

    private void layoutHorizontal() {
        final int count = getChildCount();
        final int lineWidth = getMeasuredWidth() - getPaddingLeft()
                - getPaddingRight();
        int paddingTop = getPaddingTop();
        int childTop = 0;
        int childLeft = getPaddingLeft();

        int availableLineWidth = lineWidth;
        int maxLineHight = 0;

        int lineIndex = 1;

        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (!(child == null || child.getVisibility() == GONE)) {
                final int childWidth = child.getMeasuredWidth();
                final int childHeight = child.getMeasuredHeight();

                if (availableLineWidth < childWidth) {
                    availableLineWidth = lineWidth;
                    paddingTop = paddingTop + maxLineHight + mVerticalGap;
                    childLeft = getPaddingLeft();
                    maxLineHight = 0;

                    if (!isChildLineValid(++lineIndex)) {
                        break;
                    }
                }
                childTop = paddingTop;
                childLeft += mHorizontalGap;
                setChildFrame(child, childLeft, childTop, childWidth,
                        childHeight);
                childLeft += childWidth;
                availableLineWidth = availableLineWidth - childWidth - mHorizontalGap;
                maxLineHight = Math.max(maxLineHight, childHeight);
            }
        }
    }

    private void setChildFrame(View child, int left, int top, int width,
                               int height) {
        child.layout(left, top, left + width, top + height);
    }

    private int getDesiredHeight(int width) {
        final int lineWidth = width - getPaddingLeft() - getPaddingRight();
        int availableLineWidth = lineWidth;
        int totalHeight = getPaddingTop() + getPaddingBottom();
        int lineHeight = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            final int childWidth = child.getMeasuredWidth();
            final int childHeight = child.getMeasuredHeight();
            if (availableLineWidth < childWidth) {
                availableLineWidth = lineWidth;
                totalHeight = totalHeight + lineHeight;
                lineHeight = 0;
            }
            availableLineWidth = availableLineWidth - childWidth;
            lineHeight = Math.max(childHeight, lineHeight);
        }
        totalHeight = totalHeight + lineHeight;
        return totalHeight;
    }

    public void setHorizontalGap(int horizontalGap) {
        this.mHorizontalGap = horizontalGap;
    }

    public void setVerticalGap(int verticalGap) {
        this.mVerticalGap = verticalGap;
    }

    public void setMaxChildLine(int childLine) {
        this.mChildLine = childLine;
    }

    public boolean isChildLineValid(int lineCount) {
        if (mChildLine <= 0) {
            return true;
        }

        return mChildLine >= lineCount;
    }
}
