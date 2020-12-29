package com.example.tmdbclient.data.repository.tvshow.datasource

import com.example.tmdbclient.data.model.tvShow.TvShow

interface TvshowLocalDataSource {
    suspend fun getTvshowsFromDB():List<TvShow>
    suspend fun saveTvshowsToDB(tvshows:List<TvShow>)
    suspend fun clearAll()
}