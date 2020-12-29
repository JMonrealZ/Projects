package com.anushka.viewmodeldemo1

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel(startingValue:Int) : ViewModel() {
    //private var count = 0
    private var count = MutableLiveData<Int>()
    val countData : LiveData<Int> get() = count

    init {
        count.value = startingValue
    }

//    fun getCurrentCount():Int{
//        return count.value
//    }

//    fun getUpdatedCount():Int{
//        return ++count
//    }

    fun plusOne(){

        count.value = (count.value)?.plus(1)
    }
}