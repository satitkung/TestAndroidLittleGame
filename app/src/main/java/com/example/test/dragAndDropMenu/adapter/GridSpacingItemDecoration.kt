package com.example.test.dragAndDropMenu.adapter

import android.graphics.Rect
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.dragAndDropMenu.toPx
import com.example.test.dragAndDropMenu.viewHolder.CardTaskView
import kotlinx.android.synthetic.main.item_card_task_view.view.*

class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdge: Boolean,
    private val headerNum: Int
) : RecyclerView.ItemDecoration() {
    var isFirst = true
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) - headerNum // item position
//        Log.d("tee", "getItemOffsets : $position")
//        updateView(position, parent)
        if (position >= 0) {
            val column = position % spanCount // item column

//            if (includeEdge) {
//                outRect.left = spacing - column * spacing / spanCount // spacing - column * ((1f / spanCount) * spacing)
//                outRect.right = (column + 1) * spacing / spanCount // (column + 1) * ((1f / spanCount) * spacing)
//
//                if (position < spanCount) { // top edge
//                    outRect.top = spacing
//                }
//                outRect.bottom = spacing // item bottom
//            } else {
//                outRect.left = column * spacing / spanCount // column * ((1f / spanCount) * spacing)
//                outRect.right = spacing - (column + 1) * spacing / spanCount // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                val viewTest = parent.findViewHolderForAdapterPosition(position)?.itemView as? CardTaskView
//                val title = viewTest?.titleTV?.text?.toString()
//                Log.d("tee", "getItemOffsets :    position title: ${title} position: ${position}")
                if (isFirst) {
                    if (position in 10..14) {
//                    Log.d("tee", "getItemOffsets : ")
                        outRect.top = spacing // item top

                    } else {
                        outRect.top = 0
                    }
                    if (position == 16) {
                        isFirst = false
                    }
                } else {
                    updateView(position, parent)
                }
               
            
//            if (position in 10..14) {
//                                    outRect.top = 80 // item top
//                val menuLayoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
//                menuLayoutParams.topMargin = spacing
//                view.layoutParams = menuLayoutParams
//            } else {
//                val menuLayoutParams = view.layoutParams as ViewGroup.MarginLayoutParams
//                menuLayoutParams.topMargin = 0
//                view.layoutParams = menuLayoutParams
//            }
//        } 
        }
    }
    fun updateView(position: Int, parent: RecyclerView) {
        val view = parent.findViewHolderForAdapterPosition(position)?.itemView as? CardTaskView
        if (view != null) {
            Log.d("tee", "updateView : ${position}")
            if (position in 10..14) {
                val menuLayoutParams = view.itemMainLayout.layoutParams as ViewGroup.MarginLayoutParams
                menuLayoutParams.setMargins(0, 40.toPx(), 0, 0) 
                view.itemMainLayout.layoutParams = menuLayoutParams
            } else {
                val menuLayoutParams = view.itemMainLayout.layoutParams as ViewGroup.MarginLayoutParams
                menuLayoutParams.setMargins(0, 0, 0, 0)
                view.itemMainLayout.layoutParams = menuLayoutParams
            }
        }
    }
}
