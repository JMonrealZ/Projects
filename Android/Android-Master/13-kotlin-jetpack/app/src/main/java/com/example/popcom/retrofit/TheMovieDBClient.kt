package com.example.popcom.retrofit

import com.example.popcom.common.constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TheMovieDBClient {
    private val theMovieDBService: TheMovieDBService
    private val retrofit: Retrofit

    companion object{       //Patrón singleton
        var instance : TheMovieDBClient? = null
        get(){
            if(field == null){
                instance = TheMovieDBClient()
            }
            return field
        }
    }

    init{   //al iniciar la clase
        val okHttpClientBuilder = OkHttpClient.Builder()
        okHttpClientBuilder.addInterceptor(TheMovieDBInterceptor())

        val cliente = okHttpClientBuilder.build()

        retrofit = Retrofit.Builder()
            .baseUrl(constants.TMDBAPI_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(cliente)
            .build()

        theMovieDBService = retrofit.create(TheMovieDBService::class.java)
    }

    fun getTheMovieDBService() = theMovieDBService
}