package com.github.hailouwang.demosforapi.rxjava;

import android.os.Bundle;
import android.util.Log;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.Tags;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class Demos_Flowable_distinct extends Demos_Flowable_BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demos__flowable_complex);

        //1、flatMap
        final Flowable flowable1 = Flowable.concat(flowableFromList1,floawableFromList2,floawableFromNull)
                .observeOn(Schedulers.io())
                .filter(new Predicate<List<AppInfo>>() {
                    @Override
                    public boolean test(@NonNull List<AppInfo> appInfos) throws Exception {
                        return appInfos!=null&&!appInfos.isEmpty();
                    }
                });

        Flowable flowableFromFlatMap = flowable1.flatMap(new Function<List<AppInfo>,Flowable<AppInfo>>(){

            @Override
            public Flowable<AppInfo> apply(@NonNull List<AppInfo> appInfos) throws Exception {
//                Log.d(Utils.HLWANG_TAG,"FlowableComplex flatMap o:"+appInfos);
                return Flowable.fromIterable(appInfos);
            }
        });

        //2、filter 君正系列 被过滤
        flowableFromFlatMap.filter(new Predicate<AppInfo>() {
            @Override
            public boolean test(@NonNull AppInfo o) throws Exception {
                return !"".equals(o.url)||!"".equals(o.name);
            }
        }).distinct(new Function<AppInfo,String>() {
                    @Override
                    public String apply(@NonNull AppInfo o) throws Exception {
                        Log.d(Tags.HLWANG_TAG,"FlowableComplex distinct-----> o:"+o);
                        return o.url;
                    }
                })
                .subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d(Tags.HLWANG_TAG,"FlowableComplex distinct accept=======> o:"+o);
            }
        });
    }
}
