package com.example.watchlater.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.watchlater.database.getDatabase
import com.example.watchlater.repository.MovieReminderRepository
import com.example.watchlater.utils.sendNotification

class MovieReminderWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "MovieReminderWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = MovieReminderRepository(database)
        return try {
            repository.movieRemindersToday.value?.forEach { movieReminder ->
                sendNotification(applicationContext, movieReminder)
            }
            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
    }
}