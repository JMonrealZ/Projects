package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.BuildConfig
import com.example.tmdbclient.data.api.TMDBService
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule(private val baseUrl : String) {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
//        return Retrofit.Builder()
//            .addConverterFactory(GsonConverterFactory.create())
//            .baseUrl(baseUrl)
//            .build()
        val builder = OkHttpClient.Builder()

        if(BuildConfig.DEBUG){
            builder.addInterceptor(OkHttpProfilerInterceptor())
        }

        val client = builder.build()

            return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun provideTMDBService(retrofit : Retrofit):TMDBService{
        return retrofit.create(TMDBService::class.java)
    }


}