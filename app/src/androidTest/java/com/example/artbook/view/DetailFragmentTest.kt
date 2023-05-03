package com.example.artbook.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.example.artbook.MainActivityListener
import com.example.artbook.launchFragmentInHiltContainer
import com.example.artbook.view.detail.DetailFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject
import com.example.artbook.R
import com.example.artbook.getOrAwaitValue
import com.example.artbook.local.entity.ArtModel
import com.example.artbook.repo.FakeArtRepository
import com.example.artbook.view.art.ArtViewModel
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import com.google.common.truth.Truth.assertThat

/**
 * Created by AralBenli on 3.05.2023.
 */

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class DetailFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Inject
    lateinit var listener: MainActivityListener

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory


    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testNavigationFromDetailToSearch() {
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<DetailFragment>(factory = fragmentFactory) {
            Navigation.setViewNavController(requireView(), navController)
        }
        Espresso.onView(ViewMatchers.withId(R.id.artImageView)).perform(ViewActions.click())
        Mockito.verify(navController).navigate(R.id.detailToSearch)
    }

    @Test
    fun testListener() {
        val mockedListener = mock(MainActivityListener::class.java)
        val fragment = DetailFragmentTest()
        fragment.listener = mockedListener

        // Simulate that the back navigation is enabled in the fragment
        fragment.listener.setBackNavigation(true)

        // Verify that the setBackNavigation method is called with the expected parameter value
        verify(mockedListener).setBackNavigation(true)
    }


    @Test
    fun testPopBackStack() {
            // Mock the NavController object
            val navController = mock(NavController::class.java)

            // Launch the fragment and set the NavController for the fragment's view
            launchFragmentInHiltContainer<DetailFragment>(factory = fragmentFactory) {
                Navigation.setViewNavController(requireView(), navController)
            }

        pressBack()
        Mockito.verify(navController).popBackStack()

        }

    @Test
    fun testSave(){
        val testViewModel = ArtViewModel(FakeArtRepository())
        launchFragmentInHiltContainer<DetailFragment>(factory = fragmentFactory
        ){
            detailViewModel = testViewModel
        }

        Espresso.onView(withId(R.id.nameTxt)).perform(replaceText("Nightfall"))
        Espresso.onView(withId(R.id.artistTxt)).perform(replaceText("Aral"))
        Espresso.onView(withId(R.id.yearTxt)).perform(replaceText("1770"))
        Espresso.onView(withId(R.id.saveButton)).perform(click())

        assertThat(testViewModel.artList.getOrAwaitValue()).contains(
            ArtModel("Nightfall", "Aral" , 1770,"")
        )
    }
}

