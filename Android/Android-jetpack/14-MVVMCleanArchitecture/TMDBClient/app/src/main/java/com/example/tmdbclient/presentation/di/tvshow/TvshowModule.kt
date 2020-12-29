package com.example.tmdbclient.presentation.di.tvshow

import com.example.tmdbclient.domain.usecase.*
import com.example.tmdbclient.presentation.artist.ArtistViewModelFactory
import com.example.tmdbclient.presentation.movie.MovieViewModelFactory
import com.example.tmdbclient.presentation.tvshow.tvshowViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvshowModule {

    @TvshowScope
    @Provides
    fun provideTvshowViewModelFactory(
        getTvShowsUseCase: GetTvShowsUseCase,
        updateTvShowsUseCase: UpdateTvShowsUseCase
    ) : tvshowViewModelFactory{
        return tvshowViewModelFactory(
            getTvShowsUseCase,
            updateTvShowsUseCase
        )
    }
}