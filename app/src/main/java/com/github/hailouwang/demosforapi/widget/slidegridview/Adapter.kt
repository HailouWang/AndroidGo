package com.github.hailouwang.demosforapi.widget.slidegridview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.hailouwang.demosforapi.R

/**
 *
 * Created by linlif on 2020-03-12
 */

class Adapter : RecyclerView.Adapter<ItemDataViewHolder>() {

    private var data: List<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemDataViewHolder {

        return ItemDataViewHolder(
            LayoutInflater.from(parent.getContext()).inflate(
                R.layout.layout_slide_grid_recycler_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
       return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: ItemDataViewHolder, position: Int) {

        holder.invalidataView(data?.get(position) ?: "")
    }

    fun setDara(data: List<String>) {
        this.data = data
    }


}