package com.github.hailouwang.demosforapi.widget.recyclerview.test7;

import android.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.widget.recyclerview.test7.vh.DockMockRecyclerViewItem;
import com.github.hailouwang.demosforapi.widget.recyclerview.test7.vh.DockRecyclerViewItem2;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity2 extends AppCompatActivity {

    private ActionBar actionBar;

    private RecyclerView recyclerView;
    private RecyclerViewDockAdapter2 recyclerViewDockAdapter2;

    private DockRecyclerViewItem2 footer;

    private FrameLayout container;

    private boolean mFooterAttached;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recycler_view_test2);

        actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(@NonNull View view) {
                RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(view);

                if (vh instanceof DockMockRecyclerViewItem) {
                    mFooterAttached = true;
                    onSubViewStateChange();
                }

            }

            @Override
            public void onChildViewDetachedFromWindow(@NonNull View view) {
                RecyclerView.ViewHolder vh = recyclerView.getChildViewHolder(view);

                if (vh instanceof DockMockRecyclerViewItem) {
                    mFooterAttached = false;
                    onSubViewStateChange();
                }

            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                onSubViewStateChange();
            }
        });

        recyclerViewDockAdapter2 = new RecyclerViewDockAdapter2(getRecyclerViewData());
        footer = new DockRecyclerViewItem2(this);
        recyclerViewDockAdapter2.addFooterView(footer);

        recyclerView.setAdapter(recyclerViewDockAdapter2);

        container = findViewById(R.id.dock_container);
        // Register a callback to be invoked when the global layout state or the visibility of views
        //     * within the view tree changes
        container.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //设置footerview高度
                int height = recyclerView.getHeight();
                if (footer != null) {
                    footer.resetViewHeightIfChanged(height);
                }
            }
        });
    }

    private void onSubViewStateChange() {

    }

    private List<String> getRecyclerViewData() {
        List<String> data = new ArrayList<>();

        for (int i = 1; i <= 8; i++) {
            data.add("item : " + i);
        }

        return data;
    }
}
