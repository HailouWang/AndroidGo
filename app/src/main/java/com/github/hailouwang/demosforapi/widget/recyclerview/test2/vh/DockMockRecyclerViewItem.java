package com.github.hailouwang.demosforapi.widget.recyclerview.test2.vh;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.widget.recyclerview.test1.vh.BaseRecyclerViewAdapterVh1;

public class DockMockRecyclerViewItem extends BaseRecyclerViewAdapterVh1 {
    public DockMockRecyclerViewItem(@NonNull Context context) {
        super(newContentView(context));
    }

    @Override
    public void onBindRecyclerViewHolder(BaseRecyclerViewAdapterVh1 holder, int position, String data) {

    }

    public void resetViewHeightIfChanged(int viewHeight) {
        if (itemView != null) {
            ViewGroup.LayoutParams vglp;
            vglp = itemView.getLayoutParams();
            if (vglp != null && vglp.height != viewHeight) {

                vglp.height = viewHeight;
                itemView.setLayoutParams(vglp);
            }
        }
    }

    private static LinearLayout newContentView(Context context) {
        return newContentView(context, OrientationHelper.VERTICAL);
    }

    private static LinearLayout newContentView(Context context, int orientation) {

        LinearLayout llContent = new LinearLayout(context);
        llContent.setOrientation(orientation == OrientationHelper.VERTICAL ? LinearLayout.VERTICAL : LinearLayout.HORIZONTAL);
        llContent.setLayoutParams(getRvlp(orientation));
        return llContent;
    }

    private static RecyclerView.LayoutParams getRvlp(int orientation) {

        if (orientation == OrientationHelper.VERTICAL) {
            return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        } else {
            return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT, RecyclerView.LayoutParams.MATCH_PARENT);
        }
    }
}
