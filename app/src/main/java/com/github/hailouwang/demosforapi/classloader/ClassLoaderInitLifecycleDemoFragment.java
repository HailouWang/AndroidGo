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

public class ClassLoaderInitLifecycleDemoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.class_loader_init_lifecycle_demo, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.action).setOnClickListener(v -> {

            /**
             *  首次：
             *
             * D/hlwang  (12225): ParentStaticObj 构造！！！
             * D/hlwang  (12225): Parent 静态方法！！！
             * D/hlwang  (12225): ChildStaticObj 构造！！！
             * D/hlwang  (12225): Child 静态方法！！！
             * D/hlwang  (12225): ParentObj 构造！！！
             * D/hlwang  (12225): Parent 构造方法！！！
             * D/hlwang  (12225): ChildObj 构造！！！
             * D/hlwang  (12225): Child 构造方法！！！
             *
             *
             * 二次点击
             * 
             * 
             *  D/hlwang  (12225): ParentObj 构造！！！
             *  D/hlwang  (12225): Parent 构造方法！！！
             *  D/hlwang  (12225): ChildObj 构造！！！
             *  D/hlwang  (12225): Child 构造方法！！！
             */
            Child child = new Child();
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}

class Parent {

    static {
        Log.d("hlwang", "Parent 静态方法！！！");
    }

    private static ParentStaticObj parentStaticObj = new ParentStaticObj();
    private ParentObj parentObj = new ParentObj();

    public Parent() {
        Log.d("hlwang", "Parent 构造方法！！！");
    }
}

class ParentStaticObj {
    public ParentStaticObj() {
        Log.d("hlwang", "ParentStaticObj 构造！！！");
    }
}

class ParentObj {
    public ParentObj() {
        Log.d("hlwang", "ParentObj 构造！！！");
    }
}

class Child extends Parent {
    private static ChildStaticObj childStaticObj = new ChildStaticObj();
    private ChildObj childObj = new ChildObj();

    static {
        Log.d("hlwang", "Child 静态方法！！！");
    }

    public Child() {
        Log.d("hlwang", "Child 构造方法！！！");
    }
}

class ChildStaticObj {
    public ChildStaticObj() {
        Log.d("hlwang", "ChildStaticObj 构造！！！");
    }
}

class ChildObj {
    public ChildObj() {
        Log.d("hlwang", "ChildObj 构造！！！");
    }
}