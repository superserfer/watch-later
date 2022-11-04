package com.example.watchlater

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.time.LocalDate
import java.util.*

@Entity(tableName = "movie_reminders")
data class MovieReminder(
    var title: String,
    var releaseYear: Int,
    var posterUrl: String,
    var reminderDataInput: String,
    @PrimaryKey
    val id: String = UUID.randomUUID().toString()
) : Serializable