package com.example.artbook.view.art

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artbook.local.entity.ArtModel
import com.example.artbook.model.ImageResponse
import com.example.artbook.repository.IArtRepository
import com.example.artbook.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by AralBenli on 25.04.2023.
 */

@HiltViewModel
class ArtViewModel @Inject constructor(
    private val repo: IArtRepository
) : ViewModel() {

    //ArtFragment
    val artList = repo.getArt()


    //Search Image
    private val images = MutableLiveData<Resource<ImageResponse>>()
    val imageList: LiveData<Resource<ImageResponse>>
        get() = images


    private val selectedImage = MutableLiveData<String>()
    val selectedImageUrl: LiveData<String>
        get() = selectedImage

    //Art Details

    private var insertArtMsg = MutableLiveData<Resource<ArtModel>>()
    val insertArtMessage: LiveData<Resource<ArtModel>>
        get() = insertArtMsg

    fun resetInsertArtMsg() {
        insertArtMsg = MutableLiveData<Resource<ArtModel>>()
    }

    fun setSelectedImage(url: String) {
        selectedImage.postValue(url)
    }

    fun deleteArt(art: ArtModel) = viewModelScope.launch {
        repo.deleteArt(art)
    }

    fun insertArt(art: ArtModel) = viewModelScope.launch {
        repo.insertArt(art)
    }

    fun searchForImage(searchString: String) {

        if (searchString.isEmpty()) {
            return
        }
        images.value = Resource.loading(data = null)
        viewModelScope.launch {
            val response = repo.searchImage(searchString)
            images.value = response
        }

    }


    fun checkInputs(name: String, artistName: String, year: String) {
        if (name.isEmpty() || artistName.isEmpty() || year.isEmpty()) {
            insertArtMsg.postValue(Resource.error("Enter name, artist, year", null))
            return
        }
        val yearInt = try {
            year.toInt()
        } catch (e: Exception) {
            insertArtMsg.postValue(Resource.error("year should be number", null))
            return
        }
        val art = ArtModel(name, artistName, yearInt, selectedImage.value ?: "")
        insertArt(art)
        setSelectedImage("")
        insertArtMsg.postValue(Resource.success(art))
    }
}