package com.example.tmdbclient.domain.repository

import com.example.tmdbclient.data.model.tvShow.TvShow

interface TvShowRespository {
    suspend fun getTvShows():List<TvShow>?
    suspend fun updateTvShows():List<TvShow>?
}