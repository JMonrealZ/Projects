package com.example.tmdbclient.data.repository.tvshow.datasource

import com.example.tmdbclient.data.model.tvShow.TvShow

interface TvshowCacheDataSource {
    suspend fun getTvshowsFromCache():List<TvShow>
    suspend fun saveTvshowsToCache(tvshows:List<TvShow>)
}