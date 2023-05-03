package com.example.artbook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by AralBenli on 2.05.2023.
 */

@AndroidEntryPoint
class HiltTestActivity : AppCompatActivity() , MainActivityListener{

    var listener: MainActivityListener = this

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ArtBook)
        super.onCreate(savedInstanceState)
    }
    override fun setBackNavigation(visibilityBack: Boolean) {
    }

    override fun setTitleText(visibilityTitle: Boolean) {
    }
}