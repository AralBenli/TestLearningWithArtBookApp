package com.example.artbook.view.detail

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.artbook.R
import com.example.artbook.databinding.FragmentArtDetailsBinding
import com.example.artbook.view.art.ArtFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by AralBenli on 25.04.2023.
 */


@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_art_details) {

    private var fragmentBinding : FragmentArtDetailsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentArtDetailsBinding.bind(view)
        fragmentBinding = binding

        binding.artImageView.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToSearchFragment())
        }

        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)

    }

    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }


}