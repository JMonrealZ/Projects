package com.example.sincronizaciondb.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.sincronizaciondb.data.MovieRepository;
import com.example.sincronizaciondb.data.local.entity.MovieEntity;
import com.example.sincronizaciondb.data.network.Resource;

import java.util.List;

public class MovieViewModel extends ViewModel {
    public final LiveData<Resource<List<MovieEntity>>> popularMovies;
    public MovieRepository movieRepository;

    public MovieViewModel(){
        movieRepository = new MovieRepository();
        popularMovies = movieRepository.getPopularMovies();
    }

    public LiveData<Resource<List<MovieEntity>>> getPopularMovies(){
        return popularMovies;
    }
}
