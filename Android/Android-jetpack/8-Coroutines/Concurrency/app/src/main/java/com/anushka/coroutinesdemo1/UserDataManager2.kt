package com.anushka.coroutinesdemo1

import kotlinx.coroutines.*

class UserDataManager2 {
    var count = 0
    lateinit var deferred:Deferred<Int>
    suspend fun getTotalUserCount():Int{
        //al usar coroutine garantiza que se completarán todas las tareas
        coroutineScope {
            launch(Dispatchers.IO) {
                delay(1000) //al no poner el dispatcher utilizará el del padre
                count = 50
            }

            deferred = async(Dispatchers.IO){
                delay(3000)
                return@async 70
            }
        }

        return count + deferred.await()
    }
}