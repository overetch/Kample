package com.overetch.kample.api

import com.overetch.kample.data.Movie
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Igor.Sakovich on 22.01.2017.
 */
interface api {
    @GET("random")
    fun getRandomMovie() : Call<Movie>

}