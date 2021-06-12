package com.example.android.townpeople.presentaition

import androidx.fragment.app.FragmentManager
import androidx.test.core.app.launchActivity
import com.example.android.townpeople.presentaition.list.PeopleFragment
import junit.framework.Assert
import junit.framework.Assert.assertTrue
import org.junit.Test


class MainActivityTest {
    @Test
    fun fragment_destination_is_PeopleFragment_initially() {
        val scenario = launchActivity<MainActivity>()
        var fragmentManager: FragmentManager? = null

        scenario.onActivity {
            fragmentManager = it.supportFragmentManager.primaryNavigationFragment?.childFragmentManager
        }

        val fragmentDestination = fragmentManager?.primaryNavigationFragment

        assertTrue(fragmentDestination is PeopleFragment)
    }
}