package com.github.hailouwang.demosforapi.widget.slidegridview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.hailouwang.demosforapi.R

/**
 *
 * Created by linlif on 2020-03-12
 */
class ItemDataViewHolder : RecyclerView.ViewHolder {

    var textView: TextView? = null

    constructor(view: View) : super(view) {

        view.layoutParams.width = getScreenWidth() / 5
        view.layoutParams.height = getScreenWidth() / 5

        textView = view.findViewById<TextView>(R.id.tvTitle)
    }


    fun invalidataView(title: String) {

        textView?.text = title

    }

}