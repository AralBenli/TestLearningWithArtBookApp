package com.example.artbook.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.artbook.local.entity.ArtModel

/**
 * Created by AralBenli on 25.04.2023.
 */

@Dao
interface ArtDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(art : ArtModel)

    @Delete
    suspend fun  deleteArt(art: ArtModel)

    @Query("SELECT * FROM artModel")
    fun observeArts(): LiveData<List<ArtModel>>


}