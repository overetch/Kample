package com.overetch.kample.main

import com.arellomobile.mvp.MvpPresenter
import com.overetch.kample.KampleApp
import com.overetch.kample.utils.Screens

/**
 * Created by Igor.Sakovich on 21.02.2017.
 */

class MainActivityPresenter : MvpPresenter<MainActivity>() {

    override fun onFirstViewAttach() {
        KampleApp.INSTANCE.getRouter().navigateTo(Screens.SCREEN_ROOT)
    }

    fun navigateTo(screenName: String) {
        KampleApp.INSTANCE.getRouter().navigateTo(screenName)
    }

    fun newScreenChain(screenName: String) {
        KampleApp.INSTANCE.getRouter().newScreenChain(screenName)
    }

    fun newRootScreen(screenName: String) {
        KampleApp.INSTANCE.getRouter().newRootScreen(screenName)
    }


}
