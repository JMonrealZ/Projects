package com.example.roomdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdemo.databinding.ListItemBinding
import com.example.roomdemo.db.Subscriber
import java.util.ArrayList

class MyRecyclerView(private val clickListener:(Subscriber)->Unit):RecyclerView.Adapter<MyViewHolder>(){

//    private val subscribersList: ArrayList<Subscriber>()
    val subscribersList = arrayListOf<Subscriber>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //Creamos el list item
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListItemBinding =
            DataBindingUtil.inflate(layoutInflater,R.layout.list_item,parent,false)
        return MyViewHolder(binding,clickListener)    //De aquí brinca a clase de MyViewHolder
    }

    override fun getItemCount(): Int {
        return subscribersList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscribersList[position])
    }

    fun setList(subscribers: List<Subscriber>){
        subscribersList.clear()
       subscribersList.addAll(subscribers)
    }
}

class MyViewHolder(val binding:ListItemBinding,
                   private val clickListener:(Subscriber)->Unit):RecyclerView.ViewHolder(binding.root){
    //Utilizamos esta clase para enlazar cada listitem
    fun bind(subscriber: Subscriber){
        binding.apply {
            nameTextView.text = subscriber.name
            emailTextView.text = subscriber.email
            linearLayout.setOnClickListener{
                clickListener(subscriber)
            }
        }
    }
}