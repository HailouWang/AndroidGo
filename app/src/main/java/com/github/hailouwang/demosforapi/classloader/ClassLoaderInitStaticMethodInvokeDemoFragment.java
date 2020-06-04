package com.github.hailouwang.demosforapi.classloader;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.hailouwang.demosforapi.R;

/**
 * 静态方法，会触发类的初始化吗？
 */
public class ClassLoaderInitStaticMethodInvokeDemoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.class_loader_init_static_method_invoke_demo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.action).setOnClickListener(v -> {
            try {
                Hello hello = null;
                hello.printHello();
            } catch (Exception e) {
                Log.d("hlwang", "Error : " + e.getMessage());
            }
        });
    }
}

class Hello {

    private static Parent parent = new Parent();

    static {
        Log.d("hlwang", "Hello static 初始化代码块 ");
    }

    public Hello() {
        Log.d("hlwang", "Hello 构造方法 ");
    }

    public static void printHello() {
        Log.d("hlwang", "Hello ");
    }
}