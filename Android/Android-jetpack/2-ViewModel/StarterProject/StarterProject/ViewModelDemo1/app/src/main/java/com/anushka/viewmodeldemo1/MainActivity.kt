package com.anushka.viewmodeldemo1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.anushka.viewmodeldemo1.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //private var count = 0

    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

//        binding.countText.text = count.toString()
//        binding.button.setOnClickListener {
//            count++
//            binding.countText.text = count.toString()
//        }
        binding.apply {
            countText.text = viewModel.getCurrentCount().toString()

            button.setOnClickListener{
                countText.text = viewModel.getUpdatedCount().toString()
            }
        }
    }
}
