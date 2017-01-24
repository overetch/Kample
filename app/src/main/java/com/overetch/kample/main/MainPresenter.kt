package com.overetch.kample.main

import com.overetch.kample.api.ApiService
import com.overetch.kample.data.Movie
import org.jetbrains.anko.custom.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.onComplete
import org.jetbrains.anko.uiThread
import java.security.interfaces.DSAPrivateKey
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

    override fun loadMovies() {
        mainView.onRefreshStarted()
        doAsync {
            val response = ApiService().instance().getRandomMovie().execute()
            onComplete {
                if (response.isSuccessful) {
                    val list = ArrayList<Movie>()
                    list.add(response!!.body())
                    mainView.showMovies(list)
                    mainView.onRefreshFinished()
                }
            }
        }
    }

    override fun refresh() {
    }

    private fun createRandomObjects(): ArrayList<Movie> {
        val list: ArrayList<Movie> = (1..10).mapTo(ArrayList()) { Movie(Title = "Title $it") }
        return list
    }
}