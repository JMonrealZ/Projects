package com.example.tmdbclient.presentation.di.artist

import com.example.tmdbclient.presentation.artist.ArtistActivity
import dagger.Subcomponent

@ArtistScope
@Subcomponent(modules = [ArtistModule::class])
interface ArtistSubcomponent {
    fun inject(artistActivity: ArtistActivity)

    //SUBCOMPONENT FACTORY
    @Subcomponent.Factory
    interface Factory{
        fun create():ArtistSubcomponent
    }
}