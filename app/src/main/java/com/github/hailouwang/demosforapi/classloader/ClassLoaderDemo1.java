package com.github.hailouwang.demosforapi.classloader;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * https://www.jianshu.com/p/7193600024e7
 */
public class ClassLoaderDemo1 extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ClassLoader classLoader = getClass().getClassLoader();
        while (classLoader != null) {
            Log.d("hlwang", "onCreate classLoader : " + classLoader);
            classLoader = classLoader.getParent();
        }

    }

    @Override
    public void onStart() {
        super.onStart();

        ClassLoader classLoader = getActivity().getClassLoader();
        while (classLoader != null) {
            Log.d("hlwang", "onStart classLoader : " + classLoader);
            classLoader = classLoader.getParent();
        }
    }
}
