package com.example.test.dragAndDropMenu.viewHolder

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.example.test.R
import com.example.test.dragAndDropMenu.model.MenuItemData
import com.example.test.loadImage
import com.example.test.loadImageCircle
import kotlinx.android.synthetic.main.item_card_task_view.view.*


class CardTaskView
    @JvmOverloads
    constructor(context: Context,
                attrs: AttributeSet? = null,
                defStyleAttr: Int = 0)
    : RelativeLayout(context, attrs, defStyleAttr) {


    init {
        inflate(context, R.layout.item_card_task_view, this)
    }

    fun bindData(
        data: MenuItemData,
        onItemClick: ((Int) -> Unit)?,
        position: Int
    ) {
//        if (position in 10..14) {
//            val menuLayoutParams = itemMainLayout.layoutParams as MarginLayoutParams
//            menuLayoutParams.topMargin = 40
//            itemMainLayout.layoutParams = menuLayoutParams
//        } 
//        titleTV.text = data.title
        iconImgV.loadImageCircle(data.icon)

//        onItemClick?.let { click ->
//            main_layout.setOnClickListener {
//                click(position)
//            }
//        } ?: run {
//            main_layout.setOnClickListener(null)
//        }
    }
}