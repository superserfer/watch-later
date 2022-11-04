package com.example.watchlater.overview

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.watchlater.MovieReminder
import com.example.watchlater.database.getDatabase
import com.example.watchlater.repository.MovieReminderRepository

class OverviewViewModel(application: Application) : ViewModel() {

    private val database = getDatabase(application)
    private val movieReminderRepository = MovieReminderRepository(database)

    val movieReminders = movieReminderRepository.movieReminders

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(OverviewViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return OverviewViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}