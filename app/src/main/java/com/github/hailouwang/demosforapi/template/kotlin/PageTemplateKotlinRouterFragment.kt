package com.github.hailouwang.demosforapi.template.kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.github.hailouwang.demosforapi.R

class PageTemplateKotlinRouterFragment : Fragment(){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.test1)?.apply {
            text = "Kotlin Fragment路由模板"
        }

        val textView =
            view.findViewById<TextView>(R.id.test2)
        textView.text = "点击开始测试"
        textView.setOnClickListener { v: View? -> }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_common_demo, container, false)
    }
}