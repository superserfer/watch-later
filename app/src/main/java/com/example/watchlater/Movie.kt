package com.example.watchlater

import java.io.Serializable
import java.util.*

data class Movie(
    var title: String,
    var releaseYear: Int,
    var posterUrl: String,
    val id: String = UUID.randomUUID().toString()
) : Serializable