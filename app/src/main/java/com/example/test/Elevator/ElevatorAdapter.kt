package com.example.test.Elevator

import android.os.Handler
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import java.lang.Runnable

class ElevatorAdapter(
    private val maxFloor: Int,
    private val data: ElevatorData,
    private val onItemSelected: ((Int) -> Unit)? = null
) : androidx.recyclerview.widget.RecyclerView.Adapter<ElevatorBaseHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): ElevatorBaseHolder {
        return FloorElevatorHolder(FloorElevatorView(viewGroup.context))
    }

    override fun getItemCount(): Int {
        return maxFloor
    }

    override fun onBindViewHolder(holder: ElevatorBaseHolder, position: Int) {
        holder.bindData(position,
            position == data.currentFloor,
            position == data.selectedFloor,
            onItemSelected
        )
    }

}