package com.github.hailouwang.demosforapi.picasso;

import android.content.Context;
import android.widget.AbsListView;

import com.squareup.picasso.Picasso;

public class SampleScrollListener implements AbsListView.OnScrollListener {
  private final Context context;

  public SampleScrollListener(Context context) {
    this.context = context;
  }

  @Override
  public void onScrollStateChanged(AbsListView view, int scrollState) {
    final Picasso picasso = Picasso.get();
    if (scrollState == SCROLL_STATE_IDLE || scrollState == SCROLL_STATE_TOUCH_SCROLL) {
      picasso.resumeTag(context);//恢复Picasso
    } else {
      picasso.pauseTag(context);//暂停Picasso
    }
  }

  @Override
  public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                       int totalItemCount) {
    // Do nothing.
  }
}
