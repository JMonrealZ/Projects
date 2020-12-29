package com.anushka.bindingdemo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import com.anushka.bindingdemo2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding   //TODO:Variable global de binding
    //private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)   //TODO: Referenciamos layout con binding
//        setContentView(R.layout.activity_main)
        binding.apply {
            controlButton.setOnClickListener(){
                startOrStopProgressBar()
            }
        }
        //button = findViewById(R.id.control_button)
//        button.setOnClickListener {
//            startOrStopProgressBar()
//        }
    }
      private fun startOrStopProgressBar(){
          binding.apply {
              if(progressBar.visibility == View.GONE){
                  progressBar.visibility = View.VISIBLE
                  controlButton.text = "Stop"
              }else{
                  progressBar.visibility = View.GONE
                  controlButton.text = "Start"
              }
          }
      }
//    private fun startOrStopProgressBar() {
//        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
//        if (progressBar.visibility == View.GONE) {
//            progressBar.visibility = View.VISIBLE
//            button.text = "Stop"
//        } else {
//            progressBar.visibility = View.GONE
//            button.text = "Start"
//        }
//    }
}

