package com.github.hailouwang.demosforapi.layout.supportv7;

import android.view.View;
import android.view.ViewGroup;

import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.layout.supportv7.adapter.SimpleStringAdapter;
import com.github.hailouwang.demosforapi.layout.supportv7.util.ConfigToggle;

/**
 * Created by ifei on 2017/9/17.
 */

public class GridLayoutManagerActivity extends BaseLayoutManagerTestActivity<GridLayoutManager> {
    SimpleStringAdapter mAdapter;

    @Override
    protected GridLayoutManager createLayoutManager() {
        GridLayoutManager lm = new GridLayoutManager(this, 3);
        lm.setReverseLayout(true);
        lm.setSpanSizeLookup(mSpanSizeLookup);
        return lm;
    }

    GridLayoutManager.SpanSizeLookup mSpanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
        @Override
        public int getSpanSize(int position) {
            String item = mAdapter.getValueAt(position);
            return 1 + (Math.abs(item.hashCode()) % mLayoutManager.getSpanCount());
        }
    };

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

    @Override
    protected void scrollToPositionWithOffset(boolean smooth, int position, int offset) {
        if (smooth) {
            super.scrollToPositionWithOffset(smooth, position, offset);
        } else {
            mLayoutManager.scrollToPositionWithOffset(position, offset);
        }
    }

    protected RecyclerView.Adapter createAdapter() {
        mAdapter = new SimpleStringAdapter(this, Cheeses.sCheeseStrings) {
            @Override
            public SimpleStringAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
                final SimpleStringAdapter.ViewHolder vh = super
                        .onCreateViewHolder(parent, viewType);
                vh.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int pos = vh.getAdapterPosition();
                        if (pos == RecyclerView.NO_POSITION) {
                            return;
                        }
                        if (pos + 1 < getItemCount()) {
                            swap(pos, pos + 1);
                        }
                    }
                });
                return vh;
            }
        };
        return mAdapter;
    }
}