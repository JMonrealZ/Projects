package com.example.contraintlayout.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.contraintlayout.EntrenadorRepository;
import com.example.contraintlayout.db.entity2.EntrenadorEntity;

import java.util.List;

public class NuevoEntrenadorDialogViewModel extends AndroidViewModel {
    private LiveData<List<EntrenadorEntity>> allEntrenadores;     //"Para retorno" para que se persiba los cambios
    private EntrenadorRepository entrenadorRepository;  //Es la capa transparente que nos facilita los datos

    public NuevoEntrenadorDialogViewModel(Application application){ //
        super(application);

        entrenadorRepository = new EntrenadorRepository(application);
        allEntrenadores = entrenadorRepository.getAll();
    }

    //El fragmento que necesitará recibir la nueva lista de datos(consulta)
    public LiveData<List<EntrenadorEntity>> getAllEntrenadores(){ return allEntrenadores;}

    //El fragmento que inserte una nueva nota, deberá comunicarlo a ste ViewModel(insercción)
    public void insertEntrenador(EntrenadorEntity nuevoEntrenadorEntity){ entrenadorRepository.insert(nuevoEntrenadorEntity);}

    public void updateEntrendor(EntrenadorEntity modifiedEntrenadorEntity){ entrenadorRepository.update(modifiedEntrenadorEntity);}

}