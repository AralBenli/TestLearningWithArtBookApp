package com.example.artbook.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.artbook.getOrAwaitValueTest
import com.example.artbook.repository.FakeArtRepository
import com.example.artbook.util.Status
import com.example.artbook.view.art.ArtViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Created by AralBenli on 28.04.2023.
 */

@ExperimentalCoroutinesApi
class ArtViewModelTest {

    private lateinit var viewModel: ArtViewModel


    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setup() {
    //Test Doubles

    viewModel = ArtViewModel(FakeArtRepository())

    }

    @Test
    fun `insert art without year returns error`(){
        viewModel.checkInputs("night" , "nothing", "")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)

    }
    @Test
    fun `insert art without name returns error`(){
        viewModel.checkInputs("" , "nothing", "1900")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)

    }
    @Test
    fun `insert art without artistName returns error`(){
        viewModel.checkInputs("night" , "", "1715")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)

    }

}