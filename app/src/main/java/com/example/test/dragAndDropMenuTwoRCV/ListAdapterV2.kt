package com.example.test.dragAndDropMenuTwoRCV

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.loadImageCircle


class ListAdapterV2(
    val context: Context,
    val onItemCLick: ((Int) -> Unit)? = null
) : ListAdapter<Int, ListViewHolder>(
    PostDiffCallbackKub()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(
            parent.context
        ).inflate(R.layout.item_card_task_view, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var item: Int? = null
        if (position != 8) {
            item = getItem(position)
        }
        item?.let {
            holder.iconImgV.loadImageCircle(item)
        } ?: kotlin.run {
            holder.iconImgV.loadImageCircle(R.drawable.more_landing_ico)
        }
        holder.iconImgV.setOnClickListener {
            onItemCLick?.invoke(position)
        }
    }


    override fun getItemCount(): Int {
        return 8
    }

    fun getList(): MutableList<Int> {
        return currentList
    }

}

class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val iconImgV = itemView.findViewById<ImageView>(R.id.iconImgV)
}


class PostDiffCallbackKub : DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }
}