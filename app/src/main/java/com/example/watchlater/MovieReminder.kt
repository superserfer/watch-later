package com.example.watchlater

import java.io.Serializable
import java.time.LocalDate
import java.util.*

data class MovieReminder(
    var title: String,
    var releaseYear: Int,
    var posterUrl: String,
    var reminderDataInput: LocalDate,
    val id: String = UUID.randomUUID().toString()
) : Serializable