package com.example.test.Elevator

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context


@Database(entities = [TimeStampData::class], version = 1)
abstract class ElevatorDatabase : RoomDatabase() {

    abstract fun elevatorDao(): ElevatorDao


    companion object {
        private var instance: ElevatorDatabase? = null

        fun getInstance(context: Context): ElevatorDatabase? {
            if (instance == null) {
                synchronized(ElevatorDatabase::class) {
                    instance = Room.databaseBuilder(
                        context,
                        ElevatorDatabase::class.java,
                        "elevator_table"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance
        }
    }

}