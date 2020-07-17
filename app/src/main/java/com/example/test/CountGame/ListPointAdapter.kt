package com.example.test.CountGame

import android.content.Context
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test.R
import kotlinx.android.synthetic.main.item_point_list.view.*

class ListPointAdapter(private val context: Context) : ListAdapter<CountActivity.TypePoint, ViewHolder>(
    PostDiffCallback()
){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_point_list,
                p0,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvBot?.text = item.pointBot
        holder.tvHuman?.text = item.pointHuman
    }

}

class PostDiffCallback : DiffUtil.ItemCallback<CountActivity.TypePoint>() {
    override fun areItemsTheSame(oldItem: CountActivity.TypePoint, newItem: CountActivity.TypePoint): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CountActivity.TypePoint, newItem: CountActivity.TypePoint): Boolean {
        return oldItem.pointBot == newItem.pointBot && oldItem.pointHuman == newItem.pointHuman
    }
}


class ViewHolder (view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    val tvBot = view.tv_point_bot
    val tvHuman = view.tv_point_human
}