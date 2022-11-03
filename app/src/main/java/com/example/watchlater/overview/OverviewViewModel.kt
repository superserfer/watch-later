package com.example.watchlater.overview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.watchlater.BuildConfig
import com.example.watchlater.MovieReminder
import com.example.watchlater.api.SimklApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.await
import java.time.LocalDate
import java.util.*

class OverviewViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private val _movieString = MutableLiveData<String>("Not yet loaded")
    val movieString
        get() = _movieString

    val movieReminders = MutableLiveData<List<MovieReminder>>(listOf(
        MovieReminder("Star Wars 3", 2005, "https://simkl.in/posters/84/845586d5ed3ff7c5_ca.jpg", LocalDate.of(2022,12,3)),
        MovieReminder("Star Wars 3", 2005, "https://simkl.in/posters/84/845586d5ed3ff7c5_ca.jpg", LocalDate.of(2022,12,3)),
        MovieReminder("Star Wars 3", 2005, "https://simkl.in/posters/84/845586d5ed3ff7c5_ca.jpg", LocalDate.of(2022,12,3)),
        MovieReminder("Star Wars 3", 2005, "https://simkl.in/posters/84/845586d5ed3ff7c5_ca.jpg", LocalDate.of(2022,12,3)),
        MovieReminder("Star Wars 3", 2005, "https://simkl.in/posters/84/845586d5ed3ff7c5_ca.jpg", LocalDate.of(2022,12,3)),
        MovieReminder("Star Wars 3", 2005, "https://simkl.in/posters/84/845586d5ed3ff7c5_ca.jpg", LocalDate.of(2022,12,3))
    ))
}