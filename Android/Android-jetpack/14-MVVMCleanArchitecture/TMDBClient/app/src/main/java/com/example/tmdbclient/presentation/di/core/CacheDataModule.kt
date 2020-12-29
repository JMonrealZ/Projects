package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.datasourceImpl.ArtistCacheDataSourceImpl
import com.example.tmdbclient.data.repository.movie.datasource.MovieCacheDatasource
import com.example.tmdbclient.data.repository.movie.datasourceImpl.MovieCacheDataSourceImpl
import com.example.tmdbclient.data.repository.tvshow.datasource.TvshowCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.datasourceImpl.TvshowCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheDataModule {

    @Singleton
    @Provides
    fun provideMovieCacheDataSource():MovieCacheDatasource{
        return MovieCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideArtistCacheDataSource():ArtistCacheDataSource{
        return ArtistCacheDataSourceImpl()
    }

    @Singleton
    @Provides
    fun provideTvshowCacheDataSource():TvshowCacheDataSource{
        return TvshowCacheDataSourceImpl()
    }
}