package com.github.hailouwang.demosforapi.router.page;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.github.hailouwang.demosforapi.router.page.testinject.TestObj;

/**
 * A simple {@link Fragment} subclass.
 */
@Route(path = "/test/fragment")
public class BlankFragment extends Fragment {

    @Autowired
    String name;

    @Autowired(required = true)
    TestObj obj;

    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        return textView;
    }

}
