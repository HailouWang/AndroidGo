package com.dueeeke.dkplayer.fragment.list;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dueeeke.dkplayer.R;
import com.dueeeke.dkplayer.adapter.TikTokListAdapter;
import com.dueeeke.dkplayer.bean.TiktokBean;
import com.dueeeke.dkplayer.fragment.BaseFragment;
import com.dueeeke.dkplayer.util.DataUtil;

import java.util.ArrayList;
import java.util.List;

public class TikTokListFragment extends BaseFragment {

    private List<TiktokBean> data = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private TikTokListAdapter mAdapter;
    private Button mSwitchImpl;

    @Override
    protected int getLayoutResId() {
        return R.layout.dueeeke_fragment_tiktok_list;
    }

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView = findViewById(R.id.rv_tiktok);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        mAdapter = new TikTokListAdapter(data);
        mRecyclerView.setAdapter(mAdapter);
        mSwitchImpl = findViewById(R.id.btn_switch_impl);
        PopupMenu menu = new PopupMenu(getContext(), mSwitchImpl);
        menu.inflate(R.menu.tiktok_impl_menu);
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                mAdapter.setImpl(item.getItemId());
                int itemId = item.getItemId();
                if (itemId == R.id.impl_recycler_view) {
                    mSwitchImpl.setText("RecyclerView");
                } else if (itemId == R.id.impl_vertical_view_pager) {
                    mSwitchImpl.setText("VerticalViewPager");
                } else if (itemId == R.id.impl_view_pager_2) {
                    mSwitchImpl.setText("ViewPager2");
                }
                return false;
            }
        });
        mSwitchImpl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu.show();
            }
        });

        //默认VerticalViewPager
        mAdapter.setImpl(R.id.impl_vertical_view_pager);
        mSwitchImpl.setText("VerticalViewPager");
    }

    @Override
    protected boolean isLazyLoad() {
        return true;
    }

    @Override
    protected void initData() {
        super.initData();
        //模拟请求数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<TiktokBean> tiktokBeans = DataUtil.getTiktokDataFromAssets(getActivity());
                data.addAll(tiktokBeans);

                mRecyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }
}
