package com.example.artbook.view

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.example.artbook.view.art.ArtFragment
import com.example.artbook.view.art.ArtRecyclerAdapter
import com.example.artbook.view.detail.DetailFragment
import com.example.artbook.view.search.SearchAdapter
import com.example.artbook.view.search.SearchFragment
import javax.inject.Inject


/**
 * Created by AralBenli on 26.04.2023.
 */

class ArtFragmentFactory @Inject constructor(
    private val glide : RequestManager,
    private val artRecyclerAdapter: ArtRecyclerAdapter,
    private val searchAdapter: SearchAdapter
) : FragmentFactory() {


    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            SearchFragment::class.java.name -> {
                val fragment = SearchFragment(
                    searchAdapter
                )
                fragment
            }
            DetailFragment::class.java.name -> {
                val fragment = DetailFragment(
                    glide
                )
                fragment
            }
            ArtFragment::class.java.name -> {
                val fragment = ArtFragment(
                    artRecyclerAdapter
                )
                fragment
            }
            else -> super.instantiate(classLoader, className)
        }
    }
}