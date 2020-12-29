package com.example.retrofitdemo

import com.google.gson.annotations.SerializedName

data class AlbumItem(
    //@SerializedName("id")//Este es el nombre con el que conocerá cuando se serializa o deserializa
    val id: Int,
    val title: String,
    val userId: Int
)