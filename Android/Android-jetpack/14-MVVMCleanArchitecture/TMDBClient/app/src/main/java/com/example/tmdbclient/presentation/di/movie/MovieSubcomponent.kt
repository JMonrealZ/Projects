package com.example.tmdbclient.presentation.di.movie

import com.example.tmdbclient.presentation.artist.ArtistActivity
import com.example.tmdbclient.presentation.movie.MovieActivity
import dagger.Subcomponent

@MovieScope
@Subcomponent(modules = [MovieModule::class])
interface MovieSubcomponent {
    fun inject(movieActivity: MovieActivity)

    //SUBCOMPONENT FACTORY
    @Subcomponent.Factory
    interface Factory{
        fun create():MovieSubcomponent
    }
}