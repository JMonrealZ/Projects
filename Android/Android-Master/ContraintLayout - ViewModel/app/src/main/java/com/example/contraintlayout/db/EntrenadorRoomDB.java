package com.example.contraintlayout.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.contraintlayout.db.dao.EntrenadoresDAO;
import com.example.contraintlayout.db.entity.EntrenadorEntity;

@Database(entities = {EntrenadorEntity.class}, version = 1)   //"Entidades que conforman la base de datos
public abstract class  EntrenadorRoomDB extends RoomDatabase {
    public abstract EntrenadoresDAO entrenadoresDAO();              //nos permitira un objeto DAO cuando lo necesitemos
    private static volatile EntrenadorRoomDB INSTANCE;      //guarda la instancia de la base de datos - volatile, lo indica en la documentacion(mayuscula = constante)

    public static EntrenadorRoomDB getDataBase(final Context context){  //Para obtener la BD de diferentes puntos.
        if(INSTANCE == null){
            synchronized (EntrenadorRoomDB.class){  //Generamos una nueva DB
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            EntrenadorRoomDB.class,"Gimnasio")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
