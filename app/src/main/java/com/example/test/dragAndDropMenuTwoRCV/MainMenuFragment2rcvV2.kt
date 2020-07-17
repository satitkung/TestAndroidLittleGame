package com.example.test.dragAndDropMenuTwoRCV

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test.R
import kotlinx.android.synthetic.main.fragment_main_menu_select.*
import kotlin.collections.ArrayList


class MainMenuFragment2rcvV2 : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_menu_select, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initTopRecyclerView()

    }

    private fun initTopRecyclerView() {
        activity?.let {
            selectMenu.layoutManager = GridLayoutManager(
                it, 4
            )
            val topListAdapter = ListAdapterV2(it) { position ->
                Toast.makeText(context, "Show ${position}", Toast.LENGTH_SHORT).show()
                if (position == 8) {
                    (it as DragAndDropTwoRCVActivity).addFragment(MainMenuFragment2rcv.newInstance(), true)
                }
            }
            selectMenu.adapter = topListAdapter

            topListAdapter.submitList(filterSelectMenu(listTask1).toList())
        }


    }

    fun filterSelectMenu(listTask1: MutableList<Int>): ArrayList<Int> {
        val data = arrayListOf<Int>()
        listTask1.forEachIndexed{ index, item ->
            if (index < 8) {
                data.add(index)
            }
        }
        data.add(R.drawable.ic_add)
        return data
    }

    companion object {
        fun newInstance() = MainMenuFragment2rcvV2()
    }


    var listTask1 = mutableListOf<Int>(
        R.drawable.ais_12call,
        R.drawable.b_coin_ico,
        R.drawable.back_arrow,
        R.drawable.elevator,
        R.drawable.ic_filter,
        R.drawable.ic_fixme_my_account,
        R.drawable.ic_gallery,
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

}
