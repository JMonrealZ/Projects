package com.example.recyclerviewdemo1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val fruitList = listOf<Fruit>(
        Fruit("mango","mamá"),
        Fruit("pera","papá"),
        Fruit("guayaba","Iván"),
        Fruit("platano","Ivonne"),
        Fruit("fresa","Memin"),
        Fruit("papaya","tío"),
        Fruit("papa","Tía"),
        Fruit("guanabaana","yo")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Kotlin synthetic(no se tiene que declarar finfById...)NO ES RECOMENDABLE USARLO
        recyclerView.setBackgroundColor(Color.YELLOW)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerViewAdapter(fruitList,{selectedFruitItem:Fruit->listItemClicked(selectedFruitItem)})
    }

    //funcion que toma el seleccionado item de la lista como parametro y lo muestra como toast
    private fun listItemClicked(fruit: Fruit){
        Toast.makeText(this@MainActivity, "Supplier name is ${fruit.supplier}",Toast.LENGTH_LONG).show()
    }
}