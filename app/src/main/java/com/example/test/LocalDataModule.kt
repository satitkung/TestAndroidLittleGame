package com.example.test

import androidx.room.Room
import android.content.Context
import com.example.test.Elevator.AppDatabase
import com.example.test.Elevator.ElevatorDao

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule(val context: Context) {

    private val DB_FILE_NAME = "elevator_table"

    @Provides @Singleton
    fun providePostDao(db: AppDatabase): ElevatorDao = db.elevatorDao()

    @Provides @Singleton
    fun provideAppDatabase(): AppDatabase =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_FILE_NAME)
                .allowMainThreadQueries()
                .build()
}
