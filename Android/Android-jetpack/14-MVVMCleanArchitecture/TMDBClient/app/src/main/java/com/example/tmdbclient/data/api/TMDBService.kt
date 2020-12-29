package com.example.tmdbclient.data.api

import com.example.tmdbclient.data.model.artist.ArtistList
import com.example.tmdbclient.data.model.movie.MovieList
import com.example.tmdbclient.data.model.tvShow.TvShowsList
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface TMDBService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key")apiKey:String):Response<MovieList>

    @GET("tv/popular")
    suspend fun getPopularTvShows(@Query("api_key")apiKey:String):Response<TvShowsList>

    @GET("person/popular")
    suspend fun getPopularArtists(@Query("api_key")apiKey:String):Response<ArtistList>
}