package com.overetch.kample.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.overetch.kample.data.Movie
import java.util.*

/**
 * Created by Igor.Sakovich on 21.01.2017.
 */
@StateStrategyType(AddToEndSingleStrategy::class)
interface MainView : MvpView {
    fun showMovies(movies: ArrayList<Movie>)
    fun onRefreshFinished()
    fun onRefreshStarted()

}
