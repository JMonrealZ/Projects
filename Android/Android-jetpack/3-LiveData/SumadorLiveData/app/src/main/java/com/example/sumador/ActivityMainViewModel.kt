package com.example.sumador

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ActivityMainViewModel(startingValue : Int) : ViewModel(){
    //private var sumatoria = 0
    private var sumatoria = MutableLiveData<Int>()  //Hacemos un int como LiveData
    val sumatoriaData : LiveData<Int>
    get() = sumatoria       //Con estas 2 lineas lo hacemos accesibles

    init {
        sumatoria.value = startingValue
    }

    fun sumar(number: Int){
        //sumatoria += number
        sumatoria.value = (sumatoria.value)?.plus(number)   // "?" = null safety check
    }

//    fun getSumatoria():Int{           //No requerimos esto debido a que crearemos un observador en el UI
//        return sumatoria.value
//    }
}