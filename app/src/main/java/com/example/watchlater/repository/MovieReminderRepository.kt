package com.example.watchlater.repository

import androidx.lifecycle.LiveData
import com.example.watchlater.MovieReminder
import com.example.watchlater.database.MovieRemindersDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

class MovieReminderRepository(private val database: MovieRemindersDatabase) {

    private val todayDate = LocalDate.now()

    val movieReminders: LiveData<List<MovieReminder>> = database.movieReminderDAO.getMovieReminders()

    val movieRemindersToday: LiveData<List<MovieReminder>> = database.movieReminderDAO.getMovieReminderByDate(
        "${todayDate.year} ${todayDate.month} ${todayDate.month}"
    )

    suspend fun insertMovieReminder(movieReminder: MovieReminder) {
        withContext(Dispatchers.IO) {
            try {
                database.movieReminderDAO.insertMovieReminder(movieReminder)
            } catch (ignored: Exception) {}
        }
    }

    suspend fun deleteMovieReminder(movieReminder: MovieReminder) {
        withContext(Dispatchers.IO) {
            try {
                database.movieReminderDAO.deleteMovieReminderById(movieReminder.id)
            } catch (ignored: Exception) {}
        }
    }
}