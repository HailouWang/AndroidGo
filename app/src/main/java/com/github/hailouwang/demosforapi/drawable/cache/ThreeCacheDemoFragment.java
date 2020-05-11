package com.github.hailouwang.demosforapi.drawable.cache;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.hailouwang.demosforapi.R;

/**
 * 三级缓存：https://www.jianshu.com/p/2cd59a79ed4a
 */
public class ThreeCacheDemoFragment extends Fragment {

    private View startLoad;
    private ImageView previewImageView;

    private MyBitmapUtils myBitmapUtils;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myBitmapUtils = new MyBitmapUtils();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.three_cache_demo_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        previewImageView = view.findViewById(R.id.imageView);

        startLoad = view.findViewById(R.id.startLoad);
        startLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myBitmapUtils.disPlay(previewImageView,"https://www.baidu.com/img/bd_logo1.png");
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
