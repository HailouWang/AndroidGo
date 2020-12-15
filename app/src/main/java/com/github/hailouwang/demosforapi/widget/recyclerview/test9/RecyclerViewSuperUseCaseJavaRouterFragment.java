package com.github.hailouwang.demosforapi.widget.recyclerview.test9;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.R;

import java.util.ArrayList;
import java.util.Collections;

public class RecyclerViewSuperUseCaseJavaRouterFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.page_recyclerview_super_usecase_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Toolbar
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        //设置菜单点击监听
        toolbar.setOnMenuItemClickListener(onMenuItemClick);
        toolbar.inflateMenu(R.menu.page_recyclerview_toolbar_super_usecase_fragment);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        Collections.addAll(mTitle, title);
        //为RecyclerView添加默认动画效果，测试不写也可以
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mRecyclerViewAdapter = new RecyclerViewAdapter(getContext(), mTitle));
    }


    private RecyclerView mRecyclerView;
    //item 显示所需
    private String[] title = {"JAVA", "C", "C++", "C#", "PYTHON", "PHP"
            , ".NET", "JAVASCRIPT", "RUBY", "PERL", "VB", "OC", "SWIFT"
    };
    private ArrayList<String> mTitle = new ArrayList<>();
    private RecyclerViewAdapter mRecyclerViewAdapter;

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {

            switch (menuItem.getItemId()) {
                case R.id.menu_add:
                    //添加模拟数据到第一项
                    mTitle.add(0, "www.lijizhou.com");
                    //RecyclerView列表进行UI数据更新
                    mRecyclerViewAdapter.notifyItemInserted(0);
                    //如果在第一项添加模拟数据需要调用 scrollToPosition（0）把列表移动到顶端（可选）
                    mRecyclerView.scrollToPosition(0);

                    break;

                case R.id.menu_del:
                    //删除模拟数据第一项
                    mTitle.remove(0);
                    //RecyclerView 列表进行UI数据更新
                    mRecyclerViewAdapter.notifyItemRemoved(0);
                    break;

                case R.id.menu_move:
                    //列表中第二项移到第三项 进行UI数据更新
                    mRecyclerViewAdapter.notifyItemMoved(1, 2);
                    break;

                case R.id.menu_addmore:
                    //模拟数据批量添加4条数据
                    mTitle.add(0, "test");
                    mTitle.add(0, "test1");
                    mTitle.add(0, "test2");
                    mTitle.add(0, "test3");
                    //RecyclerView列表进行批量UI数据更新
                    mRecyclerViewAdapter.notifyItemRangeInserted(0, 4);
                    // scrollToPosition（0）作用是把列表移动到顶端
                    mRecyclerView.scrollToPosition(0);
                    break;

            }

            return true;
        }
    };
}
