package com.example.test

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_warm_up.view.*

class WarmUpAdapter(private val menuList: Array<Menu>,
                    val menuClickListener: (Menu) -> Unit) : androidx.recyclerview.widget.RecyclerView.Adapter<WarmUpViewHolder>() {

    override fun onCreateViewHolder(view: ViewGroup, position: Int): WarmUpViewHolder {
        return WarmUpViewHolder(LayoutInflater.from(view.context).inflate(
                R.layout.item_warm_up,
                view,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    override fun onBindViewHolder(holder: WarmUpViewHolder, position: Int) {
        val item = menuList[position]
        holder.tvTitle.text = item.title
        holder.btnStart.setOnClickListener {
            menuClickListener.invoke(item)
        }
    }
}


class WarmUpViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    val tvTitle = view.tv_title
    val btnStart = view.btn_start
}