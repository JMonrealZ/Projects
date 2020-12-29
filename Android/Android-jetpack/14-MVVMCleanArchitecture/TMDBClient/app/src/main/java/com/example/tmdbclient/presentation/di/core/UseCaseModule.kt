package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.domain.repository.ArtistRepository
import com.example.tmdbclient.domain.repository.MovieRepository
import com.example.tmdbclient.domain.repository.TvShowRespository
import com.example.tmdbclient.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun provideGetMovieUseCase(movieRepository: MovieRepository) : GetMoviesUseCase{
        return GetMoviesUseCase(movieRepository)
    }

    @Provides
    fun provideGetUpdateMovieUseCase(movieRepository: MovieRepository) : UpdateMoviesUseCase{
        return UpdateMoviesUseCase(movieRepository)
    }

    @Provides
    fun provideGetTvShowUseCase(tvshowRepository: TvShowRespository) : GetTvShowsUseCase{
        return GetTvShowsUseCase(tvshowRepository)
    }

    @Provides
    fun provideGetUpdateTvshowUseCase(tvshowRepository: TvShowRespository) : UpdateTvShowsUseCase{
        return UpdateTvShowsUseCase(tvshowRepository)
    }

    @Provides
    fun provideGetArtistUseCase(artistRepository: ArtistRepository) : GetArtistsUseCase{
        return GetArtistsUseCase(artistRepository)
    }

    @Provides
    fun provideGetUpdateArtistUseCase(artistRepository: ArtistRepository) : UpdateArtistsUseCase{
        return UpdateArtistsUseCase(artistRepository)
    }
}