package com.github.hailouwang.demosforapi.widget.recyclerview.test8.holder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.github.hailouwang.demosforapi.R

class SimpleTextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val mTv: TextView = itemView.findViewById(R.id.textView) as TextView
}