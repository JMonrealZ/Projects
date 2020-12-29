package com.example.tmdbclient.presentation.di.core

import com.example.tmdbclient.presentation.di.artist.ArtistSubcomponent
import com.example.tmdbclient.presentation.di.movie.MovieSubcomponent
import com.example.tmdbclient.presentation.di.tvshow.TvshowSubcomponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
AppModule::class,
NetModule::class,
DataBaseModule::class,
UseCaseModule::class,
RepositoryModule::class,
RemoteDataModule::class,
LocalDataModule::class,
CacheDataModule::class
])
interface AppComponent {
    //SI QUEREMOS LIMITAR EL SCOPE DE UN VIEWMODEL AL CICLO DE VIDA DE UN ACTIVITY
    //ES NECESARIO AGREGAR SUBCOMPONENTES Y UN NUEVO SCOPE PARA EL FLUJO DE DATOS

    fun movieSubcomponent():MovieSubcomponent.Factory
    fun tvShowSubponent():TvshowSubcomponent.Factory
    fun artistSubcomponent():ArtistSubcomponent.Factory
}