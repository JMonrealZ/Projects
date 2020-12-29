package com.example.tmdbclient.presentation.di.tvshow

import com.example.tmdbclient.presentation.artist.ArtistActivity
import com.example.tmdbclient.presentation.tvshow.TvshowActivity
import dagger.Subcomponent

@TvshowScope
@Subcomponent(modules = [TvshowModule::class])
interface TvshowSubcomponent {
    fun inject(tvshowActivity: TvshowActivity)

    //SUBCOMPONENT FACTORY
    @Subcomponent.Factory
    interface Factory{
        fun create():TvshowSubcomponent
    }
}