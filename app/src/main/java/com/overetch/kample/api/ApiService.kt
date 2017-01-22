package com.overetch.kample.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Igor.Sakovich on 22.01.2017.
 */
open class ApiService{
    private val URL_ENDPOINT = "https://random-movie.herokuapp.com/"

    fun instance() : api{
        val retrofit = Retrofit.Builder()
                .baseUrl(URL_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient())
                .build()

        return retrofit.create(api::class.java)
    }

}