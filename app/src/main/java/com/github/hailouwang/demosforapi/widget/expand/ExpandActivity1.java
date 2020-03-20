package com.github.hailouwang.demosforapi.widget.expand;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.hailouwang.demosforapi.R;

public class ExpandActivity1 extends AppCompatActivity implements View.OnClickListener {

    private ViewGroup topViewContainer;
    private View topArrow;

    private ViewGroup expandViewContainer;

    private boolean isExpand = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.widget_expand_view_test1);

        topViewContainer = findViewById(R.id.top_container);
        topArrow = findViewById(R.id.top_arrow);

        topViewContainer.setOnClickListener(this);

        expandViewContainer = findViewById(R.id.expand_container);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.top_container) {
            toggleExpand();
        }
    }

    private void arrowViewAnimation() {
        if (topArrow != null) {
            ObjectAnimator animator;
            if (!isExpand()) {
                animator = ObjectAnimator.ofFloat(topArrow, "rotation", 0f, 90f);
            } else {
                topArrow.clearAnimation();
                animator = ObjectAnimator.ofFloat(topArrow, "rotation", 90f, 0f);
            }
            animator.setDuration(200);
            animator.start();
        }
    }

    public boolean isExpand() {
        return isExpand;
    }

    /**
     * 折叠view
     */
    public void collapse() {
        isExpand = false;
        expandViewContainer.setVisibility(View.GONE);
    }

    /**
     * 展开view
     */
    public void expand() {
        isExpand = true;
        expandViewContainer.setVisibility(View.VISIBLE);
    }

    public void toggleExpand() {
        arrowViewAnimation();

        if (isExpand()) {
            collapse();
        } else {
            expand();
        }
    }
}
