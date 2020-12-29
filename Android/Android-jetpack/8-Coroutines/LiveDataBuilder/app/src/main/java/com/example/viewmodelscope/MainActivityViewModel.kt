package com.example.viewmodelscope

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.viewmodelscope.model.User
import com.example.viewmodelscope.model.UserRepository
import kotlinx.coroutines.*

class MainActivityViewModel : ViewModel() {
    //Segunda parte
    private val userRepository = UserRepository()
//    var users : MutableLiveData<List<User>> = MutableLiveData()
    //Esto sirve para no tener que escribir mucho boilerplate
//    fun getUserData(){
//        viewModelScope.launch {
//            var result : List<User>? = null  //"?" lo hacemos nullable
//            withContext(Dispatchers.IO){
//                result = userRepository.getUsers()
//            }
//            users.value = result
//        }
//    }

    var users = liveData(Dispatchers.IO){
        val result = userRepository.getUsers()
        emit(result)
    }
}