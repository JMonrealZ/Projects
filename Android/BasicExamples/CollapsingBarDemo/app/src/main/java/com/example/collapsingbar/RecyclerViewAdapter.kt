package com.example.collapsingbar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*

class RecyclerViewAdapter(private val fruitList:List<Fruit>, private val clickListener:(Fruit)->Unit) : RecyclerView.Adapter<ViewHolder>(){
    //Unit = Void en java(Estamos pasando un método como on clickListener)

    //Mejor método para el onClicListener del listView:
    // (usando kotlin higher order functions and lambda expressions)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.list_item,parent,false)
        return ViewHolder(listItem)
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.view.tvName.text = fruitList.get(position).name

        //Es una mejor práctica hacer esto:
        holder.bind(fruitList[position],clickListener)
    }
}

class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
    fun bind(fruit: Fruit,clickListener:(Fruit)->Unit){
        view.tvName.text = fruit.name
        view.setOnClickListener{
            clickListener(fruit)
        }
    }
}