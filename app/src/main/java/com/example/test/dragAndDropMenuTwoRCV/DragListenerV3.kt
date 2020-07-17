package com.example.test.dragAndDropMenuTwoRCV

import android.util.Log
import android.view.DragEvent
import android.view.View
import android.view.View.OnDragListener
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import java.util.logging.Handler

class DragListenerV3 : OnDragListener {
    private var isDropped = false
    override fun onDrag(v: View, event: DragEvent): Boolean {
        when (event.action) {
            DragEvent.ACTION_DROP -> {
                isDropped = true
                var positionTarget = -1
                val viewSource = event.localState as? View?
                val viewId = v.id
                val flItem: Int = R.id.itemMainLayout
                val rvTop = R.id.rvTop
                val rvBottom = R.id.rvBottom
                when (viewId) {
                    flItem, rvTop, rvBottom -> {
                        val target: RecyclerView
                        when (viewId) {
                            rvTop -> target = v.rootView
                                .findViewById<View>(rvTop) as RecyclerView
                            rvBottom -> target = v.rootView
                                .findViewById<View>(rvBottom) as RecyclerView
                            else -> {
                                target = v.parent as RecyclerView
                                positionTarget = v.tag as Int
                            }
                        }
                        if (viewSource != null) {
                            //section source
                            val source = viewSource.parent as RecyclerView
                            val adapterSource = source.adapter as ListAdapterV1
                            val positionSource = viewSource.tag as Int
                            val sourceId = source.id
                            val listSource = adapterSource.getList()
                            val sourceItem = adapterSource.getList()[positionSource]

                            //section target
                            val adapterTarget = target.adapter as ListAdapterV1
                            val targetId = target.id
                            val listTarget = adapterTarget.getList()

                            if (sourceId == rvBottom && targetId == rvTop && listTarget.size == 7) {
                                if (positionTarget >= 0) {
                                    val lastIndexDisplayItem =
                                        adapterTarget.getList()[listTarget.lastIndex]
                                    listSource.removeAt(positionSource)
                                    listSource.add(positionSource, lastIndexDisplayItem)
                                    adapterSource.updateList(listSource)
                                    adapterSource.notifyItemChanged(positionSource)
                                    updateTag(listSource, source)
                                    listTarget.removeAt(listTarget.lastIndex)
                                    adapterTarget.updateList(listTarget)
                                    adapterTarget.notifyItemRemoved(listTarget.lastIndex + 1)
                                    listTarget.add(positionTarget, sourceItem)
                                    adapterTarget.updateList(listTarget)
                                    adapterTarget.notifyItemInserted(positionTarget)
                                    updateTag(listTarget, target)
                                }
                            } else {
                                listSource.removeAt(positionSource)
                                adapterSource.updateList(listSource)
                                adapterSource.notifyItemRemoved(positionSource)
                                updateTag(listSource, source)
                                if (positionTarget >= 0) {
                                    listTarget.add(positionTarget, sourceItem)
                                    adapterTarget.updateList(listTarget)
                                    adapterTarget.notifyItemInserted(positionTarget)
                                    updateTag(listTarget, target)
                                } else {
                                    listTarget.add(sourceItem)
                                    adapterTarget.updateList(listTarget)
                                    adapterTarget.notifyItemInserted(listTarget.lastIndex)
                                    updateTag(listTarget, target)
                                }

                            }
                        }
                    }
                }
            }
        }
        if (!isDropped && event.localState != null) {
            (event.localState as View).visibility = View.VISIBLE
        }
        return true
    }

    fun updateTag(
        data: ArrayList<Int>,
        adapterSource: RecyclerView
    ) {
        data.forEachIndexed { index, item ->
            (adapterSource.findViewHolderForAdapterPosition(index) as? ListAdapterV1.ListViewHolder)?.itemMainLayout?.tag = index
        }
    }

}