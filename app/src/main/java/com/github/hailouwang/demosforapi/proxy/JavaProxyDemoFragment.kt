package com.github.hailouwang.demosforapi.proxy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.hailouwang.demosforapi.R
import kotlinx.android.synthetic.main.proxy_demo.*
import java.lang.reflect.Proxy

/**
 * http://a.codekk.com/detail/Android/Caij/%E5%85%AC%E5%85%B1%E6%8A%80%E6%9C%AF%E7%82%B9%E4%B9%8B%20Java%20%E5%8A%A8%E6%80%81%E4%BB%A3%E7%90%86
 * 动态代理：https://www.jianshu.com/p/820645faff4f
 */
class JavaProxyDemoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.proxy_demo, container, false)
        return view;
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        // 代理类在程序运行前不存在、运行时由程序动态生成的代理方式称为动态代理。
        demo1.setOnClickListener {
            // create proxy instance
            // create proxy instance
            val timingInvocationHandler =
                TimingInvocationHandler(OperateImpl())
            val operate = Proxy.newProxyInstance(
                Operate::class.java.classLoader,
                arrayOf<Class<*>>(Operate::class.java),
                timingInvocationHandler
            ) as Operate

            Log.d("hlwang", "JavaProxyDemoFragment 动态代理类：${operate.javaClass}")

            // call method of proxy instance
            // call method of proxy instance
            operate.operateMethod1()
            println()
            operate.operateMethod2()
            println()
            operate.operateMethod3()
        }

        demo2.setOnClickListener {
            val timingInvocationHandler =
                TimingInvocationHandler(OperateImpl())
            val operate = Proxy.newProxyInstance(
                Operate::class.java.classLoader,
                arrayOf<Class<*>>(Operate::class.java),
                timingInvocationHandler
            ) as Operate

            // print info of proxy class
            Log.d("hlwang", "proxy class is: " + operate::class.java.getName())
            Log.d("hlwang", "\r\nsuper class of proxy class is: " + operate::class.java.getName());
            Log.d("hlwang", "\r\ninterfaces of proxy class are: " + operate::class)

            for (inter in operate::class.java.interfaces) {
                Log.d("hlwang", "interfaces : " + inter.getName());
            }
            Log.d("hlwang", "\r\nmethods of proxy class are: ")
            for (method in operate::class.java.getMethods()) {
                Log.d("hlwang", "method : " + method.getName());
            }
        }

        /**
         *
         * Retrofit 也是使用动态代理来实现的
         *
        public <T> T create(final Class<T> service) {
        Utils.validateServiceInterface(service);
        if (validateEagerly) {
        eagerlyValidateMethods(service);
        }
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[] { service },
        new InvocationHandler() {
        private final Platform platform = Platform.get();
        private final Object[] emptyArgs = new Object[0];

        @Override public @Nullable Object invoke(Object proxy, Method method,
        @Nullable Object[] args) throws Throwable {
        // If the method is a method from Object then defer to normal invocation.
        if (method.getDeclaringClass() == Object.class) {
        return method.invoke(this, args);
        }
        if (platform.isDefaultMethod(method)) {
        return platform.invokeDefaultMethod(method, service, proxy, args);
        }
        return loadServiceMethod(method).invoke(args != null ? args : emptyArgs);
        }
        });
        }

         */
    }
}