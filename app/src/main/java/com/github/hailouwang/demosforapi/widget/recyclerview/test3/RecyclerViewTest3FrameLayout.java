package com.github.hailouwang.demosforapi.widget.recyclerview.test3;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class RecyclerViewTest3FrameLayout extends FrameLayout implements NestedScrollingParent3 {

    private static final String TAG = "Nestest3";

    private View footerView;
    private RecyclerView outerRecyclerView;
    private RecyclerView innerRecyclerView;

    private View mTargetView;

    public RecyclerViewTest3FrameLayout(@NonNull Context context) {
        super(context);
    }

    public RecyclerViewTest3FrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerViewTest3FrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RecyclerViewTest3FrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public View getFooterView() {
        return footerView;
    }

    public void setFooterView(View footerView) {
        this.footerView = footerView;
    }

    private int getFooterViewTop() {
        if (footerView == null) {
            return 0;
        }

        return footerView.getTop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        // 级联滑动抖动：DOWN 事件，点击区域分属两个View时，要处理 Fling 与 Down事件之间的冲突问题
        if (footerView != null) {

        }

        if (!(innerRecyclerView.canScrollVertically(1) || innerRecyclerView.canScrollVertically(-1))) {
            if (mTargetView != null) {
                ViewCompat.stopNestedScroll(mTargetView, ViewCompat.TYPE_NON_TOUCH);
                mTargetView = null;
            }
        }

        return super.onInterceptTouchEvent(ev);
    }

    public void setOuterRecyclerView(RecyclerView outerRecyclerView) {
        this.outerRecyclerView = outerRecyclerView;
    }

    public void setInnerRecyclerView(RecyclerView innerRecyclerView) {
        this.innerRecyclerView = innerRecyclerView;
    }

    private int getFirstVisibleItem() {
        int curFisrtItemPos = -1;
        if (innerRecyclerView == null) {

            return curFisrtItemPos;
        }

        RecyclerView.LayoutManager manager = innerRecyclerView.getLayoutManager();
        if (manager instanceof LinearLayoutManager) {

            curFisrtItemPos = ((LinearLayoutManager) manager).findFirstCompletelyVisibleItemPosition();
        } else if (manager instanceof StaggeredGridLayoutManager) {

            int[] temp = new int[2];
            ((StaggeredGridLayoutManager) manager).findFirstCompletelyVisibleItemPositions(temp);
            curFisrtItemPos = temp[0];
        }
        return curFisrtItemPos;
    }

    // Nest method start

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        Log.d(TAG, "onNestedPreScroll parent "
                + "，target ： " + target
                + ", dxConsumed ： " + dxConsumed
                + "，dyConsumed ： " + dyConsumed
                + "，dxUnconsumed ： " + dxUnconsumed
                + "，dyUnconsumed ： " + dyUnconsumed
                + "，type:" + type);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        Log.d(TAG, "MainHomeNewestRootView parent onStartNestedScroll state : " + ((axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0)
                + ", offset is：" + computeVerticalScrollOffset()
                + ", type : " + type);
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {

    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {

    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {

    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {

        // 级联滑动抖动：如果 子RecyclerView为null，那么全部事件交由外部RecyclerView来消费
        if (innerRecyclerView == null) {
            Log.d(TAG, "onNestedFling "
                    + "，velocityX ： " + dx
                    + "，velocityY ： " + dy
                    + "， innerRecyclerView ： " + innerRecyclerView);

            if (outerRecyclerView != null) {
                outerRecyclerView.scrollBy(0, dy);
                consumed[1] = dy;
            }
            return;
        }

        boolean hiddeningTop = dy > 0 && getFooterViewTop() > /*mTitleHeight*/0; // 上滑
        boolean showingTop = (dy < 0 && innerRecyclerView.computeVerticalScrollOffset() <= 0);//下拉
        boolean showingTopEnd = (dy < 0 && (outerRecyclerView == null ? true : outerRecyclerView.computeVerticalScrollOffset() <= 0));//下拉

        if (true) {

            boolean parentViewConsumed = false;
            int firstChild = getFirstVisibleItem();

            final View firstChildWithClidAt = innerRecyclerView.getChildAt(0);
            final int childAdapterPosition = innerRecyclerView.getChildAdapterPosition(firstChildWithClidAt);

            boolean canScrollVertically = innerRecyclerView.canScrollVertically(ViewCompat.SCROLL_AXIS_VERTICAL);

            Log.d(TAG, "onNestedPreScroll parent hiddeningTop : " + hiddeningTop
                    + ", showingTop ： " + showingTop
                    + "，!(getHomePager()!=null && getHomePager().canScrollVertically()) ： " + canScrollVertically
                    + "，velocityX ： " + dx
                    + "，velocityY ： " + dy
                    + ", mFooterView.getTop() : " + getFooterViewTop()
                    + "，consumed:" + consumed
                    + "，target ： " + target
                    + ", innerRecyclerView : " + innerRecyclerView
                    + ", outerRecyclerView : " + outerRecyclerView
                    + ", exRecyclerView getScrollY : " + innerRecyclerView.getScrollY()
                    + ", exRecyclerView.computeVerticalScrollOffset() :" + innerRecyclerView.computeVerticalScrollOffset()
                    + ", parentViewConsumed : " + parentViewConsumed
                    + ", firstChild: " + firstChild
                    + ", childAdapterPosition : " + childAdapterPosition
                    + ", type : " + type
                    + ", showingTopEnd : " + showingTopEnd);
        }

        // Scroll事件，优先外层RecyclerView来处理
        if (type == ViewCompat.TYPE_TOUCH) {

            if (hiddeningTop || showingTop) {

                if (outerRecyclerView != null) {

                    // 级联滑动抖动：mErv 要隐藏顶部或者展示顶部，那么在展示前，需要先将其自身的Fling效果去除掉，不然会存在两种事件同时作用的场景，导致界面抖动
                    ViewCompat.stopNestedScroll(outerRecyclerView, ViewCompat.TYPE_NON_TOUCH);

                    outerRecyclerView.scrollBy(0, dy);
                    consumed[1] = dy;
                }
            } else {

                // 级联滑动抖动：此处可不处理
                ViewCompat.stopNestedScroll(target, ViewCompat.TYPE_NON_TOUCH);
            }

        } else {

            // 级联滑动抖动：Fling 与 Fling之间的冲突，无需处理
            // Fling事件，如果subRecyclerView未处理，父类来处理
            if (hiddeningTop || showingTop) {

                if (outerRecyclerView != null && target != outerRecyclerView) {

                    outerRecyclerView.scrollBy(0, dy);
                    consumed[1] = dy;

                    mTargetView = target;
                }
            } else {
                Log.d(TAG, "stopNestedScrollIfNeeded "
                        + "，velocityX ： " + dx
                        + "，velocityY ： " + dy
                        + ", target : " + target
                        + "， exRecyclerView ： " + innerRecyclerView
                        + ", type : " + type);

                if (type == ViewCompat.TYPE_NON_TOUCH) {
                    final int currOffset = innerRecyclerView.computeVerticalScrollOffset();
                    if ((dy < 0 && currOffset == 0)
                            || (dy > 0 && (currOffset + innerRecyclerView.computeVerticalScrollExtent()) >= innerRecyclerView.computeVerticalScrollRange())) {

                        ViewCompat.stopNestedScroll(innerRecyclerView, ViewCompat.TYPE_NON_TOUCH);
                    }
                }
            }
        }
    }
    // Nest method end
}
