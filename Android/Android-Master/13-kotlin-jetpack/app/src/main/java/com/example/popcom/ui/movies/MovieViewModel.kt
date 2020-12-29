package com.example.popcom.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.popcom.repositorio.TheMovieDBRepository
import com.example.popcom.retrofit.models.Movie

class MovieViewModel : ViewModel() {
    private var theMovieDBRepository: TheMovieDBRepository
    private var popularMovies: LiveData<List<Movie>>

    init{
        theMovieDBRepository = TheMovieDBRepository()
        popularMovies = theMovieDBRepository?.popularMovies()!!
    }

    fun getPopularMovies(): LiveData<List<Movie>>{
        return popularMovies
    }
}