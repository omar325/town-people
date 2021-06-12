package com.example.android.townpeople.presentaition.list

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.testing.withFragment
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.example.android.townpeople.R
import junit.framework.Assert
import junit.framework.Assert.assertTrue
import org.junit.Test


class PeopleFragmentTest {
    @Test
    fun newDestination_is_peopleDetailFragment_after_click_on_button() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        launchFragmentInContainer<PeopleFragment>(themeResId = R.style.Theme_AppCompat).withFragment {
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.button)).perform(ViewActions.click())

        val newDestination = navController.currentDestination?.id
        assertTrue(newDestination == R.id.peopleDetailFragment)
    }
}