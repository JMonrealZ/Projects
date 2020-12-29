package com.example.sincronizaciondb.data.remote;

import com.example.sincronizaciondb.data.remote.model.MoviesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MovieApiService {

    @GET("movie/popular")
    Call<MoviesResponse> loadPopularMovies();
}
