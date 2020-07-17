package com.example.test.Elevator

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TimeStampData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun elevatorDao(): ElevatorDao
}

