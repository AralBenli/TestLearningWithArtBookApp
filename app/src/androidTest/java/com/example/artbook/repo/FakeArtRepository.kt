package com.example.artbook.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.artbook.local.entity.ArtModel
import com.example.artbook.model.ImageResponse
import com.example.artbook.repository.IArtRepository
import com.example.artbook.util.Resource

/**
 * Created by AralBenli on 28.04.2023.
 */
class FakeArtRepository: IArtRepository {

    private val arts = mutableListOf<ArtModel>()
    private val artsLiveData = MutableLiveData< List<ArtModel>>(arts)


    override suspend fun insertArt(art: ArtModel) {
        arts.add(art)
        refreshData()
    }

    override suspend fun deleteArt(art: ArtModel) {
        arts.remove(art)
        refreshData()
    }

    override fun getArt(): LiveData<List<ArtModel>> {
        return artsLiveData
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return Resource.success(ImageResponse(listOf(),0,0))
    }

    private fun refreshData(){
        artsLiveData.postValue(arts)
    }
}