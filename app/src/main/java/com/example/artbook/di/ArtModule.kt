package com.example.artbook.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.example.artbook.view.ArtFragmentFactory
import com.example.artbook.view.art.ArtRecyclerAdapter
import com.example.artbook.view.search.SearchAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

/**
 * Created by AralBenli on 26.04.2023.
 */
@Module
@InstallIn(SingletonComponent::class)
object ArtModule {


    @Provides
    fun provideGlide(@ApplicationContext context: Context): RequestManager {
        return Glide.with(context)
    }

    @Provides
    fun provideArtRecyclerAdapter(glide: RequestManager): ArtRecyclerAdapter {
        return ArtRecyclerAdapter(glide)
    }



    @Provides
    fun provideArtFragmentFactory(
        glide: RequestManager,
        artRecyclerAdapter: ArtRecyclerAdapter ,
        searchAdapter: SearchAdapter
    ): ArtFragmentFactory {
        return ArtFragmentFactory(glide, artRecyclerAdapter , searchAdapter)
    }
}

