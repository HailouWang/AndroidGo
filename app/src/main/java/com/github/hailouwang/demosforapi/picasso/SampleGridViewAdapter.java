package com.github.hailouwang.demosforapi.picasso;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.github.hailouwang.demosforapi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.widget.ImageView.ScaleType.CENTER_CROP;

final class SampleGridViewAdapter extends BaseAdapter {
  private final Context context;
  private final List<String> urls = new ArrayList<>();

  SampleGridViewAdapter(Context context) {
    this.context = context;

    // Ensure we get a different ordering of images on each run.
    Collections.addAll(urls, Data.URLS);
    Collections.shuffle(urls);

    // Triple up the list.
    ArrayList<String> copy = new ArrayList<>(urls);
    urls.addAll(copy);
    urls.addAll(copy);
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    SquaredImageView view = (SquaredImageView) convertView;
    if (view == null) {
      view = new SquaredImageView(context);
      view.setScaleType(CENTER_CROP);
    }

    // Get the image URL for the current position.
    String url = getItem(position);

    // Trigger the download of the URL asynchronously into the image view.
    Picasso.get() //
        .load(url) //加载图片的URL
        .placeholder(R.drawable.placeholder) //默认替换图片
        .error(R.drawable.error) //加载异常图片
        .fit() //【wanghailu】会调整下载的图片大小，适应ImageView。缺点会延长request执行时间，需要等待ImageView返回
        .tag(context) //TAG用于管理 paused, resumed,or canceled.
        .into(view); //设定Target

    return view;
  }

  @Override public int getCount() {
    return urls.size();
  }

  @Override public String getItem(int position) {
    return urls.get(position);
  }

  @Override public long getItemId(int position) {
    return position;
  }
}
