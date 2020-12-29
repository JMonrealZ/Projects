package com.example.popcom.retrofit

import com.example.popcom.retrofit.models.PopularMoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface TheMovieDBService {
    @GET("movie/popular")
    fun getPopularMovies(): Call<PopularMoviesResponse>
}