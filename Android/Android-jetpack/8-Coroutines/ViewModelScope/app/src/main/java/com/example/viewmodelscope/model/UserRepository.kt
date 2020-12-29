package com.example.viewmodelscope.model

import kotlinx.coroutines.delay

class UserRepository {
    //Esta clase obtiene los datos de una base local o api rest
    suspend fun getUsers() : List<User>{
        delay(8000)
        val users : List<User> = listOf(
            User(1,"Juan"),
            User(2,"Pedro"),
            User(3,"Luis"),
            User(4,"Sancho")
        )
        return users
    }
}