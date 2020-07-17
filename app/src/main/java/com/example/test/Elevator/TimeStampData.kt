package com.example.test.Elevator

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "elevator_table")
data class TimeStampData(
    val floor: String,
    val time: String) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

