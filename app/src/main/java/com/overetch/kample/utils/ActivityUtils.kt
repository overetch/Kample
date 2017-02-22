package com.overetch.kample.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.overetch.kample.R

/**
 * Created by Igor.Sakovich on 24.01.2017.
 */

object ActivityUtils {
    fun addFragmentToActivity(fragmentManager: FragmentManager,
                              fragment: Fragment,
                              addToBackStack: Boolean = false) {
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.contentFrame, fragment)
        if (addToBackStack)
            transaction.addToBackStack(null)
        transaction.commit()
    }
}
