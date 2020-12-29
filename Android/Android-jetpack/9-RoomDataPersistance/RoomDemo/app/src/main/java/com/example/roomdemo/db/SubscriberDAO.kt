package com.example.roomdemo.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SubscriberDAO {

    @Insert//(onConflict = OnConflictStrategy.REPLACE) //replace(delete old, insert new)
    suspend fun insertSubscriber(subscriber: Subscriber):Long //retorna Long para el rowId

    @Insert
    suspend fun insertSubscrubers(subsribers: List<Subscriber>):List<Long>

    @Update
    suspend fun updateSubscriber(subscriber: Subscriber) : Int

    //int retornara num de columnas afectadas(cuando la función lo retorna)

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber) : Int

    @Query("DELETE FROM subscriber_data_table")
    suspend fun deleteAll():Int

    @Query("SELECT * FROM subscriber_data_table")   //no es necesario agregar "suspend"
    fun getAllSubscribers():LiveData<List<Subscriber>>
}