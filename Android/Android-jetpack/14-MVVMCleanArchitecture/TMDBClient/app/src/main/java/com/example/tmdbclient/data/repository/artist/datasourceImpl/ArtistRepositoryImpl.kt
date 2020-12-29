package com.example.tmdbclient.data.repository.artist.datasourceImpl

import android.util.Log
import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.model.movie.Movie
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistLocalDataSource
import com.example.tmdbclient.data.repository.artist.datasource.ArtistRemoteDataSource
import com.example.tmdbclient.domain.repository.ArtistRepository
import java.lang.Exception

class ArtistRepositoryImpl (
    private val artistRemoteDataSource: ArtistRemoteDataSource,
    private val artistLocalDataSource: ArtistLocalDataSource,
    private val artistCacheDataSource: ArtistCacheDataSource
) : ArtistRepository{
    override suspend fun getArtists(): List<Artist>? {
        return getArtistsFromCache()
    }

    override suspend fun updateArtists(): List<Artist>? {
        val newListOfArtists = getArtistsFromAPI()
        artistLocalDataSource.clearAll()
        artistLocalDataSource.saveArtistsToDB(newListOfArtists)
        artistCacheDataSource.saveArtistsToCache(newListOfArtists)
        return newListOfArtists
    }

    suspend fun getArtistsFromAPI():List<Artist>{
        lateinit var artistList:List<Artist>
        try{
            val response = artistRemoteDataSource.getArtists()
            val body = response.body()
            if(body != null){
                artistList = body.artists
            }
        }catch (exception: Exception){
            Log.i("MyTag",exception.message.toString())
        }
        return artistList
    }

    suspend fun getArtistsFromDB():List<Artist>{
        lateinit var artistsList:List<Artist>
        try{
            artistsList = artistLocalDataSource.getArtistFromDB()
        }catch (exception: Exception){
            Log.i("MyTag",exception.message.toString())
        }

        if(artistsList.size > 0){
            return artistsList
        }else{
            artistsList = getArtistsFromAPI()
            artistLocalDataSource.saveArtistsToDB(artistsList)
        }
        return artistsList
    }

    suspend fun getArtistsFromCache():List<Artist>{
        lateinit var artistList:List<Artist>
        try{
            artistList = artistCacheDataSource.getArtistsFromCache()
        }catch (exception: Exception){
            Log.i("MyTag",exception.message.toString())
        }

        if(artistList.size > 0){
            return artistList
        }else{
            artistList = getArtistsFromDB()
            artistCacheDataSource.saveArtistsToCache(artistList)
        }
        return artistList
    }
}