package com.example.todayido.home.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.test.dragAndDropMenu.model.MenuItemData


abstract class TaskBaseHolder(view: View): RecyclerView.ViewHolder(view) {

    abstract fun bindData(
        data: MenuItemData,
        onItemClick: ((Int) -> Unit)?,
        position: Int
    )

}
