package com.overetch.kample.main

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.overetch.kample.FirstFragment
import com.overetch.kample.KampleApp
import com.overetch.kample.R
import com.overetch.kample.utils.Screens
import org.jetbrains.anko.toast
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import android.app.Activity
import android.app.ActivityManager




@StateStrategyType(AddToEndSingleStrategy::class)
class MainActivity : MvpAppCompatActivity(), MvpView {

    lateinit var mDrawerLayout: DrawerLayout
    lateinit var mDebugDrawer: DrawerLayout
    lateinit var navigator: Navigator

    @InjectPresenter
    lateinit var mPresenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        setNavigator()
        setUpToolbar()
        setUpNavigationDrawer()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun setNavigator() {
        navigator = object : SupportFragmentNavigator(supportFragmentManager, R.id.contentFrame) {
            override fun createFragment(screenKey: String?, data: Any?): Fragment {
                when (screenKey) {
                    Screens.SCREEN_ROOT -> return MainFragment()
                    Screens.SCREEN_FIRST -> return FirstFragment()
                    Screens.SCREEN_SECOND -> return FirstFragment()
                    else -> throw RuntimeException("Screen key not found")
                }
            }

            override fun exit() {
                finish()
            }

            override fun showSystemMessage(message: String?) {
                toast(message!!)
            }

        }

    }

    override fun onResume() {
        super.onResume()
        KampleApp.INSTANCE.getNavigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        KampleApp.INSTANCE.getNavigatorHolder().removeNavigator()
    }

    private fun setUpNavigationDrawer() {
        mDrawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark)
        val navigationView = findViewById(R.id.nav_view) as NavigationView
        setupDrawerContent(navigationView)
        setUpDebugDrawer()
    }

    private fun setUpDebugDrawer() {

    }

    private fun setupDrawerContent(navigationView: NavigationView) {
        navigationView.setNavigationItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.menu_item_fragment_first -> mPresenter.navigateTo(Screens.SCREEN_FIRST)
                R.id.statistics_navigation_menu_item -> mPresenter.navigateTo(Screens.SCREEN_SECOND)
            }
            menuItem.isChecked = true
            mDrawerLayout.closeDrawers()
            true
        }
    }

    private fun openStatisticsActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }

    private fun setUpToolbar() {
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        val actionBar = supportActionBar
        setSupportActionBar(toolbar)
        actionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
    }


    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
        val fragmentCount = supportFragmentManager.backStackEntryCount
        if (supportFragmentManager.backStackEntryCount > 1) {
            super.onBackPressed()
        } else {

        }
    }
}




