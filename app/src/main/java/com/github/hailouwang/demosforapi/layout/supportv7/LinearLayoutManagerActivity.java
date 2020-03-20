package com.github.hailouwang.demosforapi.layout.supportv7;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.layout.supportv7.util.ConfigToggle;

/**
 * Created by ifei on 2017/9/17.
 */

public class LinearLayoutManagerActivity extends BaseLayoutManagerTestActivity<LinearLayoutManager>{
    private DividerItemDecoration mDividerItemDecoration;

    @Override
    protected LinearLayoutManager createLayoutManager() {
        return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    }

    @Override
    protected void onRecyclerViewInit(RecyclerView recyclerView) {
        mDividerItemDecoration = new DividerItemDecoration(this, mLayoutManager.getOrientation());
        recyclerView.addItemDecoration(mDividerItemDecoration);
    }

    @Override
    ConfigToggle[] createConfigToggles() {
        return new ConfigToggle[]{
                new ConfigToggle(this, "orientation Horz？") {
                    @Override
                    public boolean isChecked() {
                        return mLayoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL;
                    }

                    @Override
                    public void onChange(boolean newValue) {
                        mLayoutManager.setOrientation(newValue ? LinearLayoutManager.HORIZONTAL
                                : LinearLayoutManager.VERTICAL);
                        if (mDividerItemDecoration != null) {
                            mDividerItemDecoration.setOrientation(mLayoutManager.getOrientation());
                        }

                    }
                },
                new ConfigToggle(this, "reverse") {
                    @Override
                    public boolean isChecked() {
                        return mLayoutManager.getReverseLayout();
                    }

                    @Override
                    public void onChange(boolean newValue) {
                        mLayoutManager.setReverseLayout(newValue);
                    }
                },
                new ConfigToggle(this, "ayout_direction") {
                    @Override
                    public boolean isChecked() {
                        return ViewCompat.getLayoutDirection(mRecyclerView) ==
                                ViewCompat.LAYOUT_DIRECTION_RTL;
                    }

                    @Override
                    public void onChange(boolean newValue) {
                        ViewCompat.setLayoutDirection(mRecyclerView, newValue ?
                                ViewCompat.LAYOUT_DIRECTION_RTL : ViewCompat.LAYOUT_DIRECTION_LTR);
                    }
                },
                new ConfigToggle(this, "StackFromEnd") {
                    @Override
                    public boolean isChecked() {
                        return mLayoutManager.getStackFromEnd();
                    }

                    @Override
                    public void onChange(boolean newValue) {
                        mLayoutManager.setStackFromEnd(newValue);
                    }
                }
        };
    }
}