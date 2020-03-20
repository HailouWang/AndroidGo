package com.github.hailouwang.demosforapi.main.data;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.main.ui.DemosForApiActivity;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DemosLibData {

    public static List<Map<String, Object>> getData(Context context, String prefix) {
        List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
        // 1、拿到加载到的所有数据
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(DemosLibConstant.CategoryForDemosLib);
        List<ResolveInfo> listActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        if (listActivities == null || listActivities.size() == 0) {
            return data;
        }
        int currentIndex = 0;
        String prefexWithSlash = prefix;
        if (prefix == null || "".equals(prefix)) {
            prefix = "";
        } else {
            currentIndex = prefix.split("/").length;
            prefexWithSlash = prefix + "/";
        }
        // 2、遍历listActivities列表
        Map<String, Object> itemMap = new HashMap<String, Object>();
        for (ResolveInfo info : listActivities) {
            if (info == null) {
                continue;
            }

            // 3、得到activity的label
            CharSequence activityName = info.loadLabel(context.getPackageManager());
            String label = activityName != null ? activityName.toString() : info.activityInfo.name;
            if (label == null) {
                label = "";
            }

            // 4、合法数据，要么prefix为“”，或者label以prefix为前缀
            if ("".equals(prefix) || label.startsWith(/*prefix*/prefexWithSlash)) {
                // 5、获得即将要展示的Label
                String[] labelArray = label.split("/");
                String nextLabel = labelArray[currentIndex];
                // 6、是否遍历浏览:当prefi按照“/”拆分长度与label的拆分长度一致，则直接跳转到目标Intent
                if (currentIndex == labelArray.length - 1) {
                    // 7、将当前要展示的列表Label以及Intent，保存在Map集合中
                    addItem(data, R.drawable.ic_text_dot, nextLabel, getTargetIntent(info));
                } else {
                    String nextKeywords = "".equals(prefix) ? nextLabel : prefix + "/" + nextLabel;
                    if (itemMap.get(nextKeywords) == null) {
                        itemMap.put(nextKeywords, true);
                        addItem(data, R.drawable.ic_text_multi, nextLabel, getBroswerIntent(context, nextKeywords));
                    }
                }
            }
        }

        Collections.sort(data, sDisplayNameComparator);

        return data;
    }

    private static void addItem(List<Map<String, Object>> listData, int img, String title, Intent intent) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("image", img);
        map.put("title", title);
        map.put("intent", intent);
        // 8、将itemMap数据条目，加入List<Map<String,Object>>中
        listData.add(map);
    }

    private static Intent getBroswerIntent(Context context, String prefix) {
        Intent intent = new Intent();
        intent.setClass(context, DemosForApiActivity.class);
        intent.putExtra(DemosLibConstant.DemosLibPath, prefix);
        return intent;
    }

    private static Intent getTargetIntent(ResolveInfo info) {
        Intent intent = new Intent();
        intent.setClassName(info.activityInfo.packageName, info.activityInfo.name);
        return intent;
    }

    private final static Comparator<Map<String, Object>> sDisplayNameComparator =
            new Comparator<Map<String, Object>>() {
                private final Collator collator = Collator.getInstance();

                public int compare(Map<String, Object> map1, Map<String, Object> map2) {
                    try {
                        int imgRes1 = (int) map1.get("image");
                        int imgRes2 = (int) map2.get("image");

                        if (!(imgRes1 == R.drawable.ic_text_multi && imgRes2 == R.drawable.ic_text_multi)) {
                            if (imgRes1 == R.drawable.ic_text_multi) {
                                return -1;
                            } else if (imgRes2 == R.drawable.ic_text_multi) {
                                return 1;
                            }
                        }
                    } catch (Exception e) {

                    }

                    return collator.compare(map1.get("title"), map2.get("title"));
                }
            };
}