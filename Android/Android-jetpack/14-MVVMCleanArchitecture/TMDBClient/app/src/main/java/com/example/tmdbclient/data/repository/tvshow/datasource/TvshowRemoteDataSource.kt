package com.example.tmdbclient.data.repository.tvshow.datasource

import com.example.tmdbclient.data.model.tvShow.TvShowsList
import retrofit2.Response

interface TvshowRemoteDataSource {
    suspend fun getTvshows(): Response<TvShowsList>
}