package com.overetch.kample.main

import android.os.SystemClock
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.overetch.kample.api.ApiService
import com.overetch.kample.data.Movie
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.onComplete
import java.util.*

/**
 * Created by Igor.Sakovich on 21.01.2017.
 */

@InjectViewState
class MainPresenter : MvpPresenter<MainView>() {
    private var count: Int = 0

    init {
        viewState.onItemCount(count)
    }


    fun loadMovies() {
        viewState.onRefreshStarted()
        doAsync {
            val response = ApiService().instance().getRandomMovie().execute()
            SystemClock.sleep(3500)
            onComplete {
                if (response.isSuccessful) {
                    val list = ArrayList<Movie>()
                    list.add(response!!.body())
                    viewState.showMovies(list)
                    viewState.onRefreshFinished()
                }
            }
        }
    }


    private fun createRandomObjects(): ArrayList<Movie> {
        val list: ArrayList<Movie> = (1..10).mapTo(ArrayList()) { Movie(Title = "Title $it") }
        return list
    }
}