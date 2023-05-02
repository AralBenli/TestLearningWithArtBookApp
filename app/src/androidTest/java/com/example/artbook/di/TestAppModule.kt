package com.example.artbook.di

/**
 * Created by AralBenli on 28.04.2023.
 */
import android.content.Context
import androidx.room.Room
import com.example.artbook.MainActivityListener
import com.example.artbook.local.database.ArtDatabase
import com.example.artbook.view.main.MainActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.mockito.Mockito.mock
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("testDatabase")
    fun injectInMemoryRoom(@ApplicationContext context : Context) =
        Room.inMemoryDatabaseBuilder(context, ArtDatabase::class.java)
            .allowMainThreadQueries()
            .build()


}
