package com.github.hailouwang.demosforapi.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.main.data.DemosLibData;
import com.github.hailouwang.demosforapi.main.data.DemosLibConstant;

import java.util.Map;

public class DemosForApiActivity extends AppCompatActivity implements DemosForApiAdapter.DemosApiClickListener {
    private ActionBar actionBar;

    private RecyclerView recyclerView;
    private DemosForApiAdapter demosForApiAdapter;

    private View noDataView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demos_lib);

        actionBar = getSupportActionBar();

        noDataView = findViewById(R.id.no_data_empty);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);

        Intent intent = getIntent();
        String path = intent.getStringExtra(DemosLibConstant.DemosLibPath);

        String pathTitle = "";

        if (path == null) {
            path = "";
        } else {
            pathTitle = "路径：" + path;
        }

        if (!TextUtils.isEmpty(path)) {

            if (actionBar != null) {
                actionBar.setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_TITLE);
                actionBar.setDisplayShowHomeEnabled(true);
            }
        } else {
            if (actionBar != null) {
                actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_TITLE);
                actionBar.setDisplayShowHomeEnabled(true);
            }
        }

        demosForApiAdapter = new DemosForApiAdapter(DemosLibData.getData(this, path), pathTitle);
        demosForApiAdapter.setApiClickListener(this);
        recyclerView.setAdapter(demosForApiAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menuId = item.getItemId();
        if (android.R.id.home == menuId) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDemosForApiClick(DemosForApiAdapter demosForApiAdapter, int position, int viewType, DemosForApiAdapter.DemosItemViewHolder demosItemViewHolder, Map<String, Object> data) {
        try {
            Intent intent = new Intent((Intent) data.get("intent"));
            intent.addCategory(DemosLibConstant.CategoryForDemosLib);
            startActivity(intent);
            if (DemosLibConstant.isFinishActivityTask) {
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
