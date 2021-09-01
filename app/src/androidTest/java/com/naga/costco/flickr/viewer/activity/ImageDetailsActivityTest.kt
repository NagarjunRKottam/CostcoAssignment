package com.naga.costco.flickr.viewer.activity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import com.naga.costco.flickr.viewer.R
import com.naga.costco.flickr.viewer.adapter.ImageRecyclerViewAdapter
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.TimeUnit

class ImageDetailsActivityTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<ImageListActivity>()

    @Before
    fun setUp() {
        onView(withId(R.id.searchBox))
            .perform(
                typeText(ImageListActivityTest.STRING_TO_BE_TYPED),
                closeSoftKeyboard()
            )
        onView(withId(R.id.search_button)).perform(click())

        //Time to load the images
        TimeUnit.SECONDS.sleep(8L)

        // Click on first Image in recycler view
        onView(withId(R.id.photosRecyclerView))
            .perform(
                actionOnItemAtPosition<ImageRecyclerViewAdapter.PhotosViewHolder>(
                    1,
                    click()
                )
            )
    }

    @Test
    fun validateBackButton() {
        // Confirm Image Details Activity in View
        onView(withId(R.id.toolbar_layout))
            .check(matches(ViewMatchers.isDisplayed()))

        // Clicking on back button image in details page
        onView(withId(R.id.back_button_image)).perform(click())

        // Confirm Image List Activity in View
        onView(withId(R.id.photosRecyclerView)).check(matches(ViewMatchers.isDisplayed()))
    }

    @After
    fun tearDown() {
    }
}