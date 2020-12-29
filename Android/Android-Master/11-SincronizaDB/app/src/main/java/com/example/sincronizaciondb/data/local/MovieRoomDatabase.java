package com.example.sincronizaciondb.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.sincronizaciondb.data.local.dao.MovieDao;
import com.example.sincronizaciondb.data.local.entity.MovieEntity;

@Database(entities = {MovieEntity.class}, version = 1, exportSchema = false)
public abstract class MovieRoomDatabase extends RoomDatabase {

    public abstract MovieDao getMovieDao();

}
