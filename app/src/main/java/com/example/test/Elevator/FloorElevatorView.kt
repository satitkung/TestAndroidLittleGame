package com.example.test.Elevator

import android.content.Context
import androidx.core.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.example.test.R
import kotlinx.android.synthetic.main.activity_elevator.view.*
import kotlinx.android.synthetic.main.item_floor_elevator.view.*


class FloorElevatorView
    @JvmOverloads
    constructor(context: Context,
                attrs: AttributeSet? = null,
                defStyleAttr: Int = 0)
    : RelativeLayout(context, attrs, defStyleAttr) {

    private val mContext = context
    var listener: goDetailPage? = null

    init {
        inflate(context, R.layout.item_floor_elevator, this)
        listener = context as? goDetailPage
    }

    fun bindData(position: Int,
                 isCurrentFloor: Boolean,
                 isSelectFloor: Boolean,
                 onItemSelected: ((Int) -> Unit)?
    ) {
        tv_floor.text = (position + 1).toString()
        imv_elevator.visibility = if (isCurrentFloor) View.VISIBLE else View.INVISIBLE
        if (isSelectFloor) {
            view_floor.setBackgroundColor(ContextCompat.getColor(mContext, R.color.bgActive))
        } else {
            view_floor.setBackgroundColor(ContextCompat.getColor(mContext, R.color.bgHuman))
        }

        imv_detail.setOnClickListener {
            listener?.onDetailClicked(position)
        }
        onItemSelected?.let { onSelected ->
            main_layout.setOnClickListener {
                onSelected(position)
            }
        } ?: run {
            main_layout.setOnClickListener(null)
        }
    }

    interface goDetailPage {
        fun onDetailClicked(position: Int)
    }
}