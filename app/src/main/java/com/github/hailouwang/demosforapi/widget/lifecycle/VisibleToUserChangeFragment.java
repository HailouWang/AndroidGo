package com.github.hailouwang.demosforapi.widget.lifecycle;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.widget.lifecycle.core.MonitorShowToUserFragment;

import java.util.ArrayList;
import java.util.List;

public class VisibleToUserChangeFragment extends MonitorShowToUserFragment {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewDockAdapter1;

    // ---------------------   View 视图
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.visible_to_user,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerViewDockAdapter1 = new RecyclerViewAdapter(getRecyclerViewData());

        recyclerView.setAdapter(recyclerViewDockAdapter1);
    }

    private List<String> getRecyclerViewData() {
        List<String> data = new ArrayList<>();

        for (int i = 1; i <= 88; i++) {
            data.add("item : " + i);
        }

        return data;
    }

}
