package com.naga.costco.flickr.viewer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naga.costco.flickr.viewer.api.WebClient
import com.naga.costco.flickr.viewer.data.Photo
import kotlinx.coroutines.launch

class ImageListViewModel : ViewModel() {

    private val mutableImagesLiveData = MutableLiveData<List<Photo>>()
    val photosLiveData: LiveData<List<Photo>> = mutableImagesLiveData

    fun fetchImages(searchTerm: String) {
        if (searchTerm.isBlank()) {
            mutableImagesLiveData.postValue(emptyList())
            return
        }
        viewModelScope.launch {
            val searchResponse = WebClient.client.fetchImages(searchTerm)
            val photosList = searchResponse.photos.photo.map { photo ->
                Photo(
                    id = photo.id,
                    url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                    title = photo.title
                )
            }
            mutableImagesLiveData.postValue(photosList)
        }
    }
}
