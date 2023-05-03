package com.example.artbook.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.example.artbook.MainActivityListener
import com.example.artbook.launchFragmentInHiltContainer
import com.example.artbook.repo.FakeArtRepository
import com.example.artbook.view.art.ArtViewModel
import com.example.artbook.view.search.SearchFragment
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
import com.example.artbook.view.search.SearchAdapter
import com.google.common.truth.Truth.assertThat


/**
 * Created by AralBenli on 3.05.2023.
 */

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class SearchFragmentTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    @Inject
    lateinit var listener: MainActivityListener

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun selectImage() {
        val navController = Mockito.mock(NavController::class.java)
        val selectedImage = "aral.com"
        val testViewModel = ArtViewModel(FakeArtRepository())
        launchFragmentInHiltContainer<SearchFragment>(factory = fragmentFactory) {
            Navigation.setViewNavController(requireView(), navController)
            searchViewModel = testViewModel
            searchAdapter.searchImageList = listOf(selectedImage)
        }

        Espresso.onView(ViewMatchers.withId(R.id.recyclerViewSearch)).perform(
            RecyclerViewActions.actionOnItemAtPosition<SearchAdapter.SearchViewHolder>(0, click()
            )
        )
        //is selected image url equal to my input 'selectedImage'
        assertThat(testViewModel.selectedImageUrl.getOrAwaitValue()).isEqualTo(selectedImage)
        //is navigation works after it clicks is it going to go to detail
        Mockito.verify(navController).navigate(R.id.searchToDetail)
    }
}