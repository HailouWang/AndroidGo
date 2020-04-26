package com.github.hailouwang.demosforapi.java;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.hailouwang.demosforapi.R;

public class TestJavaFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.simple_list_item,container,false);
        return view;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.d("hlwang","TestJavaFragment finalize");
    }
}
