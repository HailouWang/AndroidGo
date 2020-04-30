package com.github.hailouwang.demosforapi.livedata;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.github.hailouwang.demosforapi.R;

public class LiveDataDemoObserveForeverFragment extends Fragment {

    private MutableLiveData<String> liveData = new MutableLiveData<>("初始值");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.live_data_demo_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.livedata01).setOnClickListener(v -> {

        });
//        view.findViewById(R.id.livedata01).setOnClickListener(v -> {
//
//        });
//        view.findViewById(R.id.livedata01).setOnClickListener(v -> {
//
//        });
//        view.findViewById(R.id.livedata01).setOnClickListener(v -> {
//
//        });
//        view.findViewById(R.id.livedata01).setOnClickListener(v -> {
//
//        });
//        view.findViewById(R.id.livedata01).setOnClickListener(v -> {
//
//        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        liveData.observeForever(new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d("hlwang", "LiveDataDemoFragment observeForever value : " + s);
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        liveData.setValue("onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        liveData.setValue("onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        liveData.setValue("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        liveData.setValue("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        liveData.setValue("onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        liveData.setValue("onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        liveData.setValue("onDetach");
    }
}
