package com.overetch.kample.main

import android.util.Log
import com.overetch.kample.api.ApiService
import com.overetch.kample.data.Movie
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.onComplete
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * Created by Igor.Sakovich on 21.01.2017.
 */
class MainPresenter(private val mainView: MainContract.View) : MainContract.Presenter {


    init {
        mainView.setPresenter(this)
    }

    override fun start() {
    }

    override fun loadMovies(isRefreshing: Boolean) {
        doAsync {
            val response = ApiService().instance().getRandomMovie().execute()
            onComplete {
                if(isRefreshing){
                    mainView.onRefreshFinished()
                }
                if (response.isSuccessful) {
                    val list = ArrayList<Movie>()
                    list.add(response!!.body())
                    mainView.showMovies(list)
                }
            }
        }
//        ApiService().instance().getRandomMovie().enqueue(object : Callback<Movie> {
//            override fun onResponse(call: Call<Movie>?, response: Response<Movie>?) {
//                val list = ArrayList<Movie>()
//                list.add(response!!.body())
//                mainView.showMovies(list)
//            }
//
//            override fun onFailure(call: Call<Movie>?, t: Throwable?) {
//                Log.d("abc", "abc")
//            }
//        })

    }

    override fun refresh() {
        loadMovies(true)
//        mainView.onRefreshFinished()

    }

    private fun createRandomObjects(): ArrayList<Movie> {
        val list: ArrayList<Movie> = (1..10).mapTo(ArrayList()) { Movie(Title = "Title $it") }
        return list
    }
}