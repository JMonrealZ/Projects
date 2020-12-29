package com.example.sumador

import androidx.lifecycle.ViewModel

class ActivityMainViewModel(startingValue : Int) : ViewModel(){
    private var sumatoria = 0

    init {
        sumatoria = startingValue
    }

    fun sumar(number: Int){
        sumatoria += number
    }

    fun getSumatoria():Int{
        return sumatoria
    }
}