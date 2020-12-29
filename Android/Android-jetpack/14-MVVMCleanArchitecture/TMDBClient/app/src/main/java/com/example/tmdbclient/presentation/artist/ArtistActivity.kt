package com.example.tmdbclient.presentation.artist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.tmdbclient.R
import com.example.tmdbclient.databinding.ActivityHomeBinding

class ArtistActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_artist)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_artist)
    }
}