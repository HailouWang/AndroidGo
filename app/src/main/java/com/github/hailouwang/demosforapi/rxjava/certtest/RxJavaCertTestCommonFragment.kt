package com.github.hailouwang.demosforapi.rxjava.certtest

import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.hailouwang.demosforapi.R
import com.github.hailouwang.demosforapi.router.page.MainLooper
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_common_demo.*

/**
 * RxJava 测试全国卷
 * https://www.jianshu.com/p/a3b8ba6314be
 */
class RxJavaCertTestCommonFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.activity_common_demo,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        test1.apply {
            text = "点击测试程式输出"
            setOnClickListener { v: View? ->
                MainLooper.runOnUiThread(Runnable { rxJavaExamination() })
            }
        }

        test2.apply {
            text = "通过 adb logcat -v time -s hlwang 来查看输出"
        }
    }

    private fun rxJavaExamination() {
        Observable.create<String> { emitter ->
            Log.d(
                "hlwang",
                "111 isMainThread : " + (Thread.currentThread() === Looper.getMainLooper()
                    .thread)
            )
            emitter.onNext("第一级主题")
            emitter.onComplete()
        }
            .subscribeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                Log.d(
                    "hlwang",
                    "222 isMainThread : " + (Thread.currentThread() === Looper.getMainLooper()
                        .thread)
                )
            }
            .observeOn(Schedulers.io())
            .doOnNext {
                Log.d(
                    "hlwang",
                    "333 isMainThread : " + (Thread.currentThread() === Looper.getMainLooper()
                        .thread)
                )
            }
            .subscribeOn(Schedulers.newThread())
            .doOnSubscribe {
                Log.d(
                    "hlwang",
                    "444 isMainThread : " + (Thread.currentThread() === Looper.getMainLooper()
                        .thread)
                )
            }
            .observeOn(Schedulers.io())
            .subscribe(object : Observer<Any?> {
                override fun onSubscribe(d: Disposable) {
                    Log.d(
                        "hlwang",
                        "555 isMainThread : " + (Thread.currentThread() === Looper.getMainLooper()
                            .thread)
                    )
                }

                override fun onNext(o: Any) {
                    Log.d(
                        "hlwang",
                        "666 isMainThread : " + (Thread.currentThread() === Looper.getMainLooper()
                            .thread)
                    )
                }

                override fun onError(e: Throwable) {}
                override fun onComplete() {}
            })
    }
}