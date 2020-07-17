package com.example.test.Elevator


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test.AppApplication

import com.example.test.R
import kotlinx.android.synthetic.main.fragment_home_elevator.*
import java.util.*

class HomeElevatorFragment : androidx.fragment.app.Fragment() {

    var maxFloor =  0
    private lateinit var adapter: ElevatorAdapter
    private val handler = Handler()
    var runnable: Runnable? = null
    var oldSelectFloor = 0
    private val data = ElevatorData(-1, 0)
    private lateinit var elevatorViewModel: ElevatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_elevator, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { elevatorViewModel = ViewModelProviders.of(it).get(ElevatorViewModel::class.java).also {
            AppApplication.component.inject(it)
        }}

        arguments?.let {
            maxFloor = it.getInt(MAX_FLOOR, 1)
        }
        initView()
    }

    private fun initView() {
        activity?.let {
            elevatorViewModel.getFloorNumber().observe(it, Observer { floor ->
                tv_lift.text = floor
            })
            rcv_elevator.layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(activity)

            adapter = ElevatorAdapter(maxFloor, data) { selected ->
                if (selected != data.selectedFloor) {
                    runnable?.let { handler.removeCallbacks(it) }
                    oldSelectFloor = data.selectedFloor
                    data.selectedFloor = selected
                    if (oldSelectFloor >= 0)
                        adapter.notifyItemChanged(oldSelectFloor)
                    adapter.notifyItemChanged(selected)
                    updateList()
                }
            }

            rcv_elevator.adapter = adapter

            btn_top.setOnClickListener {
                rcv_elevator.scrollToPosition(0)
            }

            btn_bottom.setOnClickListener {
                rcv_elevator.scrollToPosition(maxFloor - 1)
            }

        }
    }

    private fun updateList() {
        var startFloor = data.currentFloor
        val desFloor = data.selectedFloor
        if (startFloor != desFloor) {
            if (startFloor < desFloor) {
                startFloor += 1
                moveElevator(startFloor - 1, startFloor)
            } else {
                startFloor -= 1
                moveElevator(startFloor + 1, startFloor)
            }
        } else {

            val data = TimeStampData((desFloor+1).toString(), Calendar.getInstance().time.toString())
            elevatorViewModel.updateTimeStamp(data)

        }
    }

    private fun moveElevator(oldFloor: Int, nextFloor: Int) {
        data.currentFloor = nextFloor
        elevatorViewModel.setFloorNumber((nextFloor+1).toString())
        adapter.notifyItemChanged(oldFloor)
        adapter.notifyItemChanged(nextFloor)

        runnable = Runnable {
            updateList()
        }
        handler.postDelayed(runnable, 1000)
    }

    companion object {
        const val MAX_FLOOR = "max_floor"
        fun newInstance(maxFloor: Int) = HomeElevatorFragment().apply {
            arguments = Bundle().apply {
                putInt(MAX_FLOOR, maxFloor)
            }
        }
    }
}
