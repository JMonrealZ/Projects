package com.example.a17_hiltapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application(){
//    lateinit var dataComponent: DataComponent
//    override fun onCreate() {
//        dataComponent = DaggerDataComponent.builder()
//            .build()
//        super.onCreate()
//    }
}