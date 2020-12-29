package com.anushka.didemo

import android.util.Log
import javax.inject.Inject

class MemoryCard /*@Inject constructor()*/{
    //Ejemplo2 se comentó @Inject constructor para su realización
    init {
        Log.i("MYTAG","Memory Card Constructed")
    }

    fun getSpaceAvailablity(){
        Log.i("MYTAG","Memory space available")
    }
}