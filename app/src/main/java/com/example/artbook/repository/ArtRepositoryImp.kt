package com.example.artbook.repository

import androidx.lifecycle.LiveData
import com.example.artbook.local.entity.ArtModel
import com.example.artbook.model.ImageResponse
import com.example.artbook.util.Resource

/**
 * Created by AralBenli on 25.04.2023.
 */

interface ArtRepositoryImp {

    suspend fun insertArt(art : ArtModel)

    suspend fun deleteArt(art : ArtModel)

    fun getArt(): LiveData<List<ArtModel>>

    suspend fun searchImage(imageString : String) : Resource<ImageResponse>


}