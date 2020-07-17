package com.example.test


import com.example.test.Elevator.ElevatorDao
import com.example.test.Elevator.ElevatorInterface
import com.example.test.Elevator.ElevatorRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ElevatorRepositoryModule {

    @Provides @Singleton
    fun provideElevatorRepository(localSource: ElevatorDao): ElevatorInterface
            = ElevatorRepository(localSource)
}

