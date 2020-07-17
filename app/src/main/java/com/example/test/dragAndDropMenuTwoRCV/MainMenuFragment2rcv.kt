package com.example.test.dragAndDropMenuTwoRCV

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test.R
import com.example.test.dragAndDropMenu.model.MenuItemData
import kotlinx.android.synthetic.main.fragment_main_menu_2_rcv.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class MainMenuFragment2rcv : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_menu_2_rcv, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initTopRecyclerView()
        initBottomRecyclerView()

    }

    private fun initTopRecyclerView() {
//        rvTop.setLayoutManager(new LinearLayoutManager(
        activity?.let {
            rvTop.layoutManager = GridLayoutManager(
                it, 4
            )
            val topListAdapter = ListAdapterV1(listTask1, true, it)
            rvTop.setAdapter(topListAdapter)
            rvTop.setOnDragListener(topListAdapter.getDragInstance())
        }


    }

    private fun initBottomRecyclerView() {
//        rvBottom.setLayoutManager(new LinearLayoutManager(
//                this, LinearLayoutManager.HORIZONTAL, false));
        activity?.let {
            rvBottom.layoutManager = GridLayoutManager(
                it, 4
            )
            val bottomListAdapter = ListAdapterV1(listTask2, context = it)
            rvBottom.setAdapter(bottomListAdapter)
            rvBottom.setOnDragListener(bottomListAdapter.getDragInstance())
        }


    }

    companion object {
        fun newInstance() = MainMenuFragment2rcv().apply {
            arguments = Bundle().apply {
                putString(ARG_PARAM1, param1)
                putString(ARG_PARAM2, param2)
            }
        }
    }

    var listTask1 = arrayListOf<Int>(
        R.drawable.ais_12call,
        R.drawable.b_coin_ico,
        R.drawable.back_arrow,
        R.drawable.elevator,
        R.drawable.ic_add,
        R.drawable.ic_filter,
        R.drawable.ic_gallery
    )
    var listTask2 = arrayListOf<Int>(
        R.drawable.ic_graph,
        R.drawable.ic_hot_key,
        R.drawable.ic_hotkey_payment,
        R.drawable.ic_human,
        R.drawable.ic_landing_empty_list,
        R.drawable.ic_insurance_heart,
        R.drawable.ic_no_search_result_found,
        R.drawable.ic_selected,
        R.drawable.pending_ico
    )
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
