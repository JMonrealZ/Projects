package com.example.contraintlayout;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.contraintlayout.db.EntrenadorRoomDB;
import com.example.contraintlayout.db.dao.EntrenadoresDAO;
import com.example.contraintlayout.db.entity2.EntrenadorEntity;

import java.util.List;

public class EntrenadorRepository {
    private EntrenadoresDAO entrenadoresDAO;    //Para operaciones de la base de datos
    private LiveData<List<EntrenadorEntity>> allEntrenadores;


    public EntrenadorRepository(Application application){  //instanciará a partir de la aplicación una instancia de la base de datos(constructor)
        EntrenadorRoomDB db = EntrenadorRoomDB.getDataBase(application);
        entrenadoresDAO = db.entrenadoresDAO();
        allEntrenadores = entrenadoresDAO.getAll();
    }

    //Aquí se declarán todas las operaciones que se le permitirán al repositorio:
    public LiveData<List<EntrenadorEntity>> getAll(){ return allEntrenadores; }

    public void insert(EntrenadorEntity entrenador){
        new insertAsyncTask(entrenadoresDAO).execute(entrenador);
    }

    public void update(EntrenadorEntity entrenador){
        new updateAsyncTask(entrenadoresDAO).execute(entrenador);
    }

    //Es una clase para hacer tareas en el background
    private static class insertAsyncTask extends AsyncTask<EntrenadorEntity, Void, Void> {
        private EntrenadoresDAO entrenadoresDAOAsyncTask;

        insertAsyncTask(EntrenadoresDAO dao){
            entrenadoresDAOAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(EntrenadorEntity... entrenadorEntities) {         //EntrenadorEntity... entrenadorEntities = array de parametros
            entrenadoresDAOAsyncTask.insert(entrenadorEntities[0]);
            return null;
        }
    }

    private static class updateAsyncTask extends AsyncTask<EntrenadorEntity, Void, Void> {
        private EntrenadoresDAO entrenadoresDAOAsyncTask;

        updateAsyncTask(EntrenadoresDAO dao){
            entrenadoresDAOAsyncTask = dao;
        }

        @Override
        protected Void doInBackground(EntrenadorEntity... entrenadorEntities) {         //EntrenadorEntity... entrenadorEntities = array de parametros
            entrenadoresDAOAsyncTask.update(entrenadorEntities[0]);
            return null;
        }
    }
}
