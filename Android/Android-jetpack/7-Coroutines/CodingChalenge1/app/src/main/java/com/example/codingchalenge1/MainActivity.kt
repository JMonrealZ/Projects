package com.example.codingchalenge1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.codingchalenge1.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.apply {
            btnBackground.setOnClickListener{
                CoroutineScope(Dispatchers.IO).launch { background() }
            }
            btnMain.setOnClickListener{
                CoroutineScope(Dispatchers.Main).launch { main() }
            }
        }
    }

    fun background(){
        binding.tvBack.text = Thread.currentThread().name;
    }

    fun main(){
        binding.tvMain.text = Thread.currentThread().name;
    }
}