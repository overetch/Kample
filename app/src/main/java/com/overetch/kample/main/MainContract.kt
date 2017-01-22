package com.overetch.kample.main

import com.overetch.kample.BasePresenter
import com.overetch.kample.BaseView
import com.overetch.kample.data.Movie
import java.util.*

/**
 * Created by Igor.Sakovich on 21.01.2017.
 */

interface MainContract {
    interface View : BaseView<Presenter> {
        fun showMovies(movies: ArrayList<Movie>)
        fun onRefreshFinished()
    }


    interface Presenter : BasePresenter {
        fun loadMovies(isRefreshing : Boolean = false)
        fun refresh()
    }
}
