package com.example.artbook.view.art

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.artbook.R
import com.example.artbook.databinding.FragmentArtBinding
import com.example.artbook.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by AralBenli on 25.04.2023.
 */
@AndroidEntryPoint

class ArtFragment @Inject constructor(
    private val artRecyclerAdapter: ArtRecyclerAdapter
) : Fragment(R.layout.fragment_art) {

    private var fragmentBinding: FragmentArtBinding? = null
    private val artViewModel: ArtViewModel by activityViewModels()

    private val swipeCallBack =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val layoutPosition = viewHolder.layoutPosition
                val selectedArt = artRecyclerAdapter.artList[layoutPosition]
                artViewModel.deleteArt(selectedArt)

            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentArtBinding.bind(view)
        fragmentBinding = binding
        (requireActivity() as MainActivity).backNavigation(false)
        (requireActivity() as MainActivity).titleText(true)
        subscribeToObservers()
        fragmentBinding?.recyclerViewArt?.adapter = artRecyclerAdapter
        ItemTouchHelper(swipeCallBack).attachToRecyclerView(fragmentBinding?.recyclerViewArt)


        binding.fab.setOnClickListener {
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToDetailFragment())
        }

    }


    private fun subscribeToObservers() {
        artViewModel.artList.observe(viewLifecycleOwner, Observer {
            artRecyclerAdapter.artList = it
        })
    }


    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }

}