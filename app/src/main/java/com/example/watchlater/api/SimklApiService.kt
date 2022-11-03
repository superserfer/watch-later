package com.example.watchlater.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.simkl.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .build()

interface SimklApiService {

    @GET("search/movie")
    fun searchMovies(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20,
        @Query("client_id") apiKey: String,
        @Query("q") searchQuery: String): Call<String>
}

object SimklApi {
    val retrofitService : SimklApiService by lazy { retrofit.create(SimklApiService::class.java) }
}