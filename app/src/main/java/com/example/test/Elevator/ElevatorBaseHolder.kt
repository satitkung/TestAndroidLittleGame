package com.example.test.Elevator

import androidx.recyclerview.widget.RecyclerView
import android.view.View

abstract class ElevatorBaseHolder(view: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

    abstract fun bindData(
        floor: Int,
        isCurrentFloor: Boolean,
        isSelectFloor: Boolean,
        onItemSelected: ((Int) -> Unit)?
    )

}
