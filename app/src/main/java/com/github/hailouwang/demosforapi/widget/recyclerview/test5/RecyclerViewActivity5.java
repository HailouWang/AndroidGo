package com.github.hailouwang.demosforapi.widget.recyclerview.test5;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.main.data.DemosLibData;
import com.github.hailouwang.demosforapi.widget.recyclerview.test5.vh.DockRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

        recyclerViewDockAdapter5 = new RecyclerViewDockAdapter5(getRecyclerViewData());

        recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setSpanSizeLookup(new RecyclerViewDockAdapter5.SpanSize(recyclerViewDockAdapter5));
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.setAdapter(recyclerViewDockAdapter5);
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
