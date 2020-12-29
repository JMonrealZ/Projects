package com.example.tmdbclient.data.repository.tvshow.datasourceImpl

import com.example.tmdbclient.data.model.artist.Artist
import com.example.tmdbclient.data.model.tvShow.TvShow
import com.example.tmdbclient.data.repository.artist.datasource.ArtistCacheDataSource
import com.example.tmdbclient.data.repository.tvshow.datasource.TvshowCacheDataSource

class TvshowCacheDataSourceImpl :
    TvshowCacheDataSource{
    private var tvShowList = ArrayList<TvShow>()
    override suspend fun getTvshowsFromCache(): List<TvShow> {
        return tvShowList
    }

    override suspend fun saveTvshowsToCache(tvshows: List<TvShow>) {
        tvShowList.clear()
        tvShowList = ArrayList(tvshows)
    }

}