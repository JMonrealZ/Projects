package com.example.sincronizaciondb.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.sincronizaciondb.data.local.entity.MovieEntity;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movies")
    LiveData<List<MovieEntity>> loadMovies();

    @Insert(onConflict = OnConflictStrategy.REPLACE)    //si reciibimos una que ya exista que la reemplce
    void saveMovies(List<MovieEntity> movieEntityList);
}
