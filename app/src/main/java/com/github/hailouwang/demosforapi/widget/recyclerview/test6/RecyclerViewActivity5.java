package com.github.hailouwang.demosforapi.widget.recyclerview.test6;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.widget.recyclerview.test6.vh.DockRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity5 extends AppCompatActivity {

    private ViewGroup container;

    private RecyclerView recyclerView;
    private RecyclerViewDockAdapter5 recyclerViewDockAdapter5;

    private DockRecyclerViewItem dockRecyclerViewItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recycler_view_test5);

        container = findViewById(R.id.container);
        dockRecyclerViewItem = new DockRecyclerViewItem(this);
        container.addView(dockRecyclerViewItem.itemView);
        dockRecyclerViewItem.itemView.setVisibility(View.GONE);

        recyclerViewDockAdapter5 = new RecyclerViewDockAdapter5(getRecyclerViewData());

        recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new RecyclerViewDockAdapter5.SpanSize(recyclerViewDockAdapter5));
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(recyclerViewDockAdapter5);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int currentPosition = gridLayoutManager.findFirstVisibleItemPosition();

                View itemView = recyclerView.findChildViewUnder(dx, dy);
                int viewType = recyclerViewDockAdapter5.getItemViewType(currentPosition);

                Log.d("RecyclerViewActivity6", "RecyclerViewActivity6 onScrolled itemView : " + itemView
                        + ", itemView.getBottom() : " + (itemView == null ? 0 : itemView.getBottom())
                        + ", recyclerView.getHeight() : " + recyclerView.getHeight()
                        + ", viewType : " + viewType);

                if (viewType == recyclerViewDockAdapter5.VIEW_TYPE_DOCKER) {
                    if (dockRecyclerViewItem != null && dockRecyclerViewItem.itemView != null) {
                        if (itemView != null && itemView.getTop() <= 0) {
                            dockRecyclerViewItem.itemView.setVisibility(View.VISIBLE);
                        } else {
                            dockRecyclerViewItem.itemView.setVisibility(View.GONE);
                        }
                    }
                }else if (viewType == recyclerViewDockAdapter5.VIEW_TYPE_STRING_DOUBLE) {
                    dockRecyclerViewItem.itemView.setVisibility(View.VISIBLE);
                }else{
                    dockRecyclerViewItem.itemView.setVisibility(View.GONE);
                }
            }
        });
    }

    private List<String> getRecyclerViewData() {
        List<String> data = new ArrayList<>();

        for (int i = 1; i <= 18; i++) {
            data.add("single item : " + i);
        }

        data.add("dock");

        for (int i = 1; i <= 80; i++) {
            data.add("item : " + i);
        }

        return data;
    }
}
