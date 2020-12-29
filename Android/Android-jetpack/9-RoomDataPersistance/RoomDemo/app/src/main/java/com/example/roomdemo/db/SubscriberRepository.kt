package com.example.roomdemo.db

class SubscriberRepository(private val dao:SubscriberDAO) {

    //no es necesario llamarlo desde una coroutine
    // debido a que estas funciones procesa en el background por livedata
    val subscribers = dao.getAllSubscribers()

    suspend fun insert(subscriber: Subscriber):Long{
        return dao.insertSubscriber(subscriber)
    }

    suspend fun update(subscriber:Subscriber):Int{
        return dao.updateSubscriber(subscriber)
    }

    suspend fun delete(subscriber: Subscriber):Int{
        return dao.deleteSubscriber(subscriber)
    }

    suspend fun deleteAll():Int{
        return dao.deleteAll()
    }
}