package com.github.hailouwang.demosforapi.widget.recyclerview.test9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private ArrayList<String> mTitle=new ArrayList<>();

    public RecyclerViewAdapter(Context context,ArrayList<String>title){
        mContext=context;
        mTitle=title;
        mLayoutInflater=LayoutInflater.from(context);
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView=(TextView)itemView.findViewById(R.id.tv_text);
        }
    }

    public void remove(int position) {
        mTitle.remove(position);
        notifyItemRemoved(position);
    }

    public void add(String text, int position) {
        mTitle.add(position, text);
        notifyItemInserted(position);
    }

    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(mLayoutInflater.inflate(R.layout.page_recyclerview_item_super_usecase_fragment,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.mTextView.setText(mTitle.get(position));
    }

    @Override
    public int getItemCount() {
        return mTitle==null ? 0 : mTitle.size();
    }
}
