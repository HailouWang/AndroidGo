package com.github.hailouwang.demosforapi.widget.recyclerview.test1;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.main.data.DemosLibConstant;
import com.github.hailouwang.demosforapi.main.data.DemosLibData;
import com.github.hailouwang.demosforapi.main.ui.DemosForApiAdapter;
import com.github.hailouwang.demosforapi.widget.recyclerview.test1.vh.DockRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity1 extends AppCompatActivity {

    private ActionBar actionBar;

    private RecyclerView recyclerView;
    private RecyclerViewDockAdapter1 recyclerViewDockAdapter1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_demos_lib);

        actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerViewDockAdapter1 = new RecyclerViewDockAdapter1(getRecyclerViewData());
        recyclerViewDockAdapter1.addFooterView(this);

        recyclerView.setAdapter(recyclerViewDockAdapter1);
    }

    private List<String> getRecyclerViewData() {
        List<String> data = new ArrayList<>();

        for (int i = 1; i <= 8; i++) {
            data.add("item : " + i);
        }

        return data;
    }
}
