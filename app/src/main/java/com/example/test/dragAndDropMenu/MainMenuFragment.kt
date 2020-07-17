package com.example.test.dragAndDropMenu

import android.content.res.Resources
import android.os.Bundle
import android.provider.MediaStore.Audio.Playlists.Members.moveItem
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.dragAndDropMenu.model.MenuItemData
import com.example.test.dragAndDropMenu.viewHolder.CardTaskView
import com.example.todayido.baseAndUtils.RecyclerTouchHelperCallback
import com.example.todayido.baseAndUtils.RecyclerTouchHelperSwapPositionCallback
import com.example.todayido.home.adapter.TaskAdapter
import com.example.todayido.home.adapter.TaskSwapPositionAdapter
import kotlinx.android.synthetic.main.fragment_main_menu.*
import kotlinx.android.synthetic.main.item_card_task_view.view.*


class MainMenuFragment : Fragment() {

    private lateinit var adapter: TaskAdapter
    private lateinit var adapterSwapPosition: TaskSwapPositionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    private fun initView() {
        //set recyclerview

        activity?.let {
            mainMenuRCV.layoutManager = GridLayoutManager(it, 4)
        }
        adapter = TaskAdapter(
            listTask
        )
        adapterSwapPosition = TaskSwapPositionAdapter(
            listTask
        )
        
        mainMenuRCV.adapter = adapter
//        mainMenuRCV.adapter = adapterSwapPosition
        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.grid_layout_margin)

//        mainMenuRCV.addItemDecoration(GridSpacingItemDecoration(5, spacingInPixels, false, 0))
        val callback = RecyclerTouchHelperCallback(adapter)
//        val callback = RecyclerTouchHelperSwapPositionCallback(adapterSwapPosition)
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(mainMenuRCV)

    }

    fun updateView(position: Int) {
        val view = mainMenuRCV.findViewHolderForAdapterPosition(position)?.itemView as? CardTaskView
        if (view != null) {
            Log.d("tee", "updateView : ${position}")
            if (position in 10..14) {
                val menuLayoutParams = view.itemMainLayout.layoutParams as ViewGroup.MarginLayoutParams
                menuLayoutParams.topMargin = 40
                view.itemMainLayout.layoutParams = menuLayoutParams
            } else {
                val menuLayoutParams = view.itemMainLayout.layoutParams as ViewGroup.MarginLayoutParams
                menuLayoutParams.topMargin = 0
                view.itemMainLayout.layoutParams = menuLayoutParams
            }
        }
    }

    companion object {
        fun newInstance() = MainMenuFragment()
    }

    var listTask = arrayListOf<MenuItemData>(
        MenuItemData(
            R.drawable.ais_12call,
            "1"
        ),
        MenuItemData(
            R.drawable.b_coin_ico,
            "2"
        ),
        MenuItemData(
            R.drawable.back_arrow,
            "3"
        ),
        MenuItemData(
            R.drawable.elevator,
            "4"
        ),
        MenuItemData(
            R.drawable.ic_add,
            "5"
        ),
        MenuItemData(
            R.drawable.ic_filter,
            "6"
        ),
        MenuItemData(
            R.drawable.ic_fixme_my_account,
            "7"
        ),
        MenuItemData(
            R.drawable.ic_gallery,
            "8"
        ),
        MenuItemData(
            R.drawable.ic_graph,
            "9"
        ),
        MenuItemData(
            R.drawable.ic_hot_key,
            "10"
        ),
        MenuItemData(
            R.drawable.ic_hotkey_payment,
            "11"
        ),
        MenuItemData(
            R.drawable.ic_human,
            "12"
        ),
        MenuItemData(
            R.drawable.ic_insurance_heart,
            "13"
        ),
        MenuItemData(
            R.drawable.ic_landing_empty_list,
            "14"
        ),
        MenuItemData(
            R.drawable.ic_no_search_result_found,
            "15"
        ),
        MenuItemData(
            R.drawable.ic_selected,
            "16"
        ),
        MenuItemData(
            R.drawable.pending_ico,
            "17"
        )
    )
}
fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
