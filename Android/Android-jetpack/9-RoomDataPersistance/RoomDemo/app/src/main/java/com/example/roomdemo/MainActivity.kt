package com.example.roomdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdemo.databinding.ActivityMainBinding
import com.example.roomdemo.db.Subscriber
import com.example.roomdemo.db.SubscriberDatabase
import com.example.roomdemo.db.SubscriberRepository
import com.example.roomdemo.db.SubscriberViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var  binding:ActivityMainBinding
    private lateinit var subscriberViewModel: SubscriberViewModel
    private lateinit var adapter:MyRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao = SubscriberDatabase.getInstance(application).subscriberDAO
        val repository = SubscriberRepository(dao)
        val factory = SubscriberViewModelFactory(repository)
        subscriberViewModel = ViewModelProvider(this,factory).get(SubscriberViewModel::class.java)
        binding.myViewModel = subscriberViewModel
        binding.lifecycleOwner = this    //Para usar livedata with data binding necesitamos un dueño de ciclo de vida
//        displaySubsribersList()
        initRecyclerView()

        //observador para mostrar mensaje
        subscriberViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this,it,Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun initRecyclerView(){
        binding.rvSubscribers.layoutManager = LinearLayoutManager(this)
        adapter = MyRecyclerView({selectedItem:Subscriber->listItemClicked(selectedItem)})
        binding.rvSubscribers.adapter = adapter
        displaySubsribersList()
    }

//    Método que observa la tabla de room
    private fun displaySubsribersList(){
        subscriberViewModel.subscribers.observe(this, Observer {
//            Log.i("MYTAG",it.toString())
            //Se instancía cada vez que cambia? Buuuuuuuuu
//            binding.rvSubscribers.adapter = MyRecyclerView(it,{selectedItem:Subscriber->listItemClicked(selectedItem)})
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

    private fun listItemClicked(subscriber: Subscriber){
//        Toast.makeText(this,"clic con ${subscriber.name}",Toast.LENGTH_LONG).show()
        subscriberViewModel.initUpdateAndDelete(subscriber)
    }
}