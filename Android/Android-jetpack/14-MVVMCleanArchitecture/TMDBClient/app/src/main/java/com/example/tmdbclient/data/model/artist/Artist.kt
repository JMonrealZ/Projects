package com.example.tmdbclient.data.model.artist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_artists")
data class Artist(
    @PrimaryKey
    val id: Int,
    val name: String,
    val popularity: Double,
    val profile_path: String
)