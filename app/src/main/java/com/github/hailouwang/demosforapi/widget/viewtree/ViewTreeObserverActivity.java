package com.github.hailouwang.demosforapi.widget.viewtree;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.R;

/**
 * 内部类接口	备注
 * ViewTreeObserver.OnPreDrawListener	当视图树将要被绘制时，会调用的接口
 * ViewTreeObserver.OnGlobalLayoutListener	当视图树的布局发生改变或者View在视图树的可见状态发生改变时会调用的接口
 * ViewTreeObserver.OnGlobalFocusChangeListener	当一个视图树的焦点状态改变时，会调用的接口
 * ViewTreeObserver.OnScrollChangedListener	当视图树的一些组件发生滚动时会调用的接口
 * ViewTreeObserver.OnTouchModeChangeListener	当视图树的触摸模式发生改变时，会调用的接口
 */
public class ViewTreeObserverActivity extends AppCompatActivity implements View.OnClickListener {

    private View tv01;
    private View tv02;
    private View tv03;
    private View btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_tree_observer);

        tv01 = findViewById(R.id.tv01);
        tv02 = findViewById(R.id.tv02);
        tv03 = findViewById(R.id.tv03);
        btn = findViewById(R.id.btn);

        Log.d("viewtree", "ViewTreeObserverActivity onCreate tv01 viewWidth : " + tv01.getMeasuredWidth()
                + ", viewHeight : " + tv01.getMeasuredHeight());

        /**
         * 我们应该都遇到过在onCreate()方法里面调用view.getWidth()和view.getHeight()获取到的view的宽高都是0的情况，这是因为在onCreate()里还没有执行测量，需要在onResume()之后才能得到正确的高度，
         *
         * 那么可不可以在onCreate()里就得到宽高呢？答：可以！
         */

        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        tv02.measure(w, h);
        //获得宽高
        int viewWidth = tv02.getMeasuredWidth();
        int viewHeight = tv02.getMeasuredHeight();

        Log.d("viewtree", "ViewTreeObserverActivity onCreate tv02 viewWidth : " + viewWidth
                + ", viewHeight : " + viewHeight);

        //获得ViewTreeObserver
        ViewTreeObserver observer1 = tv03.getViewTreeObserver();
        //注册观察者，监听变化
        observer1.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //判断ViewTreeObserver 是否alive，如果存活的话移除这个观察者
                if (observer1.isAlive()) {
                    observer1.removeGlobalOnLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                        @Override
                        public void onGlobalLayout() {

                        }
                    });
                    //获得宽高
                    int viewWidth = tv03.getMeasuredWidth();
                    int viewHeight = tv03.getMeasuredHeight();

                    Log.d("viewtree", "ViewTreeObserverActivity onCreate tv03 viewWidth : " + viewWidth
                            + ", viewHeight : " + viewHeight);
                }
            }
        });

        //获得ViewTreeObserver
        ViewTreeObserver observer2 = tv03.getViewTreeObserver();
        //注册观察者，监听变化
        observer2.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (observer2.isAlive()) {
                    observer2.removeOnDrawListener(new ViewTreeObserver.OnDrawListener() {
                        @Override
                        public void onDraw() {

                        }
                    });
                }
                //获得宽高
                int viewWidth = tv03.getMeasuredWidth();
                int viewHeight = tv03.getMeasuredHeight();
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("viewtree", "ViewTreeObserverActivity onResume tv01 viewWidth : " + tv01.getMeasuredWidth()
                + ", viewHeight : " + tv01.getMeasuredHeight()
                + " Width : " + tv01.getWidth()
                + ", viewHeight : " + tv01.getHeight());
    }

    @Override
    public void onClick(View v) {

    }
}
