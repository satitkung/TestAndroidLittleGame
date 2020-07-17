package com.example.test

import android.app.Application

class AppApplication : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        buildDependencyGraph()
    }

    private fun buildDependencyGraph() {
        component = DaggerAppComponent.builder()
                .localDataModule(LocalDataModule(applicationContext))
            .elevatorRepositoryModule(ElevatorRepositoryModule())
                .build()
    }
}

