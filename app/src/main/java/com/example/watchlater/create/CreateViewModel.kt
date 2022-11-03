package com.example.watchlater.create

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchlater.BuildConfig
import com.example.watchlater.Movie
import com.example.watchlater.MovieReminder
import com.example.watchlater.api.SimklApi
import com.example.watchlater.api.parseMovieJsonResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import retrofit2.await
import java.time.LocalDate

class CreateViewModel : ViewModel() {

    private val _movieSaved = MutableLiveData<Boolean>()
    val movieSaved
        get() = _movieSaved

    private val _hasSetMovie = MutableLiveData<Boolean>(false)

    private val _hasSetDate = MutableLiveData<Boolean>(false)

    private val _movieReminder = MutableLiveData<MovieReminder>()
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
            reminderDataInput = LocalDate.of(9999,1,1)
        )
        _hasSetMovie.value = false
        _hasSetDate.value = false
    }

    fun setDate(year: Int, month: Int, day: Int) {
        if (_movieReminder.value == null) initMovieReminder()
        _movieReminder.value?.reminderDataInput = LocalDate.of(year, month, day)
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
            // TODO: SaveReminder to Room-DB
            _movieSaved.value = true
            initMovieReminder()
        } else {
            _movieSaved.value = false
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
}