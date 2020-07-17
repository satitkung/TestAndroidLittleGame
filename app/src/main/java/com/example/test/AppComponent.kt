package com.example.test


import com.example.test.Elevator.ElevatorViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
        modules = arrayOf(

                LocalDataModule::class,

                ElevatorRepositoryModule::class
        )
)
interface AppComponent {


    fun inject(postListViewModel: ElevatorViewModel)

}

