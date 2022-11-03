package com.example.watchlater.api

import com.example.watchlater.Movie
import org.json.JSONArray

fun parseMovieJsonResult(jsonResult: JSONArray): ArrayList<Movie> {
    val movieList = ArrayList<Movie>()

    for(i in 0 until jsonResult.length()) {
        val movieJson = jsonResult.getJSONObject(i)
        val title = movieJson.getString("title")
        val year = movieJson.getInt("year")
        val poster = "https://simkl.in/posters/" + movieJson.getString("poster") + "_ca.jpg"

        val movie = Movie(title, year, poster)
        movieList.add(movie)
    }

    return movieList
}