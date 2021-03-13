package com.orti.shoppingtodo

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.internal.matchers.Matches


@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {
    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun isActivity_inView() {
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun isActivity_RecyclerViewinView_fab() {
        onView(withId(R.id.recyclerview)).check(matches(isDisplayed()))

        onView(withId(R.id.fab)).check(matches(isDisplayed()))
    }

}