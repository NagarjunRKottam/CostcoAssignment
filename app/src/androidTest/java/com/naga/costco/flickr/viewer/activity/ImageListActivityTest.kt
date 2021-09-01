package com.naga.costco.flickr.viewer.activity

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.filters.LargeTest
import com.naga.costco.flickr.viewer.R
import com.naga.costco.flickr.viewer.adapter.ImageRecyclerViewAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
@LargeTest
class ImageListActivityTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<ImageListActivity>()


    @Test
    fun validateOnAppLaunch() {
        onView(withId(R.id.searchBox)).check(matches(ViewMatchers.isDisplayed()))
    }


    @Test
    fun validateSearch() {
        // Type text and then press the button.
        onView(withId(R.id.searchBox))
            .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard())
        onView(withId(R.id.search_button)).perform(click())

        //Time to load the images
        TimeUnit.SECONDS.sleep(8L)

        // Confirm Recycler View in View
        onView(withId(R.id.photosRecyclerView)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun validateNavigateToDetailSPage() {
        // Type text and then press the button.
        onView(withId(R.id.searchBox))
            .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard())
        onView(withId(R.id.search_button)).perform(click())

        //Time to load the images
        TimeUnit.SECONDS.sleep(8L)

        // Click on first Image in recycler view
        onView(withId(R.id.photosRecyclerView))
            .perform(actionOnItemAtPosition<ImageRecyclerViewAdapter.PhotosViewHolder>(1, click()))

        // Confirm Image Details Activity in View
        onView(withId(R.id.toolbar_layout)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun validateNavigateToImageListPage() {
        // Type text and then press the button.
        onView(withId(R.id.searchBox))
            .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard())
        onView(withId(R.id.search_button)).perform(click())
        TimeUnit.SECONDS.sleep(8L)


        onView(withId(R.id.photosRecyclerView))
            .perform(actionOnItemAtPosition<ImageRecyclerViewAdapter.PhotosViewHolder>(1, click()))

        Espresso.pressBack()
        onView(withId(R.id.photosRecyclerView)).check(matches(ViewMatchers.isDisplayed()))
    }

    companion object {
        val STRING_TO_BE_TYPED = "Costco"
    }
}