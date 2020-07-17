package com.example.test.dragAndDropMenuTwoRCV

import android.animation.ObjectAnimator
import android.content.ClipData
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.View.DragShadowBuilder
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.loadImageCircle


class ListAdapterV1(
    var data: ArrayList<Int>,
    var isDisplayPanel: Boolean = false,
    var context: Context
) : RecyclerView.Adapter<ListAdapterV1.ListViewHolder>(){
    var dataList = data

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconImgV = itemView.findViewById<ImageView>(R.id.iconImgV)
        val itemMainLayout = itemView.findViewById<FrameLayout>(R.id.itemMainLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(
            parent.context
        ).inflate(R.layout.item_card_task_view, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.iconImgV.loadImageCircle(dataList[position])
        holder.itemMainLayout.tag = position
        holder.itemMainLayout.setOnDragListener(DragListenerV3())
        holder.itemMainLayout.setOnLongClickListener {
            val data = ClipData.newPlainText("", "")
            val shadowBuilder = DragShadowBuilder(it)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                it.startDragAndDrop(data, shadowBuilder, it, 0)
            } else {
                it.startDrag(data, shadowBuilder, it, 0)
            }
        }
//        animateView(holder.iconImgV)

    }
    fun animateView(view: View) {
        val shake: Animation = AnimationUtils.loadAnimation(context, R.anim.shake)
        view.startAnimation(shake)
    }

    override fun getItemCount(): Int {
        return if (isDisplayPanel && dataList.size > 7)
            7
        else
            dataList.size
    }

    fun getList(): ArrayList<Int> {
        return dataList
    }

    fun updateList(list: ArrayList<Int>) {
        this.dataList = list
    }

    fun getDragInstance(): DragListenerV3 {
        return DragListenerV3()
    }
}