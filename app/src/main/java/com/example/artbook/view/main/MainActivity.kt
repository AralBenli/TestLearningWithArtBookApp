package com.example.artbook.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.artbook.MainActivityListener
import com.example.artbook.R
import com.example.artbook.databinding.ActivityMainBinding
import com.example.artbook.view.ArtFragmentFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() , MainActivityListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navHostFragment: NavHostFragment
    var listener: MainActivityListener = this

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        navigation()
    }

    private fun navigation() {
        binding.backIcon.setOnClickListener {
            navHostFragment.findNavController().popBackStack()
        }
    }

    private fun initViews() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
    }
    override fun setBackNavigation(visibilityBack: Boolean) {
        if (visibilityBack) {
            binding.backIcon.visibility = View.VISIBLE
        } else {
            binding.backIcon.visibility = View.GONE
        }
    }

    override fun setTitleText(visibilityTitle: Boolean) {
        if (visibilityTitle) {
            binding.myArtBooKText.visibility = View.VISIBLE
        } else {
            binding.myArtBooKText.visibility = View.GONE
        }
    }
}

