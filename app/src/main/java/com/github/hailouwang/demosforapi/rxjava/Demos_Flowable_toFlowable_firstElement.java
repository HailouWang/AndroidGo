package com.github.hailouwang.demosforapi.rxjava;

import android.os.Bundle;
import android.util.Log;

import com.github.hailouwang.demosforapi.R;
import com.github.hailouwang.demosforapi.Tags;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class Demos_Flowable_toFlowable_firstElement extends Demos_Flowable_BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demos__flowable_complex);

        //案例一
        Flowable flowable1 = Flowable.concat(flowableFromList1,floawableFromList2,floawableFromNull)
                .observeOn(Schedulers.io())
                .filter(new Predicate<List<AppInfo>>() {
                    @Override
                    public boolean test(@NonNull List<AppInfo> appInfos) throws Exception {
                        return appInfos!=null&&!appInfos.isEmpty();
                    }
                });

        Object object = flowable1.firstElement();
        Log.d(Tags.HLWANG_TAG,"FlowableComplex firstElement:"+object);

        Flowable flowable2 = flowable1.firstElement().toFlowable();
        flowable2.subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                Log.d(Tags.HLWANG_TAG,"FlowableComplex toFlowable:"+o);
            }
        });
    }
}
