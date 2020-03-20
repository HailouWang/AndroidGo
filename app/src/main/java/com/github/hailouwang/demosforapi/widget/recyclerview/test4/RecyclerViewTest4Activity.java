package com.github.hailouwang.demosforapi.widget.recyclerview.test4;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewTest4Activity extends AppCompatActivity {

    private RecyclerView rvContent;
    private TextView normalHead;
    private int headHeight;
    private LinearLayoutManager linearLayoutManager;
    private int mCurrentPosition = 0;

    private List<RecyclerViewHolderData> dataLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view_test4);
        rvContent = (RecyclerView) findViewById(R.id.rv_content);
        normalHead = (TextView) findViewById(R.id.tv_head);

        dataLists = getDataSize();

        RecyclerViewTest4Adapter adapter = new RecyclerViewTest4Adapter(dataLists, this);
        rvContent.setAdapter(adapter);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvContent.setLayoutManager(linearLayoutManager);
        rvContent.setHasFixedSize(true);
        rvContent.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                // 当recyclerView的滑动改变改变的时候 实时拿到它的高度
                headHeight = normalHead.getHeight();
                Log.d("RecyclerView4", "RecyclerViewTest4Activity onCreate onScrollStateChanged headHeight " + headHeight);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View itemView = linearLayoutManager.findViewByPosition(mCurrentPosition + 1);

                Log.d("RecyclerView4", "RecyclerViewTest4Activity onCreate onScrolled itemView " + itemView);

                if (itemView != null) {
                    // 100      110  10
                    if (itemView.getTop() <= headHeight) {
                        normalHead.setY(-(headHeight - itemView.getTop()));
                    } else {
                        normalHead.setY(0);
                    }
                }
                //拿到当前显示的item的position
                int currentPosition = linearLayoutManager.findFirstVisibleItemPosition();
                if (mCurrentPosition != currentPosition) {
                    mCurrentPosition = currentPosition;
                    normalHead.setY(0);
                    // 拿到当前item的head值,显示在normalHead上
                    normalHead.setText("dock text : " + dataLists.get(currentPosition).text);
                }
            }
        });
    }

    public List<RecyclerViewHolderData> getDataSize() {
        final List<RecyclerViewHolderData> data = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            RecyclerViewHolderData holder = new RecyclerViewHolderData();
            holder.text = "item : " + i;
            holder.uri = R.mipmap.image1;

            data.add(holder);
        }

        return data;
    }
}
