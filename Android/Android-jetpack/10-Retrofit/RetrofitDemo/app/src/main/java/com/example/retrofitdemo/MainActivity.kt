package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import kotlinx.android.synthetic.main.activity_main.*

import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var retService: AlbumService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)

//        getRequestedQueryParameters()
//        getRequestedWithPathParameters()
        uploadAlbum()
    }

    private fun getRequestedQueryParameters(){
        val responseLiveData: LiveData<Response<Albums>> = liveData {
            //val response = retService.getAlbums()
            val response = retService.getSortedAlbums(3)    //Query parameters

            emit(response)
        }
        responseLiveData.observe(this, Observer {
            val albumsList = it.body()?.listIterator()    //? save call(obtiene elemento uno por uno)
            if(albumsList!=null){
                while(albumsList.hasNext()){
                    val albumsItem = albumsList.next()
//                    Log.i("MYTAG",albumsItem.title)
                    val result = " " + "Album title: ${albumsItem.title}" + "\n" +
                            " " + "Album id: ${albumsItem.id}" + "\n" +
                            " " + "Album userid: ${albumsItem.userId}" + "\n\n\n"

                    tv.append(result)
                }
            }
        })
    }

    private fun getRequestedWithPathParameters(){
        //path parameter example
        val pathResponse : LiveData<Response<AlbumItem>> = liveData {
            val response = retService.getAlbum(3)
            emit(response)
        }
        pathResponse.observe(this, Observer {
            val title = it.body()?.title
            Toast.makeText(applicationContext,title,Toast.LENGTH_LONG).show()

        })
    }

    private fun uploadAlbum(){
        val album = AlbumItem(0,"porno corridos prrones",560)
        val postResponse : LiveData<Response<AlbumItem>> = liveData {
            val response = retService.uploadAlbum(album)
            emit(response)
        }
        postResponse.observe(this, Observer {
            val receivedAlbumItem = it.body()
            val result = " " + "Album title: ${receivedAlbumItem?.title}" + "\n" +
                    " " + "Album id: ${receivedAlbumItem?.id}" + "\n" +
                    " " + "Album userid: ${receivedAlbumItem?.userId}" + "\n\n\n"
            tv.text = result
        })
    }
}