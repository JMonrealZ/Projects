package com.example.roomdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Subscriber::class],version = 1) //Lista de entities classes
abstract class SubscriberDatabase : RoomDatabase(){
    abstract val subscriberDAO : SubscriberDAO

    //no debemos tener más de una versión instalada en el mismo celular
    //por ello generamos un singleton(con ayuda de companion):
    companion object{
        @Volatile   //esto hace visible a otros threads
        private var INSTANCE : SubscriberDatabase?=null
        fun getInstance(context:Context):SubscriberDatabase{
            synchronized(this){
                var instance = INSTANCE
                    if(instance == null){
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            SubscriberDatabase::class.java,
                            "subscriber_data_database"
                        ).build()
                    }
                return instance
            }
        }
    }
}