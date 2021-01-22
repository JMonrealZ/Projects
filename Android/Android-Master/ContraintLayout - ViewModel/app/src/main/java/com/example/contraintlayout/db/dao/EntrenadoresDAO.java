package com.example.contraintlayout.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.contraintlayout.db.entity2.EntrenadorEntity;

import java.util.List;

@Dao
public interface EntrenadoresDAO {
    @Insert
    void insert(EntrenadorEntity entrenador);

    @Update
    void update(EntrenadorEntity entrenador);

    @Query("DELETE FROM catentrenadores")
    void deleteAll();

//    @Query("DELETE FROM catentrenadores where id = :idEntrenador ORDER BY Nombre ASC")
//    void deleteById(int idEntrenador);

    @Query("SELECT * FROM catentrenadores ORDER BY Nombre ASC")
    LiveData<List<EntrenadorEntity>> getAll();

    @Query("SELECT * FROM catentrenadores where Experiencia > :Experiencia ORDER BY Nombre ASC")
    LiveData<List<EntrenadorEntity>> getAllByExp(double Experiencia);
}
