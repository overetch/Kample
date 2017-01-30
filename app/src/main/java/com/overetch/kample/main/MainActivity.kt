package com.overetch.kample.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.overetch.kample.ActivityUtils
import com.overetch.kample.R


class MainActivity : AppCompatActivity() {

    lateinit var mDrawerLayout: DrawerLayout
    lateinit var mDebugDrawer: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        setUpToolbar()
        setUpNavigationDrawer()
        createFragmentAndPresenter()

    }

    private fun createFragmentAndPresenter() {

        var mainFragment: MainFragment? = supportFragmentManager.findFragmentById(R.id.contentFrame) as MainFragment?
        if (mainFragment == null) {
            mainFragment = MainFragment()
            ActivityUtils.addFragmentToActivity(supportFragmentManager, mainFragment)
        }
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
                R.id.menu_item_fragment_first -> { /*do nothing, we're already on that screen*/
                }
                R.id.statistics_navigation_menu_item -> {
//                    ActivityUtils.addFragmentToActivity(supportFragmentManager,)
                }
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


}




