package com.example.tmdbclient.presentation

import android.app.Application
import com.example.tmdbclient.BuildConfig
import com.example.tmdbclient.presentation.di.Injector
import com.example.tmdbclient.presentation.di.artist.ArtistSubcomponent
import com.example.tmdbclient.presentation.di.core.*
import com.example.tmdbclient.presentation.di.movie.MovieSubcomponent
import com.example.tmdbclient.presentation.di.tvshow.TvshowSubcomponent

class App : Application(), Injector{
    private lateinit var appComponent:AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule(BuildConfig.API_KEY))
            .build()
    }



    override fun createMovieSubcomponent(): MovieSubcomponent {
        return appComponent.movieSubcomponent().create()
    }

    override fun createTvShowSubcomponent(): TvshowSubcomponent {
        return appComponent.tvShowSubponent().create()
    }

    override fun createArtistSubcomponent(): ArtistSubcomponent {
        return appComponent.artistSubcomponent().create()
    }
}