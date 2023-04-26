package com.example.artbook.repository

import androidx.lifecycle.LiveData
import com.example.artbook.di.ApiService
import com.example.artbook.local.dao.ArtDao
import com.example.artbook.local.entity.ArtModel
import com.example.artbook.model.ImageResponse
import com.example.artbook.util.Resource
import javax.inject.Inject

/**
 * Created by AralBenli on 25.04.2023.
 */


class ArtRepository @Inject constructor(
    private val artDao: ArtDao,
    private val service: ApiService
) : IArtRepository {


    override suspend fun insertArt(art: ArtModel) {
        artDao.insert(art)
    }

    override suspend fun deleteArt(art: ArtModel) {
        artDao.deleteArt(art)
    }

    override fun getArt(): LiveData<List<ArtModel>> {
        return artDao.observeArts()
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return try {
            val response = service.imageSearch(imageString)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error", null)
            } else {
                Resource.error("Error", null)
            }
        } catch (e: Exception) {
            Resource.error("No Data", null)
        }
    }
}