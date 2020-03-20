package com.github.hailouwang.demosforapi.main.ui;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.hailouwang.demosforapi.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemosForApiAdapter extends RecyclerView.Adapter<DemosForApiAdapter.DemosItemViewHolder> {

    private DemosApiClickListener apiClickListener;

    public interface DemosApiClickListener {
        void onDemosForApiClick(DemosForApiAdapter demosForApiAdapter, int position, int viewType, DemosItemViewHolder demosItemViewHolder, Map<String, Object> data);
    }

    private List<Map<String, Object>> demosData = new ArrayList<>();

    public DemosForApiAdapter(List<Map<String, Object>> datas) {
        this(datas, null);
    }

    public DemosForApiAdapter(List<Map<String, Object>> datas, String header) {
        demosData.clear();
        demosData.addAll(datas);
        if (!TextUtils.isEmpty(header)) {
            demosData.add(0, addHeaderValue(header));
        }
    }

    private Map<String, Object> addHeaderValue(String header) {
        HashMap<String, Object> headerMaps = new HashMap<>();
        headerMaps.put("title", header);
        return headerMaps;
    }

    @NonNull
    @Override
    public DemosItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DemosItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DemosItemViewHolder holder, int position) {
        Map<String, Object> data = demosData.get(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (apiClickListener != null) {
                    apiClickListener.onDemosForApiClick(DemosForApiAdapter.this, position, getItemViewType(position), holder, data);
                }
            }
        });
        holder.onBindViewHolder(data, position);
    }

    @Override
    public int getItemCount() {
        return demosData.size();
    }

    public void setApiClickListener(DemosApiClickListener apiClickListener) {
        this.apiClickListener = apiClickListener;
    }

    class DemosItemViewHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView text1;
        private ImageView arrow;

        private Map<String, Object> dataMaps;

        public DemosItemViewHolder(@NonNull View itemView) {
            super(itemView);

            arrow = itemView.findViewById(R.id.arrow);
            img = itemView.findViewById(R.id.img1);
            text1 = itemView.findViewById(R.id.text1);
        }

        public void onBindViewHolder(Map<String, Object> dataMaps, int position) {
            this.dataMaps = dataMaps;

            try {
                int imgRes = (int) dataMaps.get("image");
                img.setBackgroundResource(imgRes);
                img.setVisibility(View.VISIBLE);
            } catch (Exception e) {
                img.setVisibility(View.GONE);
            }

            text1.setText(String.valueOf(dataMaps.get("title")));
        }
    }
}
