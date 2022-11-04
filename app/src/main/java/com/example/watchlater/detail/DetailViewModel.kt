package com.example.watchlater.detail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.watchlater.MovieReminder
import com.example.watchlater.create.CreateViewModel
import com.example.watchlater.database.getDatabase
import com.example.watchlater.repository.MovieReminderRepository
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : ViewModel() {

    private val database = getDatabase(application)
    private val movieReminderRepository = MovieReminderRepository(database)

    private val _navigateBack = MutableLiveData(false)
    val navigateBack
        get() = _navigateBack

    fun deleteMovieReminder(movieReminder: MovieReminder) {
        viewModelScope.launch {
            movieReminderRepository.deleteMovieReminder(movieReminder)
            _navigateBack.postValue(true)
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return DetailViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}