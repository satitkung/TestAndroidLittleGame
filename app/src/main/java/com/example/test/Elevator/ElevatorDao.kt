package com.example.test.Elevator

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface ElevatorDao {

    @Insert
    fun insert(data: TimeStampData)

    @Query("SELECT * FROM elevator_table WHERE floor = :floor")
    fun getDetailFloor(floor: String): LiveData<List<TimeStampData>>

    @Query("SELECT * FROM elevator_table WHERE floor = :floor")
    fun getDetailHistory(floor: String): List<TimeStampData>

}