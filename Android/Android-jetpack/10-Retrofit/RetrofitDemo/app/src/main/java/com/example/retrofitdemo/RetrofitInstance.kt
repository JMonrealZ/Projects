package com.example.retrofitdemo

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {


    companion object{

        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(30,TimeUnit.SECONDS)    //intenta conectarse por ese tiempo sino lo deja de hacer
                .readTimeout(20,TimeUnit.SECONDS)   //max tiempo entre 2 paquetes de datos(esperando respuesta del servidor)
                .writeTimeout(25,TimeUnit.SECONDS)  //enviamos al servidor
        }.build()

        //companion object se inicializa cuando la clase es cargada por primera vez
        val BASE_URL = "https://jsonplaceholder.typicode.com/"
        fun getRetrofitInstance():Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build();
        }
    }
}