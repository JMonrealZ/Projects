package com.example.sumador

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivityViewModelFactory(private val startingValue : Int) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ActivityMainViewModel::class.java)){
            return ActivityMainViewModel(startingValue) as T
        }
        throw IllegalArgumentException("Unkown View Model Class")
    }
}