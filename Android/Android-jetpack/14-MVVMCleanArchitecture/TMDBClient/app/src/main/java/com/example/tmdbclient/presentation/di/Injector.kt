package com.example.tmdbclient.presentation.di

import com.example.tmdbclient.presentation.di.artist.ArtistSubcomponent
import com.example.tmdbclient.presentation.di.movie.MovieSubcomponent
import com.example.tmdbclient.presentation.di.tvshow.TvshowSubcomponent

interface Injector {
    fun createMovieSubcomponent():MovieSubcomponent
    fun createTvShowSubcomponent():TvshowSubcomponent
    fun createArtistSubcomponent():ArtistSubcomponent
}