package com.example.watchlater.create

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.watchlater.BuildConfig
import com.example.watchlater.Movie
import com.example.watchlater.MovieReminder
import com.example.watchlater.api.SimklApi
import com.example.watchlater.api.parseMovieJsonResult
import com.example.watchlater.database.getDatabase
import com.example.watchlater.repository.MovieReminderRepository
import com.example.watchlater.utils.sendNotification
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import retrofit2.await
import java.time.LocalDate

class CreateViewModel(application: Application) : ViewModel() {

    private val database = getDatabase(application)
    private val movieReminderRepository = MovieReminderRepository(database)

    private val _hasSetMovie = MutableLiveData<Boolean>(false)
    val hasSetMovie
        get() = _hasSetMovie

    private val _hasSetDate = MutableLiveData<Boolean>(false)
    val hasSetDate
        get() = _hasSetDate

    private val _movieReminder = MutableLiveData(MovieReminder(
        title = "",
        releaseYear = 2000,
        posterUrl = "",
        reminderDataInput = ""
    ))
    val movieReminder
        get() = _movieReminder

    private val _searchedMovie = MutableLiveData<ArrayList<Movie>>(arrayListOf())
    val searchedMovie
        get() = _searchedMovie

    private fun initMovieReminder() {
        _movieReminder.value = MovieReminder(
            title = "",
            releaseYear = 2000,
            posterUrl = "",
            reminderDataInput = ""
        )
        _hasSetMovie.value = false
        _hasSetDate.value = false
    }

    fun setDate(year: Int, month: Int, day: Int) {
        if (_movieReminder.value == null) initMovieReminder()
        _movieReminder.value?.reminderDataInput = "$year $month $day"
        _hasSetDate.value = true
    }

    fun setMovie(movie: Movie) {
        if (_movieReminder.value == null) initMovieReminder()
        _movieReminder.value?.title = movie.title
        _movieReminder.value?.releaseYear = movie.releaseYear
        _movieReminder.value?.posterUrl = movie.posterUrl
        _hasSetMovie.value = true
   }

    fun saveMovieReminder() {
        if (_hasSetMovie.value == true && _hasSetDate.value == true) {
            viewModelScope.launch {
                _movieReminder.value?.let { movieReminderRepository.insertMovieReminder(it) }
            }
            initMovieReminder()
            _searchedMovie.value = arrayListOf()
        }
    }

    fun searchMovie(searchQuery: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    searchedMovie.postValue(
                        parseMovieJsonResult(
                            JSONArray(SimklApi.retrofitService.searchMovies(apiKey = BuildConfig.api_key, searchQuery = searchQuery).await()
                            )
                        )
                    )
                } catch (e: Exception) { }
            }
        }
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CreateViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CreateViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}