package com.example.artbook.view.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.RequestManager
import com.example.artbook.R
import com.example.artbook.databinding.FragmentArtDetailsBinding
import com.example.artbook.databinding.FragmentImageSearchBinding
import com.example.artbook.databinding.SearchItemRowBinding
import com.example.artbook.util.Status
import com.example.artbook.view.art.ArtViewModel
import com.example.artbook.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by AralBenli on 25.04.2023.
 */
@AndroidEntryPoint

class SearchFragment @Inject constructor(
    private val searchAdapter: SearchAdapter
) : Fragment(R.layout.fragment_image_search) {

    private var fragmentBinding: FragmentImageSearchBinding? = null

    //when using different fragments but same view model instance you need to use activityViewModels() function
    private val searchViewModel: ArtViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).backNavigation(true)
        (requireActivity() as MainActivity).titleText(false)
        val binding = FragmentImageSearchBinding.bind(view)
        fragmentBinding = binding
        binding.recyclerViewSearch.adapter = searchAdapter

        var job: Job? = null

        binding.searchTxt.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000)
                it?.let {
                    if (it.toString().isNotEmpty()) {
                        searchViewModel.searchForImage(it.toString())
                    }
                }
            }
        }
        subscribeToObservers()
        searchAdapter.clickArt = {
            findNavController().navigate(R.id.searchToDetail)
            searchViewModel.setSelectedImage(it)

        }
    }

    private fun subscribeToObservers() {
        searchViewModel.imageList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    val urls = it.data?.hits?.map { imageResult -> imageResult.previewURL }
                    searchAdapter.searchImageList = urls ?: listOf()
                    fragmentBinding?.progressBar?.visibility = View.GONE

                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_LONG)
                        .show()
                    fragmentBinding?.progressBar?.visibility = View.GONE
                }

                Status.LOADING -> {
                    fragmentBinding?.progressBar?.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }
}