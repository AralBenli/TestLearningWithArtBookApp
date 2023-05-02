package com.example.artbook.view


import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.MediumTest
import com.example.artbook.HiltTestActivity
import com.example.artbook.MainActivityListener
import com.example.artbook.launchFragmentInHiltContainer
import com.example.artbook.view.art.ArtFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject
import com.example.artbook.R
import org.mockito.Mockito.mock


/**
 * Created by AralBenli on 2.05.2023.
 */
@MediumTest
@HiltAndroidTest
class ArtFragmentTest() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    val mockListener = mock(MainActivityListener::class.java)


    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    @Inject
    lateinit var listener: MainActivityListener



    @Before
    fun setup() {
        hiltRule.inject()

    }

    @Test
    fun testNavigationFromArtToDetails() {

        val navController = mock(NavController::class.java)
        mockListener.setBackNavigation(false)
        mockListener.setTitleText(true)

        launchFragmentInHiltContainer<ArtFragment>(factory = fragmentFactory) {
            Navigation.setViewNavController(requireView(), navController)
            listener.setBackNavigation(false)
            listener.setTitleText(false)

        }
        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click())
        Mockito.verify(navController).navigate(R.id.mainToDetail)


    }
}