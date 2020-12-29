package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.example.tmdbclient.data.repository.artist.datasourceImpl.ArtistRepositoryImpl
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDatasource
import com.example.tmdbclient.data.repository.movie.datasource.MovieLocalDatasource
import com.example.tmdbclient.data.repository.movie.datasource.MovieRemoteDatasource
import com.example.tmdbclient.data.repository.movie.datasourceImpl.MovieRepositorylmpl
import com.example.tmdbclient.data.repository.tvshow.datasource.TvshowCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvshowLocalDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvshowRemoteDataSource
import com.example.tmdbclient.data.repository.tvshow.datasourceImpl.TvshowRepositoryImpl
import com.example.tmdbclient.domain.repository.ArtistRepository
import com.example.tmdbclient.domain.repository.MovieRepository
import com.example.tmdbclient.domain.repository.TvShowRespository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule(){

    @Singleton
    @Provides
    fun provideMovieRepository(
        movieRemoteDatasource: MovieRemoteDatasource,
        movieLocalDatasource: MovieLocalDatasource,
        movieCacheDatasource: MovieCacheDatasource
    ) : MovieRepository{
        return MovieRepositorylmpl(
            movieRemoteDatasource,
            movieLocalDatasource,
            movieCacheDatasource
        )
    }

    @Singleton
    @Provides
    fun provideArtistRepository(
        artistRemoteDatasource: ArtistRemoteDataSource,
        artistLocalDatasource: ArtistLocalDataSource,
        artistCacheDatasource: ArtistCacheDataSource
    ) : ArtistRepository{
        return ArtistRepositoryImpl(
           artistRemoteDatasource,
            artistLocalDatasource,
            artistCacheDatasource
        )
    }

    @Singleton
    @Provides
    fun provideTvshowRepository(
        tvshowRemoteDataSource: TvshowRemoteDataSource,
        tvshowLocalDataSource: TvshowLocalDataSource,
        tvshowCacheDataSource: TvshowCacheDataSource
    ) : TvShowRespository{
        return TvshowRepositoryImpl(
            tvshowRemoteDataSource,
            tvshowLocalDataSource,
            tvshowCacheDataSource
        )
    }


}