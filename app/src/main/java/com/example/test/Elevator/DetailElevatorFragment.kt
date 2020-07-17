package com.example.test.Elevator


import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test.AppApplication

import com.example.test.R
import kotlinx.android.synthetic.main.fragment_detail_elevator.*


class DetailElevatorFragment : androidx.fragment.app.Fragment() {

    private lateinit var elevatorViewModel: ElevatorViewModel
    private var floorNumber = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail_elevator, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let { elevatorViewModel = ViewModelProviders.of(it).get(ElevatorViewModel::class.java).also {
            AppApplication.component.inject(it)
        }}

        arguments?.let {
            floorNumber = it.getInt(FLOOR_NUMBER, 1)
        }

        initView()
    }

    private fun initView() {
        tv_floor?.text = floorNumber.toString()

        activity?.let {
            elevatorViewModel.getFloorNumber().observe(it, Observer { floor ->
                tv_lift?.text = floor
            })

            rcv_transaction.layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(activity)
            val adapter = ElevatorDetailAdapter(it)
            rcv_transaction.adapter = adapter

            val ji = floorNumber.toString()
            elevatorViewModel.getTimeStampFloor(ji).observe(it, Observer { list ->
                adapter.submitList(list)
            })
        }

        imv_back.setOnClickListener {
            activity?.onBackPressed()
        }

    }

    companion object {
        const val FLOOR_NUMBER = "floor_number"
        fun newInstance(floorNumber: Int) = DetailElevatorFragment().apply {
            arguments = Bundle().apply {
                putInt(FLOOR_NUMBER, floorNumber)
            }
        }
    }
}
