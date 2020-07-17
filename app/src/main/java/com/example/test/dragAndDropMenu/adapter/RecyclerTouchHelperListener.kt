package com.example.todayido.baseAndUtils


interface RecyclerTouchHelperListener {
    fun onItemMove(fromPosition: Int, toPosition: Int): Boolean
    fun onClearView(fromPosition: Int, toPosition: Int)
}