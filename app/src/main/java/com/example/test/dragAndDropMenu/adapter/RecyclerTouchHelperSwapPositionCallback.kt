package com.example.todayido.baseAndUtils


import android.content.ClipData.Item
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


class RecyclerTouchHelperSwapPositionCallback(private var listener: RecyclerTouchHelperListener) : ItemTouchHelper.Callback() {
    val oldPos = IntArray(1)
    val newPos = IntArray(1)
    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        return makeFlag(
            ItemTouchHelper.ACTION_STATE_DRAG,
            ItemTouchHelper.DOWN or ItemTouchHelper.UP or ItemTouchHelper.START or ItemTouchHelper.END
        )
    }


    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        oldPos[0] = viewHolder.adapterPosition
        newPos[0] = target.adapterPosition
        Log.d("tee", "onMove : step 4")
        return listener.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//        listener.onItemDismiss(viewHolder.adapterPosition)
    }
    override fun clearView(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ) {
        super.clearView(recyclerView, viewHolder)
        listener.onClearView(oldPos[0], newPos[0])
    }


}
