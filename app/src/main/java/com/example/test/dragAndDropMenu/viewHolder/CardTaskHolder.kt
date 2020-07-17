package com.example.todayido.home.viewHolder

import com.example.test.dragAndDropMenu.model.MenuItemData
import com.example.test.dragAndDropMenu.viewHolder.CardTaskView


class CardTaskHolder(private val view: CardTaskView): TaskBaseHolder(view) {

    override fun bindData(
        data: MenuItemData,
        onItemClick: ((Int) -> Unit)?,
        position: Int
    ) {
        view.bindData(data, onItemClick, position)
    }

}