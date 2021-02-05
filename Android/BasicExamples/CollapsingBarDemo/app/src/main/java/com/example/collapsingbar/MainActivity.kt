package com.example.collapsingbar

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.collapsingbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val fruitList = listOf<Fruit>(
        Fruit("mango","mamá"),
        Fruit("pera","papá"),
        Fruit("guayaba","Iván"),
        Fruit("platano","Ivonne"),
        Fruit("fresa","Memin"),
        Fruit("papaya","tío"),
        Fruit("papa","Tía"),
        Fruit("guanabaana","yo"),
        Fruit("mango","mamá"),
        Fruit("pera","papá"),
        Fruit("guayaba","Iván"),
        Fruit("platano","Ivonne"),
        Fruit("fresa","Memin"),
        Fruit("papaya","tío"),
        Fruit("papa","Tía"),
        Fruit("guanabaana","yo"),
        Fruit("mango","mamá"),
        Fruit("pera","papá"),
        Fruit("guayaba","Iván"),
        Fruit("platano","Ivonne"),
        Fruit("fresa","Memin"),
        Fruit("papaya","tío"),
        Fruit("papa","Tía"),
        Fruit("guanabaana","yo")
    )

    private lateinit var adapter : RecyclerViewAdapter
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        adapter = RecyclerViewAdapter(fruitList,{selectedFruitItem:Fruit->listItemClicked(selectedFruitItem)})

        binding.rvDocuments.setBackgroundColor(Color.YELLOW)
        binding.rvDocuments.layoutManager = LinearLayoutManager(this)
//        binding.rvDocuments.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        binding.rvDocuments.adapter = adapter
    }

    private fun listItemClicked(fruit: Fruit){
        Toast.makeText(this, "Supplier name is ${fruit.supplier}", Toast.LENGTH_LONG).show()
    }
}