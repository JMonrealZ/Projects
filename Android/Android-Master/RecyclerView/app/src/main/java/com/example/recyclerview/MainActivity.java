package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity /*implements RestaurateFragment.OnFragmentInteractionListener*/{
    //implements OnListFragmentInteractionListener
//TODO: NO SE PUEDE IMPLEMENTAR
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //TODO: No fue necesario implementar la interfaz dentro del fragment
    /*@Override
    public void onFragmentInteraction(Restaurante restaurante) {
    }*/
}