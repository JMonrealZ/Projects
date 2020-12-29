package com.example.tmdbclient.data.repository.tvshow.datasourceImpl

import com.example.tmdbclient.data.db.TvShowDao
import com.example.tmdbclient.data.model.tvShow.TvShow
import com.example.tmdbclient.data.repository.tvshow.datasource.TvshowLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TvshowLocalDataSourceImpl(private val tvShowDao: TvShowDao):
    TvshowLocalDataSource{
    override suspend fun getTvshowsFromDB(): List<TvShow> {
        return tvShowDao.getTvShows()
    }

    override suspend fun saveTvshowsToDB(tvshows: List<TvShow>) {
        CoroutineScope(Dispatchers.IO).launch{
            tvShowDao.saveTvShows(tvshows)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch{
            tvShowDao.deleteAllTvShows()
        }
    }
}