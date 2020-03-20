package com.github.hailouwang.demosforapi.rxjava;

import android.os.Bundle;
import android.util.Log;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.Tags;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;


public class Demos_Flowable_toList extends Demos_Flowable_BaseActivity {

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

        Flowable flowableFromMap = flowableFromFlatMap.map(new Function<AppInfo,AppInfo>() {

            @Override
            public AppInfo apply(@NonNull AppInfo appInfo) throws Exception {
                appInfo.url = "11111111111111";
                return appInfo;
            }
        });

        Single<List<AppInfo>> single = flowableFromMap.toList();
        Single<List<AppInfo>> singleFromMap = single.map(new Function<List<AppInfo>, List<AppInfo>>() {
            @Override
            public List<AppInfo> apply(@NonNull List<AppInfo> appInfos) throws Exception {
                Log.d(Tags.HLWANG_TAG,"FlowableComplex singleFromMap appInfos:"+appInfos);
                return appInfos;
            }
        });

        //这里对象是List<AppInfo>
        singleFromMap.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d(Tags.HLWANG_TAG,"FlowableComplex toList accept o:"+o);
            }
        });

        flowableFromMap.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d(Tags.HLWANG_TAG,"FlowableComplex fromMap accept o:"+o);
            }
        });


        System.out.println("######################################");

    }
}
