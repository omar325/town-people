package com.example.android.townpeople.presentaition.detail

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.testing.withFragment
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.android.townpeople.R
import com.example.android.townpeople.data.Person
import com.example.android.townpeople.presentaition.list.PeopleFragment
import junit.framework.TestCase
import org.junit.Test

class PeopleDetailFragmentTest {
    @Test
    fun PeopleDetailFragment_shows_expected_data() {
        val personDetail = Person("1", "Name1", listOf("profession1"), friends = listOf("friend1"))
        var expectedAgeText = ""

        launchFragmentInContainer<PeopleDetailFragment>(
            themeResId = R.style.Theme_MaterialComponents,
            fragmentArgs = PeopleDetailFragmentArgs(
                personDetail
            ).toBundle()
        ).withFragment {
            expectedAgeText = getString(R.string.age, personDetail.age)
        }

        onView(withId(R.id.personDetailName)).check(matches(withText(personDetail.name)))
        onView(withId(R.id.personDetailAge)).check(matches(withText(expectedAgeText)))
        onView(withId(R.id.hairColor)).check(matches(withText(personDetail.hairColor)))
        onView(withId(R.id.weightText)).check(matches(withText(personDetail.weight.toString())))
        onView(withId(R.id.heightText)).check(matches(withText(personDetail.height.toString())))
    }
}