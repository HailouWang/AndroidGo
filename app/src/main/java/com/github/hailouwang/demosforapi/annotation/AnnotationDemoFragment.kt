package com.github.hailouwang.demosforapi.annotation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.hailouwang.demosforapi.R
import kotlinx.android.synthetic.main.annotation_demo.*
import java.lang.reflect.InvocationTargetException

/**
 * https://www.jianshu.com/p/9471d6bcf4cf
 */
class AnnotationDemoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = inflater.inflate(R.layout.annotation_demo, container, false);
        return v;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        demo1.setOnClickListener {
            //获取Son的class对象
            //获取Son的class对象
            val sonClass = Son::class.java
            // 获取Son类上的注解MyTestAnnotation可以执行成功
            // 获取Son类上的注解MyTestAnnotation可以执行成功
            val annotation = sonClass.getAnnotation(MyTestAnnotation::class.java)

            Log.d("hlwang", "获取Inherit 继承的属性  ： " + annotation)
        }

        demo2.setOnClickListener {
            /**
             *  /**是否存在对应 Annotation 对象*/
            public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
            return GenericDeclaration.super.isAnnotationPresent(annotationClass);
            }

            /**获取 Annotation 对象*/
            public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
            Objects.requireNonNull(annotationClass);

            return (A) annotationData().annotations.get(annotationClass);
            }
            /**获取所有 Annotation 对象数组*/
            public Annotation[] getAnnotations() {
            return AnnotationParser.toArray(annotationData().annotations);
            }
             */

            // 获取类注解属性
            var fatherClass = Wang::class.java
            var isAnnotationPresent = fatherClass.isAnnotationPresent(MyTestAnnotation::class.java)

            Log.d("hlwang", "isAnnotationPresent : " + isAnnotationPresent)

            // 获取注解方法
            try {
                var nameField = fatherClass.getDeclaredField("name")
                Log.d("hlwang", "name : " + nameField)

                var valueMethod = fatherClass.getDeclaredMethod("value")
                Log.d("hlwang", "valueMethod : " + valueMethod)
            } catch (e: Exception) {

            }
        }

        demo3.setOnClickListener {
            BankService.TransferMoney(1000.0);
        }

        demo4.setOnClickListener {
            var afterObject = AfterTest()
            var clazz: Class<*>? = afterObject.javaClass
            while (clazz != null) {
                for (method in clazz.declaredMethods) {
                    val ann =
                        method.getAnnotation(AfterPermissionGranted::class.java)
                    if (ann != null) { // Check for annotated methods with matching request code.
                        if (ann.value === 0x123456) { // Method must be void so that we can invoke it
                            // 检查方法签名，是不是void
                            if (method.parameterTypes.size > 0) {
                                Log.d(
                                    "hlwang",
                                    "Error!!! " + "Cannot execute method " + method.name + " because it is non-void method and/or has input parameters."
                                )
                                try { // Make method accessible if private
                                    if (!method.isAccessible) {
                                        method.isAccessible = true
                                    }
                                    method.invoke(afterObject, "自定义参数")
                                } catch (e: IllegalAccessException) {
                                } catch (e: InvocationTargetException) {
                                }
                            }else{
                                try { // Make method accessible if private
                                    if (!method.isAccessible) {
                                        method.isAccessible = true
                                    }
                                    method.invoke(afterObject)
                                } catch (e: IllegalAccessException) {
                                } catch (e: InvocationTargetException) {
                                }
                            }
                        }
                    }
                }
                // 循环检查 父对象
                clazz = clazz.superclass
            }
        }
    }
}