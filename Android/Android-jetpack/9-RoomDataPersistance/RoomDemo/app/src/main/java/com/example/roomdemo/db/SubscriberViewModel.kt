package com.example.roomdemo.db

import android.util.Patterns
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomdemo.Event
import kotlinx.coroutines.launch
import java.util.regex.Pattern

//Pasa usar las funciones escritas en el repositorio debemos referencia al repositorio(por ello el constructor)
class SubscriberViewModel(private val repository:SubscriberRepository) : ViewModel(),Observable{
    //El constructor del viewmodel tiene un parametro así que necesitamos un viewmodel factory

    val subscribers = repository.subscribers
    private var isUpdateOrDelete = false
    private lateinit var  subscriberToUpdateOrDelete : Subscriber

    @Bindable   //Para usarlo con dataBinding
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    //variable para string messages en toast
    private val statusMessage = MutableLiveData<Event<String>>()
    val message : LiveData<Event<String>>
    get() = statusMessage


    init {
        saveOrUpdateButtonText.value = "Save";
        clearAllOrDeleteButtonText.value = "Clear all"
    }

    fun saveOrUpdate(){

        if(inputName.value == null) statusMessage.value = Event("Ingresa un nombre")
        else if(inputEmail.value == null) statusMessage.value = Event("Ingresa un email")
        else if(!Patterns.EMAIL_ADDRESS.matcher(inputEmail.value!!).matches()) statusMessage.value = Event("Ingresa un correo correcto")
        else {
            if(isUpdateOrDelete){
                subscriberToUpdateOrDelete.name = inputName.value!!
                subscriberToUpdateOrDelete.email = inputEmail.value!!
                update(subscriberToUpdateOrDelete)
            }else{
                val name = inputName.value!!
                val email = inputEmail.value!!
                insert(Subscriber(0,name,email))    //agreamos id = 0(no importa) ya que es autoincrementado
                inputName.value = null
                inputEmail.value = null
            }
        }
    }

    fun clearAllOrDelete(){
        if(isUpdateOrDelete){
            delete(subscriberToUpdateOrDelete)
        }else{
            deleteAll()
        }
    }

    fun initUpdateAndDelete(subscriber: Subscriber){
        inputName.value = subscriber.name
        inputEmail.value = subscriber.email
        isUpdateOrDelete = true
        subscriberToUpdateOrDelete = subscriber
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"
    }

//    fun insert(subscriber: Subscriber){
//        viewModelScope.launch {
//            repository.insert(subscriber)
//        }
//    }
    fun insert(subscriber: Subscriber)=viewModelScope.launch {
        val newRowId:Long = repository.insert(subscriber)
        if(newRowId > -1){
            statusMessage.value = Event("Subscriber interted successfully. $newRowId")
        }else{
            statusMessage.value = Event("Error ocurred")
        }
    }

    fun update(subscriber: Subscriber)=viewModelScope.launch {
        val rowsAffected:Int = repository.update(subscriber)
        if(rowsAffected > 0){
            inputName.value = null
            inputEmail.value = null
            isUpdateOrDelete = false
            subscriberToUpdateOrDelete = subscriber
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear all"
            statusMessage.value = Event("Subscriber updated succesfully. $rowsAffected afected")
        }else{
            statusMessage.value = Event("Error occurred")
        }
    }

    fun delete(subscriber: Subscriber)=viewModelScope.launch {
        val deletedRows:Int = repository.delete(subscriber)
        if(deletedRows > 0){
            inputName.value = null
            inputEmail.value = null
            isUpdateOrDelete = false
            subscriberToUpdateOrDelete = subscriber
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear all"
            statusMessage.value = Event("Subscriber deleted succesfully. ${deletedRows}")
        }else{
            statusMessage.value = Event("Error occurred")
        }
    }

    fun deleteAll()=viewModelScope.launch {
        val deletedRows:Int = repository.deleteAll()
        if(deletedRows > 0){
            statusMessage.value = Event("All subscribers deleted succesfully. ${deletedRows}")
        }
        else{
            statusMessage.value = Event("Error occurred")
        }

    }

    //Implementamos métodos de observable...
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
}