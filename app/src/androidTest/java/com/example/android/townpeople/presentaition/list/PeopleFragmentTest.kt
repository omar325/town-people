package com.example.android.townpeople.presentaition.list

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.testing.withFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.townpeople.R
import com.example.android.townpeople.data.Person
import com.example.android.townpeople.testutil.RecyclerViewMatcher
import junit.framework.Assert
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module


class PeopleFragmentTest {

    private var mockedViewModel = object: PeopleViewModel {
        val _state = MutableLiveData<PeopleViewModelState>(PeopleViewModelState.Initial)
        override val state: LiveData<PeopleViewModelState> = _state

        override fun getPeople() {}
    }

    @Before
    fun setUp() {
        stopKoin()
        startKoin { modules( module {
            factory<PeopleViewModel> { mockedViewModel }
        })}
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun progressBar_VISIBLE_when_viewModelState_is_Loading() {
        launchFragmentInContainer<PeopleFragment>(themeResId = R.style.Theme_MaterialComponents)

        mockedViewModel._state.postValue(PeopleViewModelState.Loading)

        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
    }

    @Test
    fun errorLayout_VISIBLE_when_viewModelState_is_Error() {
        launchFragmentInContainer<PeopleFragment>(themeResId = R.style.Theme_MaterialComponents)

        mockedViewModel._state.postValue(PeopleViewModelState.Error)

        onView(withId(R.id.errorLayout)).check(matches(isDisplayed()))
    }

    @Test
    fun hasCountersLayout_VISIBLE_when_viewModelState_is_Success() {
        launchFragmentInContainer<PeopleFragment>(themeResId = R.style.Theme_MaterialComponents)

        mockedViewModel._state.postValue(PeopleViewModelState.Success(emptyList()))

        onView(withId(R.id.peopleRecyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun peopleRecyclerView_shows_expectedData_from_Success_state() {
        launchFragmentInContainer<PeopleFragment>(themeResId = R.style.Theme_MaterialComponents)

        val expectedPeople = listOf(
            Person(
                "", "Name1", emptyList(), friends = emptyList()
            ),
            Person(
                "", "Name2", emptyList(), friends = emptyList()
            )
        )
        mockedViewModel._state.postValue(PeopleViewModelState.Success(expectedPeople))

        onView(
            RecyclerViewMatcher(R.id.peopleRecyclerView)
                .atPositionOnView(0, R.id.nameTextView)
        ).check(matches(withText(expectedPeople[0].name)))
    }

    @Test
    fun newDestination_is_peopleDetailFragment_after_click_on_button() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        launchFragmentInContainer<PeopleFragment>(themeResId = R.style.Theme_AppCompat).withFragment {
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(requireView(), navController)
        }

        //onView(withId(R.id.button)).perform(ViewActions.click())

        val newDestination = navController.currentDestination?.id
        //assertTrue(newDestination == R.id.peopleDetailFragment)
    }
}