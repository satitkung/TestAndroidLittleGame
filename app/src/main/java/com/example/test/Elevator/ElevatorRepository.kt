package com.example.test.Elevator

import androidx.lifecycle.LiveData
import javax.inject.Inject

class ElevatorRepository @Inject constructor(val localSource: ElevatorDao) : ElevatorInterface {
    override fun insert(time: TimeStampData) {
        localSource.insert(time)
    }

    override fun getPostById(floor: String): LiveData<List<TimeStampData>> {
        return localSource.getDetailFloor(floor)
    }

    override fun getHistoryById(floor: String): List<TimeStampData> {
        return localSource.getDetailHistory(floor)
    }

}