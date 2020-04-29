package com.github.hailouwang.demosforapi.mmkv;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.hailouwang.demosforapi.R;
import com.tencent.mmkv.MMKV;

import java.util.HashSet;

public class MMKVDemoFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mmkv_demo_fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.crud).setOnClickListener(v -> {
            MMKV kv = MMKV.defaultMMKV();

            String mmpId = kv.mmapID();
            Log.d("hlwang", "MMKVDemoFragment mmpId: " + mmpId);

            kv.encode("bool", true);
            Log.d("hlwang", "MMKVDemoFragment bool: " + kv.decodeBool("bool"));

            kv.encode("int", Integer.MIN_VALUE);
            Log.d("hlwang", "MMKVDemoFragmentint: " + kv.decodeInt("int"));

            kv.encode("long", Long.MAX_VALUE);
            Log.d("hlwang", "MMKVDemoFragment long: " + kv.decodeLong("long"));

            kv.encode("float", -3.14f);
            Log.d("hlwang", "MMKVDemoFragment float: " + kv.decodeFloat("float"));

            kv.encode("double", Double.MIN_VALUE);
            Log.d("hlwang", "MMKVDemoFragment double: " + kv.decodeDouble("double"));

            kv.encode("string", "Hello from mmkv");
            Log.d("hlwang", "MMKVDemoFragment string: " + kv.decodeString("string"));

            byte[] bytes = {'m', 'm', 'k', 'v'};
            kv.encode("bytes", bytes);
            Log.d("hlwang", "MMKVDemoFragment bytes: " + new String(kv.decodeBytes("bytes")));
        });

        view.findViewById(R.id.del).setOnClickListener(v -> {
            MMKV kv = MMKV.defaultMMKV();

            kv.encode("bool", true);
            Log.d("hlwang", "MMKVDemoFragment bool: " + kv.decodeBool("bool"));

            kv.encode("int", Integer.MIN_VALUE);
            Log.d("hlwang", "MMKVDemoFragmentint: " + kv.decodeInt("int"));

            kv.encode("long", Long.MAX_VALUE);
            Log.d("hlwang", "MMKVDemoFragment long: " + kv.decodeLong("long"));

            kv.encode("float", -3.14f);
            Log.d("hlwang", "MMKVDemoFragment float: " + kv.decodeFloat("float"));

            kv.encode("double", Double.MIN_VALUE);
            Log.d("hlwang", "MMKVDemoFragment double: " + kv.decodeDouble("double"));

            kv.encode("string", "Hello from mmkv");
            Log.d("hlwang", "MMKVDemoFragment string: " + kv.decodeString("string"));

            byte[] bytes = {'m', 'm', 'k', 'v'};
            kv.encode("bytes", bytes);
            Log.d("hlwang", "MMKVDemoFragment bytes: " + new String(kv.decodeBytes("bytes")));

        });

        view.findViewById(R.id.multi_file).setOnClickListener(v -> {
            MMKV mmkv = MMKV.mmkvWithID("MyID");
            mmkv.encode("bool", true);
            Log.d("hlwang", "MMKVDemoFragment bool: " + mmkv.decodeBool("bool"));
        });

        view.findViewById(R.id.multi_process).setOnClickListener(v -> {
            MMKV mmkv = MMKV.mmkvWithID("InterProcessKV", MMKV.MULTI_PROCESS_MODE);
            mmkv.encode("bool", false);
            Log.d("hlwang", "MMKVDemoFragment bool: " + mmkv.decodeBool("bool"));
        });

        view.findViewById(R.id.multi_shared_preference).setOnClickListener(v -> {
            //SharedPreferences preferences = getSharedPreferences("myData", MODE_PRIVATE);
            MMKV preferences = MMKV.mmkvWithID("myData");
            // 迁移旧数据
            {
                SharedPreferences old_man = getContext().getSharedPreferences("myData", Context.MODE_PRIVATE);
                preferences.importFromSharedPreferences(old_man);
                old_man.edit().clear().commit();
            }
            // 跟以前用法一样
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("bool", true);
            editor.putInt("int", Integer.MIN_VALUE);
            editor.putLong("long", Long.MAX_VALUE);
            editor.putFloat("float", -3.14f);
            editor.putString("string", "hello, imported");
            HashSet<String> set = new HashSet<String>();
            set.add("W"); set.add("e"); set.add("C"); set.add("h"); set.add("a"); set.add("t");
            editor.putStringSet("string-set", set);
            // 无需调用 commit()
            //editor.commit();
        });
    }
}
