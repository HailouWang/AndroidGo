package com.github.hailouwang.demosforapi.jvm.hashmap

import android.util.SparseArray
import androidx.fragment.app.Fragment
import java.util.*

/**
 * 1、HashMap ： https://blog.csdn.net/u010687392/article/details/47809295
 * - 数据结构：数字+ 链表
 * - 处理hash冲突的方式：再哈希法，链地址法
 * - 扩容的条件：数据量 > 容量 * 加载因子，默认容量： 16，默认加载因子：0.75 ，默认扩容数据量： 12
 */
class HashMapDemoFragment : Fragment() {
    fun useSparseArray(){
        var arrays:SparseArray<Item> = SparseArray()

        // 1、key 和 value ，分别用数组表示
        /**
         *     private int[] mKeys;
         *     private Object[] mValues;
         */

        // 2、put 和 get，会使用二分查找法

        // 3、key 值必须是 int
    }

    fun useArrayMap(){
        // key 和 value，分别用数组存储

        // put 和 get ，使用二分查找

        // key 不必须 int
    }

    class Item{
        var name:String = "name"
    }
}