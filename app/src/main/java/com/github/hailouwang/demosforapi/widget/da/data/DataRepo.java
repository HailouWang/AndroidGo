package com.github.hailouwang.demosforapi.widget.da.data;

import java.util.ArrayList;
import java.util.List;

public class DataRepo {
    public static List<String> getRecyclerViewData() {
        return getRecyclerViewDataWithPrefix("");
    }

    public static List<String> getRecyclerViewDataWithPrefix(String prefix) {
        List<String> data = new ArrayList<>();

        for (int i = 1; i <= 200; i++) {
            data.add(prefix + "item : " + i);
        }

        return data;
    }
}
