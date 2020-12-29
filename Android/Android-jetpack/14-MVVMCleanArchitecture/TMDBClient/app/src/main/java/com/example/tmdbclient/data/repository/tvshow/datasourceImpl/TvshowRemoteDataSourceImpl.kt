package com.example.tmdbclient.data.repository.tvshow.datasourceImpl

import com.example.tmdbclient.data.api.TMDBService
import com.example.tmdbclient.data.model.tvShow.TvShowsList
import com.example.tmdbclient.data.repository.tvshow.datasource.TvshowRemoteDataSource
import retrofit2.Response

class TvshowRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
    private val apiKey:String)
    : TvshowRemoteDataSource{

    override suspend fun getTvshows(): Response<TvShowsList> = tmdbService.getPopularTvShows(apiKey)
}