package com.github.hailouwang.demosforapi.router.page.testservice;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.PathReplaceService;

@Route(path = "/service/androidGoPathReplaceService")
public class AndroidGoPathReplaceService implements PathReplaceService {

    @Override
    public String forString(String path) {
        Log.d("hlwang", "AndroidGoPathReplaceService forString path : " + path);
        if ("/test/activity2".equals(path)) {
            return "/test/activity1";
        }
        return path;
    }

    @Override
    public Uri forUri(Uri uri) {
        Log.d("hlwang", "AndroidGoPathReplaceService forUri uri : " + uri);
        return uri;
    }

    @Override
    public void init(Context context) {

    }
}
