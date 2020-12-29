package com.example.tmdbclient.presentation.di.core

import android.content.Context
import com.example.tmdbclient.presentation.di.artist.ArtistSubcomponent
import com.example.tmdbclient.presentation.di.movie.MovieSubcomponent
import com.example.tmdbclient.presentation.di.tvshow.TvshowSubcomponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [MovieSubcomponent::class,TvshowSubcomponent::class,ArtistSubcomponent::class])
class AppModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideApplicationContext():Context{
        return context.applicationContext
    }
}