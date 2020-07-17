package com.example.test.Elevator

import androidx.lifecycle.LiveData

interface ElevatorInterface {

    fun insert(time: TimeStampData)

    fun getPostById(floor: String): LiveData<List<TimeStampData>>

    fun getHistoryById(floor: String): List<TimeStampData>
}
