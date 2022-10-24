package com.example.watchlater

import java.io.Serializable
import java.util.*

data class MovieReminder(
    var title: String,
    var releaseYear: Int,
    var posterUrl: String,
    var rating: Double,
    var reminderDataInput: Date,
    val id: String = UUID.randomUUID().toString()
) : Serializable