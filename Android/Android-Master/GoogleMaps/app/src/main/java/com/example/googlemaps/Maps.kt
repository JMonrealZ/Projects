package com.example.googlemaps

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.googlemaps.databinding.ActivityMapsBinding

class Maps : AppCompatActivity() {

    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_maps)

        binding.apply {
            btnIntentImplicitoGoogle.setOnClickListener(){  OpenImplicitActivityMAPS()  }
            btnMapsActivity.setOnClickListener(){   OpenMapsActivity()  }
        }
    }

    private fun OpenImplicitActivityMAPS(){
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse("geo:25.659308,-100.186211?z=23")
        }
        if(intent.resolveActivity(packageManager) != null)  //con esta linea nos aseguramos que haya una app
            //que pueda abrir la petición anterior(intent)(que pueda abrir una posicion gps)
            startActivity(intent)
    }

    private fun OpenMapsActivity(){
        val intent = Intent(this,MapsActivityApiKey::class.java)
        startActivity(intent)
    }
}