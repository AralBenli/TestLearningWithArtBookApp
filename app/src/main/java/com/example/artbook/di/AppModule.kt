package com.example.artbook.di

import android.content.Context
import androidx.room.Room
import com.example.artbook.util.Constants
import com.example.artbook.local.database.ArtDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by AralBenli on 25.04.2023.
 */


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context : Context) = Room.databaseBuilder(
        context,
        ArtDatabase::class.java,
        "ArtBookDB"
    ).build()

    @Singleton
    @Provides
    fun injectDao(database : ArtDatabase) = database.artDao()

    @Singleton
    @Provides
    fun injectRetrofitApi(): ApiService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(ApiService::class.java)
    }

}