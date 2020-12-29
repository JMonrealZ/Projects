package com.anushka.coroutinesdemo1

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class UserDataManager {
    suspend fun getTotalUserCount():Int{
        var count = 0
        //CoroutineScope genera una nueva coroutine el cual se comporta separadamente de su padre
        //en el main activity
        CoroutineScope(IO).launch {
            delay(1000)
            count = 50  //no garantiza que se complete todas las tareas
        }

        val deferred = CoroutineScope(IO).async {
            delay(3000)
            return@async 70
        }

//        Las funciones anteriores no es posible hacerse cargo de las excepciones.
        return count + deferred.await()
    }
}