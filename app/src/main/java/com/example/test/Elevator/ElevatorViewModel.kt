package com.example.test.Elevator

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Log
import javax.inject.Inject

class ElevatorViewModel: ViewModel() {

    @Inject
    lateinit var elevatorRepository: ElevatorRepository
    private var floorNumber = MutableLiveData<String>().apply { value = "1" }
    private var timeStamp: LiveData<List<TimeStampData>>? = null

    fun setFloorNumber(floor: String){
        floorNumber.value = floor
    }

    fun getFloorNumber(): MutableLiveData<String> {
        return floorNumber
    }

    fun getTimeStampFloor(floor: String): LiveData<List<TimeStampData>> {
        timeStamp =  elevatorRepository.getPostById(floor)
        return timeStamp!!
    }

    fun updateTimeStamp(timeStampData: TimeStampData) {
        elevatorRepository.insert(timeStampData)
    }
}