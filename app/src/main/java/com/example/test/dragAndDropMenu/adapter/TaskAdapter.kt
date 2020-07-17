package com.example.todayido.home.adapter

import android.util.Log
import android.view.MenuItem
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.dragAndDropMenu.model.MenuItemData
import com.example.test.dragAndDropMenu.viewHolder.CardTaskView
import com.example.todayido.baseAndUtils.RecyclerTouchHelperListener

import com.example.todayido.home.viewHolder.CardTaskHolder
import com.example.todayido.home.viewHolder.TaskBaseHolder
import java.lang.Boolean.FALSE

import java.util.*
import kotlin.collections.ArrayList

class TaskAdapter(
    private var listTaskData: ArrayList<MenuItemData>,
    private val onItemClick: ((Int) -> Unit)? = null,
    private val onItemSwap: ((Int) -> Unit)? = null
) : RecyclerView.Adapter<TaskBaseHolder>(), RecyclerTouchHelperListener {

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(listTaskData, fromPosition, toPosition)
        Log.d("tee", "onItemMove : ")
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun onClearView(fromPosition: Int, toPosition: Int) {
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): TaskBaseHolder {
        return CardTaskHolder(CardTaskView(viewGroup.context))
    }

    override fun getItemCount(): Int {
//        return 15
        return listTaskData.size
    }

    override fun onBindViewHolder(holder: TaskBaseHolder, position: Int) {
        val data = listTaskData[position]
        holder.bindData(
            data,
            onItemClick,
            position
        )

    }

    fun updateData(listTaskData: ArrayList<MenuItemData>) {
        this.listTaskData.clear()
        this.listTaskData.addAll(listTaskData)
        notifyDataSetChanged()
    }

}