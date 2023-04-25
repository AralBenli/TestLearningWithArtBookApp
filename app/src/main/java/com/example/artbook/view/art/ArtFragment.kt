package com.example.artbook.view.art

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.artbook.R
import com.example.artbook.databinding.FragmentArtBinding

/**
 * Created by AralBenli on 25.04.2023.
 */
class ArtFragment : Fragment(R.layout.fragment_art){

    private var fragmentBinding : FragmentArtBinding? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentArtBinding.bind(view)
        fragmentBinding = binding


        binding.fab.setOnClickListener {
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToDetailFragment())
        }

    }


    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }

}