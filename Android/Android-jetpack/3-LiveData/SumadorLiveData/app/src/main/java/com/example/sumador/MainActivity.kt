package com.example.sumador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sumador.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding                       //Enlazar code to layout
    private lateinit var viewModel: ActivityMainViewModel                   //Retener datos
    private lateinit var viewModelFactory: MainActivityViewModelFactory     //TODO: ???

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModelFactory = MainActivityViewModelFactory(125)
        viewModel = ViewModelProvider(this,viewModelFactory).get(ActivityMainViewModel::class.java)

        viewModel.sumatoriaData.observe(this, Observer {
            binding.tvSumatoria.text = it.toString()
        })

        binding.apply {

//            tvSumatoria.text = viewModel.getSumatoria().toString()

            btnSumar.setOnClickListener{
                if(etNumber.text.toString().isNotEmpty()){
                    viewModel.sumar( etNumber.text.toString().toInt())
//                    tvSumatoria.text = viewModel.getSumatoria().toString()
                }
            }

        }
    }
}