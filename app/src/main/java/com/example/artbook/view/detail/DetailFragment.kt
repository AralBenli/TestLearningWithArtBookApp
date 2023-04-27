package com.example.artbook.view.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.example.artbook.R
import com.example.artbook.databinding.FragmentArtDetailsBinding
import com.example.artbook.util.Status
import com.example.artbook.view.art.ArtViewModel
import com.example.artbook.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

import javax.inject.Inject

/**
 * Created by AralBenli on 25.04.2023.
 */

@AndroidEntryPoint

class DetailFragment @Inject constructor(
    val glide: RequestManager
) : Fragment(R.layout.fragment_art_details) {

    private var fragmentBinding: FragmentArtDetailsBinding? = null
    private val detailViewModel : ArtViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).backNavigation(true)
        (requireActivity() as MainActivity).titleText(false)

        val binding = FragmentArtDetailsBinding.bind(view)
        fragmentBinding = binding

        subscribeToObservers()



        binding.artImageView.setOnClickListener {
            findNavController().navigate(R.id.detailToSearch)
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                detailViewModel.setSelectedImage("")
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)


        binding.saveButton.setOnClickListener {
            detailViewModel.checkInputs(
                binding.nameTxt.text.toString(),
                binding.artistTxt.text.toString(),
                binding.yearTxt.text.toString()
            )
        }
    }

    private fun subscribeToObservers() {
        detailViewModel.selectedImageUrl.observe(viewLifecycleOwner, Observer { url ->
            Log.d("url", url)
            fragmentBinding?.let {
                glide.load(url).into(it.artImageView)
            }
        })

        detailViewModel.insertArtMessage.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.detailToArt)
                    detailViewModel.resetInsertArtMsg()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_SHORT)
                        .show()
                }

                else -> {}
            }
        })
    }


    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }


}