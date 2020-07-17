package com.example.test.dragAndDropMenuTwoRCV

import android.view.DragEvent
import android.view.View
import android.view.View.OnDragListener
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R

class DragListener : OnDragListener {
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

                            if (sourceId == rvBottom && targetId == rvTop && listTarget.size == 8) {
                                val lastIndexDisplayItem = adapterTarget.getList()[listTarget.lastIndex]
                                listSource.removeAt(positionSource)
                                listSource.add(positionSource, lastIndexDisplayItem)
                                adapterSource.updateList(listSource)
                                adapterSource.notifyDataSetChanged()

                                if (positionTarget >= 0) {
                                    listTarget.add(positionTarget, sourceItem)
                                } else {
                                    listTarget.add(sourceItem)
                                }
                                listTarget.removeAt(listTarget.lastIndex)
                                adapterTarget.updateList(listTarget)
                                adapterTarget.notifyDataSetChanged()
                            } else {
                                listSource.removeAt(positionSource)
                                adapterSource.updateList(listSource)
                                adapterSource.notifyDataSetChanged()
                                if (positionTarget >= 0) {
                                    listTarget.add(positionTarget, sourceItem)
                                } else {
                                    listTarget.add(sourceItem)
                                }
                                adapterTarget.updateList(listTarget)
                                adapterTarget.notifyDataSetChanged()
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


}