package com.example.tmdbclient.presentation.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.tmdbclient.domain.usecase.GetMoviesUseCase
import com.example.tmdbclient.domain.usecase.GetTvShowsUseCase
import com.example.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.example.tmdbclient.domain.usecase.UpdateTvShowsUseCase

class tvshowViewModel (
    private val getTvShowsUseCase: GetTvShowsUseCase,
    private val updateTvShowsUseCase: UpdateTvShowsUseCase
): ViewModel(){

    //CUANDO UN VIEW MODEL TIENE PARAMETROS HAY QUE CREAR UN FACTORY

    //No le pasamos scope debido a que en datasources indicamos el thread
    fun getTvshows() = liveData {
        val tvshowList = getTvShowsUseCase.execute()
        emit(tvshowList)
    }

    fun updateTvshows() = liveData {
        val tvshowList = updateTvShowsUseCase.execute()
        emit(tvshowList)
    }
}