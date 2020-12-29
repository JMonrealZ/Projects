package com.example.tmdbclient.data.repository.tvshow.datasourceImpl

import android.util.Log
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.model.tvShow.TvShow
import com.example.tmdbclient.data.repository.tvshow.datasource.TvshowCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvshowLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvshowRemoteDataSource
import com.example.tmdbclient.domain.repository.TvShowRespository
import java.lang.Exception

class TvshowRepositoryImpl (
    private val tvshowRemoteDataSource: TvshowRemoteDataSource,
    private val tvshowLocalDataSource: TvshowLocalDataSource,
    private val tvshowCacheDataSource: TvshowCacheDataSource
) : TvShowRespository{
    override suspend fun getTvShows(): List<TvShow>? {
        return getTvshowsFromCache()
    }

    override suspend fun updateTvShows(): List<TvShow>? {
        val newListOfTvShows = getTvshowsFromAPI()
        tvshowLocalDataSource.clearAll()
        tvshowLocalDataSource.saveTvshowsToDB(newListOfTvShows)
        tvshowCacheDataSource.saveTvshowsToCache(newListOfTvShows)
        return newListOfTvShows
    }

    suspend fun getTvshowsFromAPI():List<TvShow>{
        lateinit var tvshowList:List<TvShow>
        try{
            val response = tvshowRemoteDataSource.getTvshows()
            val body = response.body()
            if(body != null){
                tvshowList = body.tvShows
            }
        }catch (exception: Exception){
            Log.i("MyTag",exception.message.toString())
        }
        return tvshowList
    }

    suspend fun getTvshowsFromDB():List<TvShow>{
        lateinit var tvshowList:List<TvShow>
        try{
            tvshowList = tvshowLocalDataSource.getTvshowsFromDB()
        }catch (exception: Exception){
            Log.i("MyTag",exception.message.toString())
        }

        if(tvshowList.size > 0){
            return tvshowList
        }else{
            tvshowList = getTvshowsFromDB()
            tvshowLocalDataSource.saveTvshowsToDB(tvshowList)
        }
        return tvshowList
    }

    suspend fun getTvshowsFromCache():List<TvShow>{
        lateinit var tvshowList:List<TvShow>
        try{
            tvshowList = tvshowCacheDataSource.getTvshowsFromCache()
        }catch (exception: Exception){
            Log.i("MyTag",exception.message.toString())
        }

        if(tvshowList.size > 0){
            return tvshowList
        }else{
            tvshowList = getTvshowsFromDB()
            tvshowCacheDataSource.saveTvshowsToCache(tvshowList)
        }
        return tvshowList
    }
}