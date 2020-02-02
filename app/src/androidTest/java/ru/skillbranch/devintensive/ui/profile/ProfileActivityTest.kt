package ru.skillbranch.devintensive.ui.profile

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ProfileActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(ProfileActivity::class.java)

    @Test
    fun mainTst() {
        onView(withText("Android Developer")).check(matches(isDisplayed()))
    }

}