package com.overetch.kample

import android.app.Application
import android.util.Log
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

/**
 * Created by Igor.Sakovich on 21.02.2017.
 */
class KampleApp : Application() {
    companion object {
        lateinit var INSTANCE: KampleApp
        lateinit var cicerone: Cicerone<Router>

    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        initCicerone()
    }

    fun getNavigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }

    fun getRouter(): Router {
        return cicerone.router
    }

    fun initCicerone() {
        cicerone = Cicerone.create()
        Log.d("","" + cicerone)
    }
}