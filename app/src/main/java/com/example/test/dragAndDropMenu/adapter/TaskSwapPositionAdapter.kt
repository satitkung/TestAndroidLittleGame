package com.example.todayido.home.adapter

import android.os.Handler
import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.dragAndDropMenu.model.MenuItemData
import com.example.test.dragAndDropMenu.viewHolder.CardTaskView
import com.example.todayido.baseAndUtils.RecyclerTouchHelperListener
import com.example.todayido.home.viewHolder.CardTaskHolder
import com.example.todayido.home.viewHolder.TaskBaseHolder
import java.util.*


class TaskSwapPositionAdapter(
    private var listTaskData: ArrayList<MenuItemData>,
    private val onItemClick: ((Int) -> Unit)? = null,
    private val onItemSwap: ((Int) -> Unit)? = null
) : RecyclerView.Adapter<TaskBaseHolder>(), RecyclerTouchHelperListener {
    private val handler = Handler()
    var runnable: Runnable? = null
    val oldPos = IntArray(1)
    val newPos = IntArray(1)
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

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Log.d("tee", "onItemMove fromPosition: ${fromPosition}   toPosition: ${toPosition} ")
        runnable?.let { handler.removeCallbacks(it) }
        runnable = Runnable {
            Log.d("tee", "onItemMove : step1")
            Collections.swap(listTaskData, fromPosition, toPosition)
            Log.d("tee", "onItemMove : step2")

            notifyItemChanged(fromPosition)
            Log.d("tee", "onItemMove : step3")

            notifyItemChanged(toPosition)
        }
        handler.postDelayed(runnable, 500)

        return false
    }

    override fun onClearView(fromPosition: Int, toPosition: Int){
//        moveItem(fromPosition, toPosition)
//        Collections.swap(listTaskData, fromPosition, toPosition)
//        notifyItemChanged(fromPosition)
//        notifyItemChanged(toPosition)
//        notifyItemMoved(fromPosition, toPosition)
    }

    private fun moveItem(oldPos: Int, newPos: Int) {
        val temp = listTaskData.get(oldPos)
        listTaskData.set(oldPos, listTaskData.get(newPos))
        listTaskData.set(newPos, temp)
        notifyItemChanged(oldPos)
        notifyItemChanged(newPos)
//        notifyItemMoved(oldPos, newPos)
    }
}