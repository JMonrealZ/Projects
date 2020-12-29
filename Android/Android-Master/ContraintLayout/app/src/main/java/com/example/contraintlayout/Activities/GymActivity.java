package com.example.contraintlayout.Activities;

import android.os.Bundle;

import com.example.contraintlayout.Interfaces.EntrenadorInteractionListener;
import com.example.contraintlayout.Models.Entrenador;
import com.example.contraintlayout.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class GymActivity extends AppCompatActivity implements EntrenadorInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        //Esta linea es la que se encarga de construir el bottom navigation menu(enlaza botones con fragments)
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_entrenadores, R.id.navigation_horarios, R.id.navigation_tiempo, R.id.navigation_ajustes)
                .build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }


    @Override
    public void editEntrenador(Entrenador entrenador) {

    }

    @Override
    public void eliminarEntrenador(Entrenador entrenador) {

    }

    @Override
    public void bloquearEntrenador(Entrenador entrenador) {
        if(entrenador.getEstatus() == 1) entrenador.setEstatus(0);
        else entrenador.setEstatus(1);
    }
}