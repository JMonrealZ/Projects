package com.example.tmdbclient.domain.usecase

import com.example.tmdbclient.data.model.tvShow.TvShow
import com.example.tmdbclient.domain.repository.TvShowRespository

class UpdateTvShowsUseCase(private val tvShowRespository: TvShowRespository) {
    suspend fun execute():List<TvShow>? = tvShowRespository.updateTvShows()
}