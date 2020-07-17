package com.example.test.Elevator

import android.content.Context
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test.R
import kotlinx.android.synthetic.main.item_detail_elevator.view.*
import kotlinx.android.synthetic.main.item_point_list.view.*

class ElevatorDetailAdapter(private val context: Context) : ListAdapter<TimeStampData, ViewHolder>(
    PostDiffCallback()
){
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_detail_elevator,
                p0,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.tvFloor?.text = (position + 1).toString()
        holder.tvTimeStamp?.text = item.time
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<TimeStampData>() {
    override fun areItemsTheSame(oldItem: TimeStampData, newItem: TimeStampData): Boolean {
        return oldItem.time == newItem.time
    }

    override fun areContentsTheSame(oldItem: TimeStampData, newItem: TimeStampData): Boolean {
        return oldItem.time == newItem.time
    }
}


class ViewHolder (view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
    val tvTimeStamp = view.tv_time_stamp
    val tvFloor = view.tv_order
}